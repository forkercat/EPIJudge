package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  // public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
  //   // 1 ≤ k ≤ length
  //   ListNode<Integer> first = L;
  //   ListNode<Integer> second = L;
  //   for (int i = 0; i < k; ++i) second = second.next;
  //
  //   if (second == null) return L.next; // k = length
  //
  //   while (second.next != null) {
  //     first = first.next;
  //     second = second.next;
  //   } // stops at last (k + 1)-th
  //
  //   first.next = first.next.next; // consider k = 1
  //
  //   return L;
  // }

  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
    ListNode<Integer> second = L;
    for (int i = 0; i < k; ++i) second = second.next;

    ListNode<Integer> dummy = new ListNode<>(-1, L);
    ListNode<Integer> first = dummy;

    while (second != null) { // including k = length case
      first = first.next;
      second = second.next;
    }

    first.next = first.next.next;
    return dummy.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
