package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ListCyclicRightShift {
  @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")

  public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L, int k) {
    if (L == null) {
      return null;
    }

    int len = 1;
    ListNode<Integer> p = L;
    while (p.next != null) {
      p = p.next;
      ++len;
    }
    p.next = L; // make a ring

    //  1  2  3  4  5, k = 2
    //        p
    //  4  5  1  2  3
    k = k % len;
    if (k == 0) return L;
    int step = len - k - 1;
    p = L;
    for (int i = 0; i < step; ++i) {
      p = p.next;
    }

    ListNode<Integer> newHead = p.next;
    p.next = null; // remove the ring

    return newHead;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ListCyclicRightShift.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
