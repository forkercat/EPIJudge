package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ClosestIntSameWeight {
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")

  public static long closestIntSameBitCount(long x) {
    // return solution(x);
    // return myway(x);
    // return review(x);
    return reviewO1(x);
  }

  // O(n)
  public static long solution(long x) {
    if (x == 0 || ~x == 1) throw new IllegalArgumentException();
    final int NUM_UNSIGNED_BITS = 63;
    // x is assumed to be non-negative so we know the leading bit is 0.
    // We restrict to our attention to 63 LSBs.
    for (int i = 0; i < NUM_UNSIGNED_BITS - 1; ++i) { // 0 ~ 61 (62 is for i + 1)
      long b1 = ((x >>> i) & 1);
      long b2 = ((x >>> (i + 1)) & 1);
      if (b1 != b2) { // swap
        x ^= (1L << i) | (1L << (i + 1)); // swap bit-i and bit-(i + 1), just toggle
        return x;
      }
    }
    throw new IllegalArgumentException("All bits are 0 or 1");
  }

  // I found the rule - O(1)
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

    // if (isZero) return x - isolate; // using count
    // else return x + isolate;
    return x ^= (isolate) | (isolate << 1L);
  }


  // review
  public static long review(long x) {
    int n = 64;
    for (int i = 0; i < n - 1; ++i) {
      int j = i + 1;
      long p = (x >> i) & 1;
      long q = (x >> j) & 1;
      if (p != q) {
        // diff and swap
        x ^= (1L << i) | (1L << j);
        break;
      }
    }
    return x;
  }

  // O(1)
  public static long reviewO1(long x) {
    long lsd = (x & 1);
    long rightmostDigit;
    if (lsd == 0) { // find the rightmost "1", eg. 111111100000000
      rightmostDigit = x & ~(x - 1);
    } else {                              // 00000000111111111
      rightmostDigit = (~x) & ~((~x) - 1);  // flip from 0 to 1 first
    }
    // swap(rightmostDigit, rightmostDigit >> 1)
    x ^= (rightmostDigit) | (rightmostDigit >> 1);
    return x;
  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
