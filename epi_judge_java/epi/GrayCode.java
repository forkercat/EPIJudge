package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class GrayCode {

  /**
   * Backtracking
   */
  /*
  public static List<Integer> grayCode(int numBits) {
    List<Integer> result = new ArrayList<>(List.of(0));
    directedGrayCode(numBits, new HashSet<>(List.of(0)), result);
    return result;
  }

  private static boolean directedGrayCode(int numBits, Set<Integer> history, List<Integer> result) {
    // if (result.size() == (int) Math.pow(2, numBits))
    if (result.size() == (1 << numBits)) { // 1 << numBits equals 2^numBits
      // compare the first element and the last element
      return differsByOneBit(result.get(0), result.get(result.size() - 1));
    }
    for (int i = 0; i < numBits; ++i) {
      int prevCode = result.get(result.size() - 1);
      int candCode = prevCode ^ (1 << i); // xor 001, 010, 100
      if (!history.contains(candCode)) { // candCode not in history
        history.add(candCode);
        result.add(candCode);
        if (directedGrayCode(numBits, history, result)) {
          return true; // found
        }
        history.remove(candCode);
        result.remove(result.size() - 1);
      }
    }
    return false;
  }

   */


  public static List<Integer> grayCode(int numBits) {
    List<Integer> result = new ArrayList<>(List.of(0));
    for (int i = 0; i < numBits; ++i) {
      List<Integer> res1 = result;
      List<Integer> res2 = new ArrayList<>(res1);
      Collections.reverse(res2);
      // prepend 1 to res2
      int prependVal = (1 << i); // if i = 1, we have 10
      for (int j = 0; j < res2.size(); ++j) {
        res2.set(j, prependVal + res2.get(j));
      }
      // combine
      res1.addAll(res2);
    }
    return result;
  }



  private static boolean differsByOneBit(int x, int y) {
    int xor = x ^ y;
    // x == y: xor == 0
    // diff bit > 1: (xor & (xor - 1)) != 0
    return xor != 0 && (xor & (xor - 1)) == 0;
  }

  @EpiTest(testDataFile = "gray_code.tsv")
  public static void grayCodeWrapper(TimedExecutor executor, int numBits)
      throws Exception {
    List<Integer> result = executor.run(() -> grayCode(numBits));

    int expectedSize = (1 << numBits);
    if (result.size() != expectedSize) {
      throw new TestFailure("Length mismatch: expected " +
                            String.valueOf(expectedSize) + ", got " +
                            String.valueOf(result.size()));
    }
    for (int i = 1; i < result.size(); i++)
      if (!differsByOneBit(result.get(i - 1), result.get(i))) {
        if (result.get(i - 1).equals(result.get(i))) {
          throw new TestFailure("Two adjacent entries are equal");
        } else {
          throw new TestFailure(
              "Two adjacent entries differ by more than 1 bit");
        }
      }

    Set<Integer> uniq = new HashSet<>(result);
    if (uniq.size() != result.size()) {
      throw new TestFailure("Not all entries are distinct: found " +
                            String.valueOf(result.size() - uniq.size()) +
                            " duplicates");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "GrayCode.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
