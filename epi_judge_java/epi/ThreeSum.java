package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSum {
  @EpiTest(testDataFile = "three_sum.tsv")

  // brute-force
  public static boolean hasThreeSum(List<Integer> A, int t) {
    // calculate all combinations of two sum
    for (int i = 0; i < A.size(); ++i) {
      for (int j = 0; j < A.size(); ++j) {
        int twoSum = A.get(i) + A.get(j);
        for (int k = 0; k < A.size(); ++k) { // twoSum + element = t
          if (twoSum + A.get(k) == t) {
            return true;
          }
        }
      }
    }
    return false;
  }

  // hash-set
  public static boolean hasThreeSum(List<Integer> A, int t) {
    Set<Integer> set = new HashSet<>();
    for (int val : A) {
      set.add(val);
    }
    for (int val1 : A) {
      for (int val2 : A) {
        if (set.contains(t - val1 - val2)) {
          return true;
        }
      }
    }
    return false;
  }


  // cont'd above using binary search
  // O(N^2logN)
  public static boolean hasThreeSum(List<Integer> A, int t) {
    int n = A.size();
    Collections.sort(A); // O(NlogN)
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) { // O(logN!) = O(NlogN)
        // lo starts from j rather than j + 1
        if (binarySearch(A, j, n - 1, t - A.get(i) - A.get(j))) return true; // O(logN)
      }
    }
    return false;
  }

  // standard binary search
  private static boolean binarySearch(List<Integer> A, int lo, int hi, int t) {
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (A.get(mid) == t) return true;
      if (A.get(mid) > t) {
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    }
    return false;
  }



  // sort, using the idea of two pointers in Two Sum in sorted array
  public static boolean hasThreeSum(List<Integer> A, int t) {
    Collections.sort(A); // O(NlogN)
    for (int val : A) { // for each element O(N)
      int i = 0, j = A.size() - 1;
      while (i <= j) { // O(N)
        int sum = A.get(i) + A.get(j) + val;
        if (sum < t) {
          i += 1;
        } else if (sum > t) {
          j -= 1;
        } else {
          return true;
        }
      }
    }
    return false;
  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ThreeSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
