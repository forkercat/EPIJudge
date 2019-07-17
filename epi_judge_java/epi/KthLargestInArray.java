package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.Random;

public class KthLargestInArray {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(A, 1) returns 3, findKthLargest(A, 2) returns 2,
  // findKthLargest(A, 3) returns 1, and findKthLargest(A, 4) returns -1.
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")



  // Brute-force
  // public static int findKthLargest(int k, List<Integer> A) {
  //   Collections.sort(A, Comparator.reverseOrder());
  //   return A.get(k - 1);
  // }

  // Better
  public static int findKthLargest(int k, List<Integer> A) {
    int n = A.size();
    return quickSelect(A, n - k, 0, n - 1);
  }

  private static int quickSelect(List<Integer> A, int k, int lo, int hi) {
    if (lo == hi) {
      return A.get(lo);
    }
    Random rand = new Random();
    int randIdx = lo + rand.nextInt(hi - lo + 1);   // 1 2 3 4   lo = 1, hi = 4, hi - lo + 1 = 4
    swap(A, lo, randIdx);
    int p = lo, i = lo, j = hi + 1; // off by 1

    while (true) {
      while (A.get(++i) < A.get(p)) {
        if (i == hi) break;
      }
      while (A.get(--j) > A.get(p)) {
        if (j == lo) break;
      }
      if (i >= j) break;
      swap(A, i, j);
    }
    swap(A, p, j);

    if (j == k) {
      return A.get(k);
    } else if (k < j) { // left
      return quickSelect(A, k, lo, j - 1);
    } else { // right
      return quickSelect(A, k, j + 1, hi);
    }
  }


  private static void swap(List<Integer> A, int i, int j) {
    int temp = A.get(i);
    A.set(i, A.get(j));
    A.set(j, temp);
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
