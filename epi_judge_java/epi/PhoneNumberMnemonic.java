package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class PhoneNumberMnemonic {

  private static final String[] MAPPING = { "0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ" };

  @EpiTest(testDataFile = "phone_number_mnemonic.tsv")

  public static List<String> phoneMnemonic(String phoneNumber) {
    List<String> result = new ArrayList<>();
    phoneMnemonic(0, phoneNumber, new StringBuilder(), result);
    // phoneMnemonic(phoneNumber, new StringBuilder(), result);
    return result;
  }

  private static void phoneMnemonic(int pos, String phoneNumber, StringBuilder sb, List<String> result) {
    // base case
    if (pos == phoneNumber.length()) { // pos: 0 ~ len - 1
      result.add(sb.toString());
      return;
    }

    int num = phoneNumber.charAt(pos) - '0'; // convert from char to integer
    String option = MAPPING[num];

    for (int i = 0; i < option.length(); ++i) {
      char ch = option.charAt(i);
      sb.append(ch);
      phoneMnemonic(pos + 1, phoneNumber, sb, result);
      sb.setLength(sb.length() - 1); // remove ch
    }
  }

  /** Actually, we don't need pos parameter */
  private static void phoneMnemonic(String phoneNumber, StringBuilder sb, List<String> result) {
    int pos = sb.length();
    // base case
    if (pos == phoneNumber.length()) { // pos: 0 ~ len - 1
      result.add(sb.toString());
      return;
    }

    int num = phoneNumber.charAt(pos) - '0'; // convert from char to integer
    String option = MAPPING[num];

    for (int i = 0; i < option.length(); ++i) {
      char ch = option.charAt(i);
      sb.append(ch);
      phoneMnemonic(pos + 1, phoneNumber, sb, result);
      sb.setLength(sb.length() - 1); // remove ch
    }
  }




  @EpiTestComparator
  public static BiPredicate<List<String>, List<String>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
