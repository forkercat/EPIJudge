package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchForMinMaxInArray {
  @EpiUserType(ctorParams = {Integer.class, Integer.class})

  public static class MinMax {
    public Integer smallest;
    public Integer largest;

    public MinMax(Integer smallest, Integer largest) {
      this.smallest = smallest;
      this.largest = largest;
    }

    private static MinMax minMax(Integer a, Integer b) {
      return Integer.compare(b, a) < 0 ? new MinMax(b, a) : new MinMax(a, b);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      MinMax minMax = (MinMax)o;

      if (!smallest.equals(minMax.smallest)) {
        return false;
      }
      return largest.equals(minMax.largest);
    }

    @Override
    public String toString() {
      return "min: " + smallest + ", max: " + largest;
    }
  }

  @EpiTest(testDataFile = "search_for_min_max_in_array.tsv")


  public static MinMax findMinMax(List<Integer> A) {
    int minVal = Integer.MAX_VALUE;
    int maxVal = Integer.MIN_VALUE;
    //  0  1  2  3  4  5  6
    //  3  4  5  6  7  8  9
    for (int i = 0; i + 1 < A.size(); i += 2) {
      if (A.get(i) < A.get(i + 1)) {
        minVal = Math.min(minVal, A.get(i));
        maxVal = Math.max(maxVal, A.get(i + 1));
      } else {
        minVal = Math.min(minVal, A.get(i + 1));
        maxVal = Math.max(maxVal, A.get(i));
      }
    }

    if ((A.size() & 1) == 1) {
      minVal = Math.min(minVal, A.get(A.size() - 1));
      maxVal = Math.max(maxVal, A.get(A.size() - 1));
    }

    return new MinMax(minVal, maxVal);
  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchForMinMaxInArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
