package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class Permutations {
  @EpiTest(testDataFile = "permutations.tsv")

  public static List<List<Integer>> permutations(List<Integer> A) {
    List<List<Integer>> result = new ArrayList<>();
    permutation(0, A, result); // depth = 0
    return result;
  }

  private static void permutation(int depth, List<Integer> A, List<List<Integer>> result) {
    // base case
    if (depth == A.size() - 1) {
      result.add(new ArrayList<>(A));
      return;
    }
    // enumeration
    for (int i = depth; i < A.size(); ++i) {
      Collections.swap(A, depth, i);
      permutation(depth + 1, A, result);
      Collections.swap(A, depth, i);
    }
  }


  // variant







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
            .runFromAnnotations(args, "Permutations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
