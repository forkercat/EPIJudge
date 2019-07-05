package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PivotList {

  public static ListNode<Integer> listPivoting(ListNode<Integer> L, int k) {
    ListNode<Integer> leftDummy = new ListNode<>(-1, null);
    ListNode<Integer> midDummy = new ListNode<>(-1, null);
    ListNode<Integer> rightDummy = new ListNode<>(-1, null);
    ListNode<Integer> leftPrev = leftDummy;
    ListNode<Integer> midPrev = midDummy;
    ListNode<Integer> rightPrev = rightDummy;

    while (L != null) {
      if (L.data < k) { // go to left
        leftPrev.next = L;
        leftPrev = leftPrev.next;
      } else if (L.data > k) { // go to right
        rightPrev.next = L;
        rightPrev = rightPrev.next;
      } else {
        midPrev.next = L;
        midPrev = midPrev.next;
      }
      L = L.next;
    }

    rightPrev.next = null;
    midPrev.next = rightDummy.next;
    leftPrev.next = midDummy.next;

    // Error occurs when mid list is empty
    leftPrev.next = midDummy.next;
    midPrev.next = rightDummy.next;
    rightPrev.next = null;

    return leftDummy.next;
  }



  public static List<Integer> linkedToList(ListNode<Integer> l) {
    List<Integer> v = new ArrayList<>();
    while (l != null) {
      v.add(l.data);
      l = l.next;
    }
    return v;
  }

  @EpiTest(testDataFile = "pivot_list.tsv")
  public static void listPivotingWrapper(TimedExecutor executor,
                                         ListNode<Integer> l, int x)
      throws Exception {
    List<Integer> original = linkedToList(l);

    final ListNode<Integer> finalL = l;
    l = executor.run(() -> listPivoting(finalL, x));

    List<Integer> pivoted = linkedToList(l);

    int mode = -1;
    for (Integer i : pivoted) {
      switch (mode) {
      case -1:
        if (i == x) {
          mode = 0;
        } else if (i > x) {
          mode = 1;
        }
        break;
      case 0:
        if (i < x) {
          throw new TestFailure("List is not pivoted");
        } else if (i > x) {
          mode = 1;
        }
        break;
      case 1:
        if (i <= x) {
          throw new TestFailure("List is not pivoted");
        }
      }
    }

    Collections.sort(original);
    Collections.sort(pivoted);
    if (!original.equals(pivoted))
      throw new TestFailure("Result list contains different values");
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PivotList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
