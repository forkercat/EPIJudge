package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.List;
public class SortedArrayRemoveDups {

  // brute-force O(N^2)
  // Returns the number of valid entries after deletion.
  /*
  public static int deleteDuplicates(List<Integer> A) {
    //  0  1  2  3  4  5
    //  1  2  2  3  3  4
    //  i  i+1
    int i = 0;
    while (i < A.size() - 1) { // skip the last
      if (A.get(i).equals(A.get(i + 1))) { // equal
        A.remove(i + 1);
      } else { // not equal
        i += 1; // move forward
      }
    }
    return A.size();
  }
   */


  // O(N)
  // 1  2  2  3  3  4  5
  // 1  2  3  4  5  idx
  public static int deleteDuplicates(List<Integer> A) {
    int i = 0, idx = 0;
    while (i < A.size()) {
      // not the last index && A[i] == A[i + 1]
      /*
      if (i < A.size() - 1 && A.get(i).equals(A.get(i + 1))) {
        i += 1;
      } else {
        A.set(idx++, A.get(i++));
      }
      */
      // alternatively
      if (i == A.size() - 1 || !A.get(i).equals(A.get(i + 1))) {
        A.set(idx++, A.get(i));
      }
      i += 1;
    }
    // remove (optional)
    return idx;
  }





  @EpiTest(testDataFile = "sorted_array_remove_dups.tsv")
  public static List<Integer> deleteDuplicatesWrapper(TimedExecutor executor,
                                                      List<Integer> A)
      throws Exception {
    int end = executor.run(() -> deleteDuplicates(A));
    return A.subList(0, end);
  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArrayRemoveDups.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
