package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")
  /*
  // [lo, hi]
  public static int searchFirstOfK(List<Integer> A, int k) {
    int lo = 0, hi = A.size() - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (A.get(mid) >= k) { // first occurrence
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    }

    // return
    if (lo != A.size() && A.get(lo) == k) {
      return lo;
    } else {
      return -1;
    }
  }
  */

  // [lo, hi)
  public static int searchFirstOfK(List<Integer> A, int k) {
    int lo = 0, hi = A.size();
    while (lo < hi) {
      int mid = lo + (hi - lo) / 2;
      if (A.get(mid) >= k) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }

    if (lo < A.size() && A.get(lo) == k) {
      return lo;
    } else {
      return -1;
    }
  }

  /*
  // [lo, hi]
  public static int searchFirstOfK(List<Integer> A, int k) {
    int lo = 0, hi = A.size() - 1, result = -1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (A.get(mid) >= k) {
        if (A.get(mid) == k) result = mid;
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    }
    return result;
  }

   */




  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
