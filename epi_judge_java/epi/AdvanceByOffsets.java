package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class AdvanceByOffsets {

  @EpiTest(testDataFile = "advance_by_offsets.tsv")

  /*
  // brute-force
  public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
    return dfs(maxAdvanceSteps, 0);
  }

  // dfs
  private static boolean dfs(List<Integer> steps, int pos) {
    if (pos >= steps.size() - 1) {
      return true;
    }
    int numStep = steps.get(pos);
    for (int i = 1; i <= numStep; ++i) {
      if (dfs(steps, pos + i)) {
        return true;
      }
    }
    return false;
  }
  */

  // time: O(N)
  // space: O(N)
  /*
  public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
    int n = maxAdvanceSteps.size();
    int[] numStartExit = new int[n];
    int balance = 0;

    for (int i = 0; i < n; ++i) {
      int step = maxAdvanceSteps.get(i);
      int start = i;
      int end = start + step;
      numStartExit[start] += 1;
      if (end < n) {
        numStartExit[end] -= 1;
      }
      // numStartExit[i] += (i > 0) ? numStartExit[i - 1] : 0;
      balance += numStartExit[i];
      if (i < n - 1 && balance <= 0) { // not the last element && balance <= 0
        return false;
      }
    }
    return true;
  }
  */



  // solution
  // time: O(N)
  // space: O(1)
  // furthestIndex = i + maxAdvanceSteps.get(i)
  public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
    int n = maxAdvanceSteps.size();
    int lastIndex = n - 1;
    int furthestIndex = 0;
    // 1. current index is reachable
    // 2. furthestIndex < the last index (meaning we still not reach the end)
    for (int i = 0; i <= furthestIndex && furthestIndex < lastIndex; ++i) {
      furthestIndex = Math.max(furthestIndex, i + maxAdvanceSteps.get(i));
    }
    return furthestIndex >= lastIndex;
  }





  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AdvanceByOffsets.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
