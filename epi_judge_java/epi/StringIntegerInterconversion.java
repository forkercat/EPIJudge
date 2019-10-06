package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  /*
  // not good
  public static String intToString(int x) {
    StringBuilder sb = new StringBuilder();
    if (x < 0) { // negative number
      sb.append("-");
    }
    x = Math.abs(x);

    // number of digits
    int y = x;
    int numDigit = 0;
    while (y > 0) {
      numDigit += 1;
      y /= 10;
    } // can use Math.log
    int numMask = (int) Math.pow(10, numDigit - 1);

    // conversion
    while (numMask > 0) {
      int digit = x / numMask;
      sb.append(digit);
      x = x % numMask; // update
      numMask /= 10;
    }
    return sb.toString();
  }
   */


  /**
   * intToString
   */
  // do-while
  /*
  public static String intToString(int x) {
    boolean isNegative = (x < 0);
    x = Math.abs(x); // error when x = -2147483648

    StringBuilder sb = new StringBuilder();

    do {
      int digit = x % 10;
      sb.append(digit);
      x /= 10;
    } while (x > 0);

    return sb.append(isNegative ? "-" : "").reverse().toString();
  }
   */

  // while + handle 0-case separately
  /*
  public static String intToString(int x) {
    boolean isNegative = (x < 0);
    x = Math.abs(x);

    StringBuilder sb = new StringBuilder();
    if (x == 0) sb.append(0);
    while (x > 0) {
      int digit = x % 10;
      sb.append(digit);
      x /= 10;
    }

    return sb.append(isNegative ? "-" : "").reverse().toString();
  }
   */

  public static String intToString(int x) {
    boolean isNegative = (x < 0);

    StringBuilder sb = new StringBuilder();

    do {
      int digit = Math.abs((x % 10));
      sb.append(digit);
      x /= 10;
    } while (x != 0);

    return sb.append(isNegative ? "-" : "").reverse().toString();
  }



  /**
   * stringToInt
   */
  public static int stringToInt(String s) {
    // trim whitespaces
    s.trim();
    // sign
    int sign = s.startsWith("-") ? -1 : +1;
    s = s.replace("-", ""); // remove the "-" (immutable!)
    // conversion
    int num = 0;
    for (int i = 0; i < s.length(); ++i) {
      int digit = s.charAt(i) - '0';
      num = num * 10 + digit;
    }
    return sign * num;
  }



  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (!intToString(x).equals(s)) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
