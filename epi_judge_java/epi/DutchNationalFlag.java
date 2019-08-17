package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class DutchNationalFlag {
  public enum Color { RED, WHITE, BLUE }



  // brute-force

  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
    Color pivot = A.get(pivotIndex);
    // First pass: group elements smaller than pivot.
    for (int i = 0; i < A.size(); ++i) {
      // Look for a smaller element
      for (int j = i + 1; j < A.size(); ++j) {
        if (A.get(j).ordinal() < pivot.ordinal()) {
          Collections.swap(A, i, j);
          break;
        }
      }
    }
    // Second pass: group elements larger than pivot.
    for (int i = A.size()  - 1; i >= 0; --i) {
      // Stop when hitting an element smaller than the pivot.
      if (A.get(i).ordinal() < pivot.ordinal()) break;

      for (int j = i - 1; j >= 0; --j) {
        if (A.get(j).ordinal() > pivot.ordinal()) {
          Collections.swap(A, i, j);
          break;
        }
      }
    }
  }


  // O(N) - Two Passes
  /*
  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
    Color pivot = A.get(pivotIndex);
    // Note: pivotIndex may change, but pivot won't change anymore.
    // First pass
    int count = 0;
    for (int i = 0; i < A.size(); ++i) {
      if (A.get(i).ordinal() < pivot.ordinal()) {
        Collections.swap(A, i, count++);
      }
    }
    // Second pass - Move elements equal to pivot
    for (int i = count; i < A.size(); ++i) {
      if (A.get(i).ordinal() == pivot.ordinal()) {
        Collections.swap(A, i, count++);
      }
    }
  }
  */


  // O(N) - One Pass / Three Pointers
  /*
  public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
    Color pivot = A.get(pivotIndex);
    int smaller = 0, equal = 0, larger = A.size() - 1;
    while (equal <= larger) {
      // A.get(equal) is the incoming unclassified element
      if (A.get(equal).ordinal() < pivot.ordinal()) {
        Collections.swap(A, equal++, smaller++);
      } else if (A.get(equal).ordinal() > pivot.ordinal()) {
        Collections.swap(A, equal, larger--); // equal not changing since its element is not processed
      } else { // A.get(equal).ordinal() == pivot.ordinal()
        equal += 1;
      }
    }
  }
   */





  @EpiTest(testDataFile = "dutch_national_flag.tsv")
  public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                               List<Integer> A, int pivotIdx)
      throws Exception {
    List<Color> colors = new ArrayList<>();
    int[] count = new int[3];

    Color[] C = Color.values();
    for (int i = 0; i < A.size(); i++) {
      count[A.get(i)]++;
      colors.add(C[A.get(i)]);
    }

    Color pivot = colors.get(pivotIdx);
    executor.run(() -> dutchFlagPartition(pivotIdx, colors));

    int i = 0;
    while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
      count[colors.get(i).ordinal()]--;
      ++i;
    }

    if (i != colors.size()) {
      throw new TestFailure("Not partitioned after " + Integer.toString(i) +
                            "th element");
    } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
      throw new TestFailure("Some elements are missing from original array");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
