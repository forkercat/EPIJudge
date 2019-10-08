package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class ReverseWords {

  public static void reverseWords(char[] input) {
    int n = input.length;
    // reverse all
    reverse(input, 0, n - 1);
    int i = 0;
    while (i < n) {
      int end = i;
      while (end < n && input[end] != ' ') {
        end += 1;
      } // end finally points to ' '
      reverse(input, i, end - 1);
      i = end + 1;
    }
  }

  private static void reverse(char[] charArr, int lo, int hi) {
    int n = hi - lo + 1;
    for (int i = 0; i < n / 2; ++i) {
      swap(charArr, lo + i, hi - i);
    }
  }

  private static void swap(char[] charArr, int i, int j) {
    char temp = charArr[i];
    charArr[i] = charArr[j];
    charArr[j] = temp;
  }





  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
