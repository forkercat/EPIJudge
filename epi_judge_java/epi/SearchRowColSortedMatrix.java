package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchRowColSortedMatrix {
  @EpiTest(testDataFile = "search_row_col_sorted_matrix.tsv")

  public static boolean matrixSearch(List<List<Integer>> A, int x) {
    int row = A.size(), col = A.get(0).size();
    int r = 0, c = col - 1;
    // search
    while (r < row && c >= 0) {
      int val = A.get(r).get(c);
      if (x == val) return true;
      if (x < val) { // not in that column
        c -= 1;
      } else { // not in that row
        r += 1;
      }
    }
    return false;
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchRowColSortedMatrix.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
