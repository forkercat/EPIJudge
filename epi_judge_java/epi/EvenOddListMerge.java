package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class EvenOddListMerge {
  @EpiTest(testDataFile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
    ListNode<Integer> evenDummy = new ListNode<>(-1, null);
    ListNode<Integer> oddDummy = new ListNode<>(-1, null);
    ListNode<Integer> evenPrev = evenDummy;
    ListNode<Integer> oddPrev = oddDummy;

    int count = 0;
    while (L != null) {
      if ((count & 1) == 0) { // even
        evenPrev.next = L;
        evenPrev = evenPrev.next;
      } else { // odd
        oddPrev.next = L;
        oddPrev = oddPrev.next;
      }
      count ^= 1;
      L = L.next;
    }
    // evenPrev -> the last node of even list
    evenPrev.next = oddDummy.next;
    oddPrev.next = null; // important. set the tail
    return evenDummy.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
