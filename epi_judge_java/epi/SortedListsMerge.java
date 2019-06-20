package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> n1,
                                                      ListNode<Integer> n2) {
    ListNode<Integer> dummy = new ListNode<>(-1, null);
    ListNode<Integer> prev = dummy;

    while (n1 != null && n2 != null) {
      if (n1.data <= n2.data) {
        prev.next = n1;
        n1 = n1.next;
      } else {
        prev.next = n2;
        n2 = n2.next;
      }
      prev = prev.next;  // don't forget
    }

    prev.next = (n1 != null) ? n1 : n2;
    return dummy.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
