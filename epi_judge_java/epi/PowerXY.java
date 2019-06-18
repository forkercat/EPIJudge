package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PowerXY {
  @EpiTest(testDataFile = "power_x_y.tsv")

  /** Non-recurisve version */
  public static double power(double x, int y) { // take time to ponder
    double result = 1.0;
    long power = y;
    if (y < 0) {
      power = -power;
      x = 1.0 / x;
    }
    // Another way to think about the while is that x^1010 = x^1000 * x^0010
    while (power != 0) { // consider 0, 1, 2, 3, 4, 5
      if ((power & 1) != 0) { // odd
        result *= x; // only happens once or twice
      }
      x *= x; // use x to store the product
      power >>>= 1;  // divide by 2
    }
    return result;
  }

  /*
  // recursive
  public static double power(double x, int y) {
    // return x^y // y = -2, -1, 0, 1, 2
    double ret = helper2(x, Math.abs(y));
    if (y < 0) ret = 1 / ret; // negative number
    return ret;
  }
   */

  // better style
  private static double helper2(double x, int y) {
    if (y == 0) return 1;
    if (y == 1) return x;
    double res = helper2(x, y / 2);
    if ((y & 1) == 0) {
      return res * res;
    } else {
      return res * res * x;
    }
  }

  // y >= 0 | O(N) = O(log(y))
  private static double helper1(double x, int y) {
    if (y == 0) return 1;
    if (y == 1) return x;
    int y0 = (int) Math.floor(y / 2.0);
    int y1 = (int) Math.ceil(y / 2.0);
    // Alternative:
    // int y0 =  y / 2;
    // int y1 = (y + 1) / 2;
    // 4 => 2, 2 | 5 => 3,2
    double result = helper1(x, y0);
    if (y0 == y1) return result * result; // even
    else return result * result * x; // odd
  }


  /*
  // brute-force
  public static double power(double x, int y) {
    // return x^y
    // y = -2, -1, 0, 1, 2
    double ret = 1;
    for (int i = 0; i < Math.abs(y); ++i) {
      ret *= x;
    }
    if (y < 0) ret = 1 / ret; // negative number
    return ret;
  }
   */

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerXY.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }


}
