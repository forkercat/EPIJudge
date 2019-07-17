package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchShiftedSortedArray {
  @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")

  // brute-force
  // public static int searchSmallest(List<Integer> A) {
  //   for (int i = 0; i < A.size() - 1; ++i) {
  //     if (A.get(i) > A.get(i + 1)) {
  //       return i + 1;
  //     }
  //   }
  //   return 0;
  // }

  // Note: Our goal is to find the minimum value.
  //          [3]
  //  0  1  2  3  4  5  6
  //  7  9  1  2  3  4  5
  //        ^
  //          [3]
  //  0  1  2  3  4  5  6
  //  5  7  9  1  2  3  4
  //           1 < 4         => go left
  // [0]
  //  0  1  2  3  4  5  6
  //  1  2  3  4  5  7  9
  //           4 < 9         => go left
  //                   [6]
  //  0  1  2  3  4  5  6
  //  2  3  4  5  7  9  1
  //           5 > 1         => go right
  //
  public static int searchSmallest(List<Integer> A) {
    int n = A.size();
    int lo = 0, hi = n - 1;

    while (lo < hi) { // variant if you want to stop at lo == hi
      int mid = lo + (hi - lo) / 2;
      if (A.get(mid) < A.get(n - 1)) {
        hi = mid; // left (variant)
      } else {
        lo = mid + 1;
      }
    }
    return lo;
  }

  // based on the above offset, search the value val.
  public static int binarySearchByOffset(List<Integer> A, int val, int offset) {
    int lo = 0, hi = A.size() - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (A.get(mid + offset) >= val) {
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    }
    return lo + offset;
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
