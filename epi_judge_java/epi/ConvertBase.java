package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ConvertBase {
  @EpiTest(testDataFile = "convert_base.tsv")

  public static String convertBase(String numAsString, int b1, int b2) {
    // negative handler
    boolean isNegative =  numAsString.startsWith("-");
    if (isNegative) {
      numAsString = numAsString.substring(1);
    }

    int decimal = getDecimalByBase(numAsString, b1);
    String result = getStringFromDecimalByBase(decimal, b2);
    return isNegative ? "-" + result : result;
  }

  /**
   * String -> Decimal
   */
  private static int getDecimalByBase(String s, int base) {
    int result = 0;
    for (char ch : s.toCharArray()) {
      int val = getVal(ch);
      result = result * base + val;
    }
    return result;
  }

  /**
   * Decimal -> String
   */
  private static String getStringFromDecimalByBase(int decimal, int base) {
    StringBuilder sb = new StringBuilder();
    do {
      sb.append(getChar(decimal % base));
      decimal /= base;
    } while (decimal != 0);
    return sb.reverse().toString();
  }

  private static int getVal(char ch) {
    if (Character.isDigit(ch)) {
      return ch - '0';
    }
    if (ch == 'A') return 10;
    if (ch == 'B') return 11;
    if (ch == 'C') return 12;
    if (ch == 'D') return 13;
    if (ch == 'E') return 14;
    if (ch == 'F') return 15;
    throw new IllegalArgumentException("Unsupported Character");
  }

  private static char getChar(int val) {
    if (val >= 0 && val <= 9) {
      return (char) ('0' + val);
    }
    if (val == 10) return 'A';
    if (val == 11) return 'B';
    if (val == 12) return 'C';
    if (val == 13) return 'D';
    if (val == 14) return 'E';
    if (val == 15) return 'F';
    throw new IllegalArgumentException("Unsupported Value");
  }

  public static void main(String[] args) {
    System.exit(
            GenericTest
                    .runFromAnnotations(args, "ConvertBase.java",
                            new Object() {
                            }.getClass().getEnclosingClass())
                    .ordinal());
  }
}
