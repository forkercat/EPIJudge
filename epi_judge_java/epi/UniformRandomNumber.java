package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.RandomSequenceChecker;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class UniformRandomNumber {
  private static int zeroOneRandom() {
    Random gen = new Random();
    return gen.nextInt(2);
  }

  public static int uniformRandom(int lowerBound, int upperBound) {
    int n = upperBound - lowerBound + 1; // number of outcomes
    int result;
    do {
      result = 0;
      int numBit = (int) log2(n) + 1;
      for (int i = 0; i < numBit; ++i) {
      // for (int i = 0; (1 << i) < n; ++i) {   // (1 << i) <=> 2^i
        result = (result << 1) | zeroOneRandom();
      }
    } while (result >= n);
    return lowerBound + result;
  }

  // Solution
  public static int uniformRandom(int lowerBound, int upperBound) {
    int numberOfOutcomes = upperBound - lowerBound + 1;
    int result;

    do {
      result = 0;
      // 100    < number of outcomes
      // i = 2
      // [] [] [] []
      // 16 8  4  2  represents #outcomes, if 16 is not enough, go on adding to the result
      for (int i = 0; (1 << i) < numberOfOutcomes; ++i) {
        result = (result << 1) | zeroOneRandom(); // place the 0/1 digit
      }
    } while (result >= numberOfOutcomes); // since the result is in [0 ~ #outcome - 1]

    return result + lowerBound;
  }


  private static double log2(int x) {
    return Math.log(x) / Math.log(2);
  }

  private static boolean uniformRandomRunner(TimedExecutor executor,
                                             int lowerBound, int upperBound)
      throws Exception {
    List<Integer> results = new ArrayList<>();

    executor.run(() -> {
      for (int i = 0; i < 100000; ++i) {
        results.add(uniformRandom(lowerBound, upperBound));
      }
    });

    List<Integer> sequence = new ArrayList<>();
    for (Integer result : results) {
      sequence.add(result - lowerBound);
    }
    return RandomSequenceChecker.checkSequenceIsUniformlyRandom(
        sequence, upperBound - lowerBound + 1, 0.01);
  }

  @EpiTest(testDataFile = "uniform_random_number.tsv")
  public static void uniformRandomWrapper(TimedExecutor executor,
                                          int lowerBound, int upperBound)
      throws Exception {
    RandomSequenceChecker.runFuncWithRetries(
        () -> uniformRandomRunner(executor, lowerBound, upperBound));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "UniformRandomNumber.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
