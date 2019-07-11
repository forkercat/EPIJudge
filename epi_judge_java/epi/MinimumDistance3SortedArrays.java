package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MinimumDistance3SortedArrays {

  public static class ArrayData implements Comparable<ArrayData> {
    public int val;
    public int idx;

    public ArrayData(int idx, int val) {
      this.val = val;
      this.idx = idx;
    }

    @Override
    public int compareTo(ArrayData o) {
      int result = Integer.compare(val, o.val);
      if (result == 0) {
        result = Integer.compare(idx, o.idx);
      }
      return result;
    }
  }

  @EpiTest(testDataFile = "minimum_distance_3_sorted_arrays.tsv")

  public static int findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {
    int k = sortedArrays.size();
    System.out.println(k);
    int[] index = new int[k];
    int minResult = Integer.MAX_VALUE;
    boolean isFinished = false;
    while (isFinished == false) {
      int maxIdx = -1, minIdx = -1;
      int maxVal = Integer.MIN_VALUE, minVal = Integer.MAX_VALUE;
      for (int i = 0; i < k; ++i) {
        int currIndex = index[i];
        if (currIndex >= sortedArrays.get(i).size()) {
          isFinished = true;
          break;
        }
        int val = sortedArrays.get(i).get(currIndex);
        if (val < minVal) {
          minVal = val;
          minIdx = i;
        }
        if (val > maxVal) {
          maxVal = val;
          maxIdx = i;
        }
      }
      if (maxIdx != -1 && minIdx != -1) {
        minResult = Math.min(minResult, maxVal - minVal);
        index[minIdx] += 1;
      }
    }

    return minResult;
  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumDistance3SortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
