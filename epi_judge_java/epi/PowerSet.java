package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class PowerSet {
  @EpiTest(testDataFile = "power_set.tsv")

  /**
   * Use the code in 15.6
   */
  /*
  public static List<List<Integer>> generatePowerSet(List<Integer> inputSet) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> nums = new ArrayList<>();
    int n = inputSet.size();
    for (int k = 0; k <= n; ++k) {
      combine(n, k, 1, inputSet, nums, result);
    }
    return result;
  }

  private static void combine(int n, int k, int d, List<Integer> inputSet, List<Integer> nums, List<List<Integer>> result) {
    // base case
    if (nums.size() == k) { // get a result
      result.add(new ArrayList<>(nums));
      return;
    }
    if (n - d + 1 < k - nums.size()) { // remaining elements are not enough
      return;
    }

    for (int i = d; i <= n; ++i) {
      int elem = inputSet.get(i - 1);
      nums.add(elem);
      combine(n, k, i + 1, inputSet, nums, result);
      nums.remove(nums.size() - 1); // remove the last
    }
  }

   */



  /**
   * Brute-force Solution
   */
  public static List<List<Integer>> generatePowerSet(List<Integer> inputSet) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> numList = new ArrayList<>();
    result.add(new ArrayList<>()); // empty set
    subset(0, numList, inputSet, result);
    return result;
  }

  private static void subset(int offset, List<Integer> numList, List<Integer> inputSet, List<List<Integer>> result) {
    // base case
    if (offset == inputSet.size()) {
      return;
    }
    int val = inputSet.get(offset);
    // pick
    numList.add(val);
    subset(offset + 1, numList, inputSet, result);
    // add to result(
    result.add(new ArrayList<>(numList));
    // not pick
    numList.remove(numList.size() - 1);
    subset(offset + 1, numList, inputSet, result);
  }



  @EpiTestComparator
  public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    for (List<Integer> l : expected) {
      Collections.sort(l);
    }
    expected.sort(new LexicographicalListComparator<>());
    for (List<Integer> l : result) {
      Collections.sort(l);
    }
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerSet.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
