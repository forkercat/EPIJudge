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

  // Solution
  /*
  private static long[] precomputed;
  public static long reverseBits(long x) {
    final int MASK_SIZE = 16;
    final int BIT_MASK = 0xFFFF;  // 16
    return precomputed[(int) (x & BIT_MASK)] << (3 * MASK_SIZE) |
            precomputed[(int) (x >>> MASK_SIZE) & BIT_MASK] << (2 * MASK_SIZE) |
            precomputed[(int) (x >>> MASK_SIZE * 2) & BIT_MASK] << (1 * MASK_SIZE) |
            precomputed[(int) (x >>> MASK_SIZE * 3) & BIT_MASK];
  }
  */

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
