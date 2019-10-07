package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RomanToInteger {
  @EpiTest(testDataFile = "roman_to_integer.tsv")

  public static int romanToInteger(String s) {
    int n = s.length();
    int result = 0;
    for (int i = 0; i < n; ++i) {
      int curr = getVal(s.charAt(i));
      int next = (i + 1 < n) ? getVal(s.charAt(i + 1)) : Integer.MIN_VALUE;
      if (curr < next) {
        result -= curr;
      } else {
        result += curr;
      }
    }
    return result;
  }

  private static int getVal(char ch) {
    if (ch == 'I') return 1;
    if (ch == 'V') return 5;
    if (ch == 'X') return 10;
    if (ch == 'L') return 50;
    if (ch == 'C') return 100;
    if (ch == 'D') return 500;
    if (ch == 'M') return 1000;
    throw new IllegalArgumentException("Unsupported Character");
  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RomanToInteger.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
