package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SwapBits {
  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {
    if (i < 0 || j < 0 || i > 63 || j > 63) throw new IllegalArgumentException();

    long imask = 1L << i; tobin(imask);
    long jmask = 1L << j; tobin(jmask);

    long ival = x & imask; // >0: 1, =0: 0
    long jval = x & jmask;

    if (jval > 0) x |= imask;
    else          x &= ~imask;
    if (ival > 0) x |= jmask;
    else          x &= ~jmask;

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
}
