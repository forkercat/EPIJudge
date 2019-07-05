package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsListPalindromic {
  @EpiTest(testDataFile = "is_list_palindromic.tsv")

  public static boolean isLinkedListAPalindrome(ListNode<Integer> L) {
    if (L == null) {
      return true;
    }
    // find the mid
    ListNode<Integer> mid = findMid(L);
    ListNode<Integer> left = L;
    ListNode<Integer> right = mid.next;
    mid.next = null;
    right = reverse(right);
    boolean result = true;
    while (right != null) {
      if (left.data != right.data) {
        result = false;
        break;
      }
      right = right.next;
      left = left.next;
    }

    // restore
    right = reverse(right);
    mid.next = right;

    return result;
  }

  // Find the left-leaning mid
  private static ListNode<Integer> findMid(ListNode<Integer> L) {
    if (L == null) {
      return null;
    }
    ListNode<Integer> slow = L;
    ListNode<Integer> fast = L;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  // Reverse
  private static ListNode<Integer> reverse(ListNode<Integer> L) {
    if (L == null || L.next == null) {
      return L;
    }
    //       1  2  3  4
    // prev  L
    ListNode<Integer> prev = null;
    while (L != null) {
      ListNode<Integer> nextTemp = L.next;
      L.next = prev;
      prev = L;
      L = nextTemp;
    }
    return prev;
  }



  // private static int getLength(ListNode<Integer> L) {
  //   int count = 0;
  //   while (L != null) {
  //     L = L.next;
  //     count += 1;
  //   }
  //   return count;
  // }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
