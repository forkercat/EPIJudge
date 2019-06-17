package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseDigits {
  @EpiTest(testDataFile = "reverse_digits.tsv")

  public static long reverse(int x) {
    // return reverseItr(x);
    return reverseRec(x);
  }

  // iterative
  public static long reverseItr(int x) {
    long sum = 0;
    while (x != 0) { // 123 => 321
      sum = sum * 10 + (x % 10);
      x /= 10; // x = 123 / 10
    }
    return sum;
  }

  // recursion
  // 123
  public static long reverseRec(int x) {
    long p = Math.abs(x);
    long res = helper(p);
    if (x > 0) return res;
    else return -res;
  }
  // watch out the type
  // if we use int
  // when x = 1799113645
  // ten = 1000000000 => ten x 5 will overflow
  public static long helper(long x) {
    if (x < 10) return x;
    long ten = (long) Math.pow(10, (long) Math.log10(x));
    return x % 10 * ten + helper(x / 10);
  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
