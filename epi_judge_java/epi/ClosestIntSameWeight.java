package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ClosestIntSameWeight {
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")

  public static long closestIntSameBitCount(long x) {
    return solution(x);
    // return myway(x);
  }

  public static long solution(long x) {
    if (x == 0 || ~x == 1) throw new IllegalArgumentException();

    return x;
  }

  // I found the rule
  public static long myway(long x) {
    if (x == 0 || ~x == 1) throw new IllegalArgumentException();
    long isolate;
    boolean isZero = (x & 1L) == 0;
    if (isZero) { // 0
      isolate = x & ~(x - 1); // rightmost 1
    } else { // 1
      isolate = (~x) & ~((~x) - 1); // rightmost 0
    }
    isolate >>>= 1L;

    if (isZero) return x - isolate;
    else return x + isolate;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
