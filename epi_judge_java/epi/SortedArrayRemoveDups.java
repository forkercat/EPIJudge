package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.List;
public class SortedArrayRemoveDups {

  /** brute-force O(N^2) */
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

  /** better O(N) */
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


  /** Variant 1 */
  /*
  public static void main(String[] args) {
    List<Integer> L = new ArrayList<>();
    int[] nums = new int[] { 3, 4, 2, 1, 1, 9, 1, 2 };
    // result: 3, 4, 2, 9, 2
    for (int val : nums) L.add(val);
    System.out.println("count: " + removeOccurrence(L, 1));
    System.out.println("List: " + L);
  }
   */

  // O(N)
  public static int removeOccurrence(List<Integer> A, int key) {
    int i = 0, idx = 0;
    while (i < A.size()) {
      if (A.get(i).equals(key) == false) { // equal to the occurrence
        A.set(idx++, A.get(i));
      }
      i += 1;
    }
    return idx;
  }

  /** Variant 2 */
  /*
  public static void main(String[] args) {
    List<Integer> L = new ArrayList<>();
    int m = 1;
    int[] nums = new int[] { 1, 2, 2, 2, 2, 3, 4, 4, 5, 6, 6, 6 };
    // result:               1, 2, 2, 3, 4, 4, 5, 6, 6, 6  (m = 4)
    for (int val : nums) L.add(val);
    System.out.println("count: " + removeExtra(L, m)); // test (L, 1)
    System.out.println("List: " + L);
  }
   */

  // O(N) one-pass
  // index | 0  1  2  3  4  5  6  7  8  9  10  11
  // m = 4 | 1  2  2  2  2  3  4  4  5  6  6   6
  //         i  ----------> i
  //        idx ----------> idx
  //                 idx <---| (back to)
  //                    (m - 2)
  public static int removeExtra(List<Integer> A, int m) {
    int i = 0, idx = 0;
    while (i < A.size()) {
      int count = 0;
      int curr = A.get(i);
      // count the occurrence
      while (i < A.size() && A.get(i).equals(curr)) {
        count += 1;
        A.set(idx++, A.get(i++));
      }
      if (m >= 2 && count == m) { // min(2, m)
        idx -= (m - 2);
      }
    }
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
