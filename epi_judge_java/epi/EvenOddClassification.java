/**
 * @author Junhao Wang
 * @date 08/15/2019
 */
package epi;

import java.util.Collections;
import java.util.List;

public class EvenOddClassification {

  // Time: O(N)
  // Space: O(1)

  // even  unclassified  odd
  // nextOdd          nextEven
  public static void evenOdd(List<Integer> A) {
    int nextOdd = 0, nextEven = A.size() - 1;
    while (nextOdd < nextEven) {
      while (A.get(nextOdd) % 2 == 0) nextOdd += 1;
      while (A.get(nextEven) % 2 != 0) nextEven -= 1;
      if (nextOdd < nextEven) {
        Collections.swap(A, nextOdd, nextEven);
      }
    }
  }


}
