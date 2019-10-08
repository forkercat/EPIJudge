package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LookAndSay {
  @EpiTest(testDataFile = "look_and_say.tsv")

  public static String lookAndSay(int n) {
    String prev = "1";
    for (int i = 1; i < n; ++i) {
      prev = lookSay(prev);
    }
    return prev;
  }

  private static String lookSay(String s) {
    StringBuilder sb = new StringBuilder();
    // go through the string
    int i = 0;
    while (i < s.length()) {
      int num = s.charAt(i) - '0';
      int count = 0;
      while (i < s.length() && s.charAt(i) - '0' == num) {
        count += 1;
        i += 1;
      }
      sb.append(count); sb.append(num);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LookAndSay.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
