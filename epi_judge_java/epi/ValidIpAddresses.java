package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class ValidIpAddresses {
  @EpiTest(testDataFile = "valid_ip_addresses.tsv")

  /*
  public static List<String> getValidIpAddress(String s) {
    List<String> result = new ArrayList<>();
    List<Integer> build = new ArrayList<>();
    backtracking(0, s, build, result);
    return result;
  }

  private static void backtracking(int d, String s, List<Integer> build, List<String> result) {
    // base case
    if (build.size() == 4) {
      if (d == s.length()) { // add to result
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < build.size(); ++i) {
          sb.append(build.get(i));
          if (i < build.size() - 1)  sb.append(".");
        }
        result.add(sb.toString());
      }
      return;
    }
    // Consider 3 numbers (check the first number)
    if (d >= s.length()) return;
    int count = ((s.charAt(d) - '0') == 0) ? 1 : 3;
    int sum = 0;
    for (int i = 0; i < count; ++i) { // adding two numbers
      if (d + i >= s.length()) return;
      sum = sum * 10 + (s.charAt(d + i) - '0');
      if (isValid(sum) == false) return;
      build.add(sum);
      backtracking(d + i + 1, s, build, result);
      build.remove(build.size() - 1);
    }
  }

  private static boolean isValid(int num) {
    return num >= 0 && num <= 255;
  }
  */


  // Iterative (String)
  // 0 1 2 3 4 5 6 7
  // 1 9 2 1 6 8 1 1
  public static List<String> getValidIpAddress(String s) {
    List<String> result = new ArrayList<>();
    int n = s.length();
    for (int i = 1; i <= 3 && i <= n; ++i) {
      String first = s.substring(0, i);
      if (isValidStr(first)) {
        for (int j = 1; j <= 3 && i + j <= n; ++j) {
          String second = s.substring(i, i + j);
          if (isValidStr(second)) {
            for (int k = 1; k <= 3 && i + j + k <= n; ++k) {
              String third = s.substring(i + j, i + j + k);
              String fourth = s.substring(i + j + k, n);
              if (isValidStr(third) && isValidStr(fourth)) {
                result.add(first +  "." + second + "." + third + "." + fourth);
              }
            }
          }
        }
      }
    }
    return result;
  }

  private static boolean isValidStr(String s) {
    if (s.length() == 0 || s.length() >= 4)  return false;
    if (s.startsWith("0") && s.length() > 1) return false;
    int val = Integer.parseInt(s);
    return val >= 0 && val <= 255;
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
            .runFromAnnotations(args, "ValidIpAddresses.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
