package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")


  /*
  public static List<Integer> multiply(List<Integer> nums1, List<Integer> nums2) {
    // check null
    if (nums1 == null || nums1.size() == 0 || nums2 == null || nums2.size() == 0) {
      throw new IllegalArgumentException();
    }

    int n1 = nums1.size(), n2 = nums2.size();

    // negative cases
    int sign = (nums1.get(0) * nums2.get(0) > 0) ? +1 : -1;
    nums1.set(0, Math.abs(nums1.get(0)));
    nums2.set(0, Math.abs(nums2.get(0)));

    // determine the size (calculate the first three numbers)
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < n1; ++i) {
      result.add(0);
    }
    int carry = 0;
    for (int i = n1 - 1; i >= 0; --i) {
      int product = nums1.get(i) * nums2.get(0);
      result.set(i, product % 10);
      carry = product / 10;
    }
    if (carry > 0) {
      result.add(0, carry);
    }
    for (int i = 0; i < n2 - 1; ++i) { // add "0"
      result.add(0);
    }

    // multiply the rest
    for (int i = n2 - 1; i >= 1; --i) {
      int c = 0;
      for (int j = n1 - 1; j >= 0; --j) {
        int product = nums1.get(j) * nums2.get(i);
        result.set(result.size() - (n2 - i) - ((n1 - 1) - j), product % 10);
        c = product / 10;
      }
      int offset = 0;
      while (c > 0) {
        int sum = result.get(result.size() - (n2 - i) - (n1 - 1) - offset) + c;
        result.set(result.size() - (n2 - i) - (n1 - 1) - offset, sum % 10);
        c = sum / 10;
        offset += 1;
      }
    }

    return result;
  }
  */

  // Consider Cases:
  // 123 x 100 = 12300 (012300)
  // 123 x 0 = 0 (0000)
  // 123 x 1 = 123 (0123)
  public static List<Integer> multiply(List<Integer> nums1, List<Integer> nums2) {
    int n1 = nums1.size(), n2 = nums2.size();

    final int sign = nums1.get(0) < 0 ^ nums2.get(0) < 0 ? -1 : 1;
    nums1.set(0, Math.abs(nums1.get(0)));
    nums2.set(0, Math.abs(nums2.get(0)));
    List<Integer> result = new ArrayList<>(Collections.nCopies(n1 + n2, 0));

    for (int i = n1 - 1; i >= 0; --i) {
      for (int j = n2 - 1; j >= 0; --j) {
        int index = i + j + 1;
        int sum = result.get(index) + nums1.get(i) * nums2.get(j);
        result.set(index, sum % 10);
        result.set(index - 1, result.get(index - 1) + sum / 10); // carry
      }
    }

    // Remove the leading zeroes.
    int numZero = 0;

    while (numZero < result.size() && result.get(numZero) == 0) {
      numZero += 1;
    }

    // removing
    int rsize = result.size(); // It is very important because result is changing
    for (int i = 0; i < rsize - 1 && i < numZero; ++i) {
      result.remove(0);
    }

    // Set sign
    result.set(0, result.get(0) * sign);

    return result;
  }





  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}















