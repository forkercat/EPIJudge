package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MajorityElement {

  /**
   * HashMap
   */
  public static String majoritySearch(Iterator<String> stream) {
    // map to count
    Map<String, Integer> map = new HashMap<>();
    while (stream.hasNext()) {
      String s = stream.next();
      int count = map.getOrDefault(s, 0);
      // if (stream.size() > n / 2) { return s; }
      map.put(s, count + 1);
    }
    // find the max
    int maxCount = Integer.MIN_VALUE;
    String maxString = null;
    for (String s : map.keySet()) {
      int count = map.get(s);
      if (count > maxCount) {
        maxCount = count;
        maxString = s;
      }
    }
    return maxString;
  }


  /**
   * Candidate Method
   */
  /*
  public static String majoritySearch(Iterator<String> stream) {
    String candidate = null;
    int count = 0;

    while (stream.hasNext()) {
      String s = stream.next();
      if (count == 0) {
        candidate = s;
        count += 1;
      } else if (s.equals(candidate)) {
        count += 1;
      } else { // s != candidate
        count -= 1;
      }
    }

    // If the problem does not assume the majority exists, we should do a double pas to check if it is the majority element.

    return candidate;
  }

   */




  @EpiTest(testDataFile = "majority_element.tsv")
  public static String majoritySearchWrapper(List<String> stream) {
    return majoritySearch(stream.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MajorityElement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
