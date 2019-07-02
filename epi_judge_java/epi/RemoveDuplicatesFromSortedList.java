package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RemoveDuplicatesFromSortedList {
  @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")

  // public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
  //   if (L == null) {
  //     return null;
  //   }
  //   ListNode<Integer> curr = L;
  //   while (curr.next != null) {
  //     if (curr.data == curr.next.data) { // found duplicates
  //       curr.next = curr.next.next;
  //     } else {
  //       curr = curr.next;
  //     }
  //   }
  //   return L;
  // }


  // Recursion #1
  public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
    if (L == null || L.next == null) {
      return L;
    }

    if (L.data == L.next.data) {
      L.next = L.next.next;
      removeDuplicates(L);
    } else {
      L.next = removeDuplicates(L.next);
    }
    return L;
  }

  // Recursion #2
  // public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
  //   return removeDuplicates(L, null);
  // }
  //
  // //  1   1   1   2
  // //  p   L
  // private static ListNode<Integer> removeDuplicates(ListNode<Integer> L, Integer prev) {
  //   if (L == null) {
  //     return null;
  //   }
  //
  //   if (prev != null && prev == L.data) {
  //     L = L.next;
  //     L = removeDuplicates(L, prev);
  //   } else {
  //     L.next = removeDuplicates(L.next, L.data);
  //   }
  //
  //   return L;
  // }


    public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
