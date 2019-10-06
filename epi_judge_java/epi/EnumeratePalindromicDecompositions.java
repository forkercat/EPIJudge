package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
public class EnumeratePalindromicDecompositions {
  @EpiTest(testDataFile = "enumerate_palindromic_decompositions.tsv")

  /**
   * Backtracking
   * Think about the time complexity (Use recurrence)
   */
  public static List<List<String>> palindromeDecompositions(String input) {
    List<List<String>> result = new ArrayList<>();
    generatePalindrome(0, input, new ArrayList<>(), result);
    return result;
  }

  /**
   * helper: generatePalindrome
   */
  private static void generatePalindrome(int offset, String input, List<String> build, List<List<String>> result) {
    // base case
    if (offset >= input.length()) {
      result.add(new ArrayList<>(build)); // copy a new array list to result
      return;
    }
    /** Index warning */
    for (int preLen = 1; offset + preLen <= input.length(); ++preLen) { // offset + preLen is at most the length of the string
      String sub = input.substring(offset, offset + preLen);
      if (isPalindromic(sub)) {
        build.add(sub);
        generatePalindrome(offset + preLen, input, build, result);
        build.remove(build.size() - 1); // remove the last
      }
    }
  }

  /**
   * isPalindromic
   */
  private static boolean isPalindromic(String input) {
    for (int i = 0; i < input.length() / 2; ++i) { // right-leaning
      if (input.charAt(i) != input.charAt(input.length() - i - 1)) {
        return false;
      }
    }
    return true;
  }



  @EpiTestComparator
  public static BiPredicate<List<List<String>>, List<List<String>>> comp =
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
            .runFromAnnotations(args, "EnumeratePalindromicDecompositions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
