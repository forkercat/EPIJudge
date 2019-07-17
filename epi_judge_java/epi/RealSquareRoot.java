package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RealSquareRoot {
  @EpiTest(testDataFile = "real_square_root.tsv")

  public static double squareRoot(double x) {
    double eps = 0.000001;
    double lo, hi;
    if (x < 1.0) {
      lo = x; hi = 1.0;
    } else {
      lo = 1.0; hi = x;
    }
    // Keeps searching as long as lo != hi, within tolerance
    while (Math.abs(lo - hi) > eps) {
      double mid = lo + 0.5 * (hi - lo);
      double midSquared = mid * mid;
      if (midSquared >= x) {
        hi = mid;
      } else {
        lo = mid;
      }
    }
    return lo;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RealSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
