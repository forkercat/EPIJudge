package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
public class Combinations {
  @EpiTest(testDataFile = "combinations.tsv")


  // 1 2 3 4 (n = 4, k = 2)
  // 1 2
  // 1 3
  // 1 4
  // 2 3
  // 2 4
  // 3 4

  // 1 2 3 4 (n = 4, k = 3)
  // 1= 2,3,4 (pick 2)
  // 2= 3,4   (pick 2)
  // 3= 4     (N/A)
  // 4=       (N/A)

  public static List<List<Integer>> combinations(int n, int k) {
    // Init
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> nums = new ArrayList<>();
    // Backtracking
    combinations(n, k, 1, nums, result);
    return result;
  }

  private static void combinations(int n, int k, int d, List<Integer> nums, List<List<Integer>> result) {
    // base case
    int numLeft = n - d + 1;
    if (numLeft < k) {
      return;
    }

    if (k <= 0) {
      result.add(new ArrayList<>(nums));
      return;
    }

    for (int i = d; i <= n; ++i) {
      nums.add(i);
      combinations(n, k - 1, i + 1, nums, result); // k - 1 because we have put a new number to nums
      nums.remove(nums.size() - 1);
    }
  }


  @EpiTestComparator
  public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Combinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
