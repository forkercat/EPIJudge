package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveMultiply {
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
    // return myway_fail(x, y);
    // return bruteForce(x, y);
    return solution(x, y);
  }

  /** O(n) */
  public static long solution(long x, long y) {
    // x = 1101 (13)
    // y = 1001 (9)
    // Iterate through x = 1101, we have y*2^3 + y*2^2 + 0 + y*2^0 = 117
    long sum = 0;
    while (x != 0) { // Examines each bit of x
      if ((x % 1) == 1) { // add
        sum = add(sum, y);
      }
      x >>>= 1;
      y <<= 1; // 2^k * y
    }
    return sum;
  }

  public static long add(long x, long y) {
    while (y != 0) {
      long c = x & y;
      x = x ^ y;
      y = c << 1;
    }
    return x;
  }

  /**
   *  Brute-force, O(2^n)
   */
  public static long bruteforce(long x, long y) {
    long result = 0;

    if (x < y) { // small optimization
      long tmp = x;
      x = y;
      y = tmp;
    }

    // O(y)
    for (long i = 0; i < y; i = add(i, 1L)) { // add x to result for y times
      result += x;
    }
    return result;
  }





  public static long myway_fail(long x, long y) { // x = 0 or y = 0
    if (x == 0 || y == 0) return 0;

    // we want x > y => better performance
    // if (x < y) { // exchange
    //   long tmp = x;
    //   x = y;
    //   y = tmp;
    // }

    // 0001 - shiftCount = 0
    // 0010 - shiftCount = 1
    long originalX = x;
    long shiftCount = 0;
    while (y != 0) {
      // 0001
      // 0011
      // 0101
      // 0100
      while ((y & 1L) == 0L) { // 0010 => count = 2
        shiftCount += 1;
        y >>>= 1L;
      }
      x <<= shiftCount; // product
      y &= ~1L; // turn off the y's rightmost bit for next move
    }

    if ((y & 1L) == 1) { // odd
      x = add(x, originalX);
    }

    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
