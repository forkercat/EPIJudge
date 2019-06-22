package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveDivide {
  @EpiTest(testDataFile = "primitive_divide.tsv")
  public static int divide(int x, int y) {
    if (x < 0 || y <= 0) {
      throw new IllegalArgumentException();
    }
    // return bruteForce(x, y);
    return better(x, y);
  }


  public static int bruteForce(int x, int y) {
    int count = 0; // quotient
    while (x >= y) {
      x = x - y;
      ++count;
    }
    return count;
  }

  public static int better(int x, int y) {
    int count = 0; // quotient
    int k = 32;
    long yPower = (long) y << k;
    while (x >= y) {
      while (yPower > x) { // find the largest k
        yPower >>>= 1;
        --k;
      }
      count += (1 << k);
      x -= yPower;
    }
    return count;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveDivide.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
