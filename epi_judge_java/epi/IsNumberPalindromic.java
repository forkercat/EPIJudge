package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    // return myway(x);
    // return solution(x);
    return bruteforce(x);
  }

  public static boolean myway(int x) {
    if (x < 0) return false;
    long original = x;
    long reverse = 0;
    while (x != 0) {
      reverse = reverse * 10 + (x % 10);
      x /= 10;
    }
    return original == reverse;
  }

  public static boolean solution(int x) {
    if (x <= 0) return x == 0;
    final int numDigits = (int) (Math.floor(Math.log10(x))) + 1; // 123
    int msdMask = (int) Math.pow(10, numDigits - 1); // 100
    for (int i = 0; i < numDigits / 2; ++i) {
      int msd = (x / msdMask) % 10;
      int lsd = x % 10;
      if (msd != lsd) {
        return false;
      }
      x %= msdMask;   // remove msd
      x /= 10;        // remove lsd
      msdMask /= 100; // update msdMask
    }
    return true;
  }

  public static boolean bruteforce(int x) {
    if (x <= 0) return x == 0;
    String str = String.valueOf(x); // O(#digit)
    int n = str.length();
    for (int i = 0; i < str.length() / 2; ++i) {
      char msd = str.charAt(i);
      char lsd = str.charAt(n - 1 - i);
      if (msd != lsd) return false;
    }
    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
