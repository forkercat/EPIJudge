package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class IntAsArrayIncrement {

  @EpiTest(testDataFile = "int_as_array_increment.tsv")

  // Test Cases:
  // []
  // [1]
  // [9, 9]
  // [1, 2, 3]
  public static List<Integer> plusOne(List<Integer> A) {
    int n = A.size();
    int carry = 1;
    for (int i = n - 1; i >= 0; --i) {
      int curr = A.get(i);
      A.set(i, (curr + carry) % 10);
      carry = (curr + carry) / 10;
    }
    // if carry > 0, expend the array
    if (carry > 0) {
      A.add(0);
      A.set(0, 1); // set the first index as 1
    }
    return A;
  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
