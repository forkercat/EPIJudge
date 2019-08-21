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
  public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
    int n = maxAdvanceSteps.size();
    int[] count = new int[n];

    for (int i = 0; i < n; ++i) {
      int step = maxAdvanceSteps.get(i);
      if (step > 0) {
        int start = i;
        int end = start + step;
        count[start] += 1;
        if (end < n) {
          count[end] -= 1;
        }
      }
      count[i] += (i > 0) ? count[i - 1] : 0;
      if (i < n - 1 && count[i] == 0) { // not the last element && count == 0
        return false;
      }
    }
    return true;
  }


  // // solution
  // // time: O(N)
  // // space: O(1)
  // public static boolean canReachEnd




  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AdvanceByOffsets.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
