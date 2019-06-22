package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
    ListNode<Integer> dummy = new ListNode<>(0, L);
    ListNode<Integer> subDummy = dummy;
    int k = 1;
    while (k < start) {
      subDummy = subDummy.next;
      ++k;
    } // subHead -> previous node of f-th node
    // reverse sublist
    ListNode<Integer> iter = subDummy.next;
    while (start < finish) {
      //   1     2     3     4     5
      //         s           f
      //subDummy iter  temp temp.next
      //   1     3     2     4     5
      //               s     f
      //subDummy       iter temp  temp.next
      //   1     4     3     2     5
      //                    s/f        <-- DONE
      ListNode<Integer> temp = iter.next; // temp is the new Head
      iter.next = temp.next;
      temp.next = subDummy.next; // NOT: temp.next = iter
      subDummy.next = temp;
      ++start;
    }

    return dummy.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
