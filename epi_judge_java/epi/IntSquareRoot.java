package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int k) {
    int lo = 1, hi = k;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (mid > k / mid) {
        hi = mid - 1;
      } else {
        lo =  mid + 1;
      }
    }
    return lo - 1;
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
