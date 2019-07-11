package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class SortIncreasingDecreasingArray {

  private enum SubType { INCR, DECR }

  @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")
  public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {
    if (A.size() <= 1) {
      return A;
    }
    // Decomposes A into a set of sorted arrays
    List<List<Integer>> subLists = new ArrayList<>();
    // Assume no adjacent elements are equal
    SubType type = A.get(0) < A.get(1) ? SubType.INCR : SubType.DECR;
    int i = 0;
    while (i < A.size()) {
      List<Integer> subList = new ArrayList<>();
      int j = i;
      while (j < A.size() - 1) {
        subList.add(A.get(j));
        // meet endpoint in increasing sublist
        if (type == SubType.INCR && A.get(j) > A.get(j + 1)) {
          break;
        }
        // meet endpoint in decreasing sublist
        if (type == SubType.DECR && A.get(j) < A.get(j + 1)) {
          break;
        }
        j += 1;
      }
      if (j == A.size() - 1) {
        subList.add(A.get(j));
      }
      // set subList
      if (type == SubType.DECR) Collections.reverse(subList);
      subLists.add(subList);
      // update
      type = (type == SubType.INCR) ? SubType.DECR : SubType.INCR;
      i = j + 1;
    }
    return SortedArraysMerge.mergeSortedArrays(subLists);
  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortIncreasingDecreasingArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
