package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {
  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {
    // In book (lookup table)
    return x;
  }

  public static long swapBits(long x, int i, int j) {
    if (((x >>> i) & 1) != ((x >>> j) & 1)) { // diff
      x ^= (1L << i) | (1L << j);
    }
    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
  /*
  // brute-force
  public static long reverseBits(long x) {
    for (int i = 0; i < 32; ++i) {
      int j = 64 - i - 1;
      x = swapBits(x, i, j);
    }
    return x;
  }
   */
}
