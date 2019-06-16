package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class Parity {
  @EpiTest(testDataFile = "parity.tsv")
  public static short parity(long x) {
    x ^= x >>> 32;
    x ^= x >>> 16;
    x ^= x >>> 8;
    x ^= x >>> 4;
    x ^= x >>> 2;
    x ^= x >>> 1;
    return (short) (x & 0x1);  // or (x & 1)
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }

  /*
  // brute-force
  public static short parity(long x) {
    short result = 0;
    while (x != 0) {
      result ^= (x & 1); // accumulative count: 0 <-> 1
      x >>>= 1;
    }
    return result;
  }
   */

  /*
  // drop rightmost "1"
  public static short parity(long x) {
    short result = 0;
    while (x != 0) {
      result ^= 1;   // count
      x &= (x - 1);  // remove rightmost "1" bit
    }
    return result;
  }
   */
}
