package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class DoTerminatedListsOverlap {

  public static ListNode<Integer> overlappingNoCycleLists(ListNode<Integer> l1, ListNode<Integer> l2) {
    // get the difference in length
    int diff = getLenDiff(l1, l2);

    if (diff >= 0) { // l1 is longer or the same
      while (diff > 0) {
        l1 = l1.next;
        diff -= 1;
      }
    } else { // l2 is longer or the same
      while (diff < 0) {
        l2 = l2.next;
        diff += 1;
      }
    }
    
    while (l1 != null && l2 != null) {
      if (l1 == l2) {
        return l1;   // intersection node exists
      }
      l1 = l1.next;
      l2 = l2.next;
    }
    return null; // no intersection node
  }

  private static int getLenDiff(ListNode<Integer> l1, ListNode<Integer> l2) {
    int len1 = 0, len2 = 0;
    while (l1 != null || l2 != null) {
      if (l1 != null) {
        len1 += 1;
        l1 = l1.next;
      }
      if (l2 != null) {
        len2 += 1;
        l2 = l2.next;
      }
    }
    return len1 - len2;
  }

  /*
  public static ListNode<Integer> overlappingNoCycleLists(ListNode<Integer> l1, ListNode<Integer> l2) {
    if (l1 == null || l2 == null) {
      return null;
    }
    ListNode<Integer> head1 = l1;
    ListNode<Integer> head2 = l2;
    while (l1 != l2) {
      l1 = l1.next;
      l2 = l2.next;
      if (l1 != l2) {
        if (l1 == null) l1 = head2;
        if (l2 == null) l2 = head1;
      }
    }
    return l1;
  }
  */

  @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
  public static void
  overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l1,
                                 ListNode<Integer> l2, ListNode<Integer> common)
      throws Exception {
    if (common != null) {
      if (l1 != null) {
        ListNode<Integer> i = l1;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l1 = common;
      }

      if (l2 != null) {
        ListNode<Integer> i = l2;
        while (i.next != null) {
          i = i.next;
        }
        i.next = common;
      } else {
        l2 = common;
      }
    }

    final ListNode<Integer> finalL0 = l1;
    final ListNode<Integer> finalL1 = l2;
    ListNode<Integer> result =
        executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

    if (result != common) {
      throw new TestFailure("Invalid result");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
