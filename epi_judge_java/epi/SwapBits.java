package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SwapBits {
  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {
    // Extract the i-th and j-th bits, and see if they differ
    if (((x >>> i) & 1) != ((x >>> j) & 1)) { // diff
      x ^= (1L << i);
      x ^= (1L << j);
      // or
      // x ^= (1L << i) | (1L << j);
    }
    return x;
  }

  private static void tobin(long x) {
    System.out.println(Long.toBinaryString(x));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }

  /*
  // brute-force
  public static long swapBits(long x, int i, int j) {
    if (i < 0 || j < 0 || i > 63 || j > 63) throw new IllegalArgumentException();

    long imask = 1L << i;
    long jmask = 1L << j;

    long ival = x & imask; // >0: 1, =0: 0
    long jval = x & jmask;
    // ^= toggle a bit
    if (jval > 0) x |= imask;  // |= turn on a bit
    else          x &= ~imask; // &= turn off a bit
    if (ival > 0) x |= jmask;
    else          x &= ~jmask;

    return x;
  }
   */
}
