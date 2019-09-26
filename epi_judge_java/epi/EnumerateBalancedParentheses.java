package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class EnumerateBalancedParentheses {
  @EpiTest(testDataFile = "enumerate_balanced_parentheses.tsv")


  public static List<String> generateBalancedParentheses(int numPairs) {
    List<String> result = new ArrayList<>();
    parenthesis(numPairs, numPairs, new StringBuilder(), result);
    return result;
  }

  private static void parenthesis(int leftNum, int rightNum, StringBuilder sb, List<String> result) {
    if (rightNum == 0) {
      result.add(sb.toString());
      return;
    }

    if (leftNum > 0) { // able to add "("
      sb.append("(");
      parenthesis(leftNum - 1, rightNum, sb, result);
      sb.setLength(sb.length() - 1);
    }

    if (leftNum < rightNum) { // able to add ")"
      sb.append(")");
      parenthesis(leftNum, rightNum - 1, sb, result);
      sb.setLength(sb.length() - 1);
    }
  }




  @EpiTestComparator
  public static BiPredicate<List<String>, List<String>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EnumerateBalancedParentheses.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
