package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MaxTrappedWater {
  @EpiTest(testDataFile = "max_trapped_water.tsv")

  /**
   * Brute-force
   */
  /*
  public static int getMaxTrappedWater(List<Integer> heights) {
    int n = heights.size();
    int maxWater = Integer.MIN_VALUE;
    for (int i = 0; i < n; ++i) {
      for (int j = i + 1; j < n; ++j) {
        maxWater = Math.max(maxWater, (j - i) * Math.min(heights.get(i), heights.get(j)));
      }
    }
    return maxWater;
  }
   */

  /**
   * Greedy
   * Time: O(N)
   */
  public static int getMaxTrappedWater(List<Integer> heights) {
    int n = heights.size();
    int lo = 0, hi = n - 1;
    int maxWater = 0; // because the value won't be negative (and corner case is also 0)
    while (lo <= hi) {
      int loHeight = heights.get(lo);
      int hiHeight = heights.get(hi);
      int water = Math.min(loHeight, hiHeight) * (hi - lo);
      maxWater = Math.max(maxWater, water); // update
      if (loHeight <= hiHeight) lo += 1;
      else                      hi -= 1;
    }
    return maxWater;
  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxTrappedWater.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
