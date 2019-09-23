package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class MinimumPointsCoveringIntervals {
  @EpiUserType(ctorParams = {int.class, int.class})

  public static class Interval {
    public int left, right;

    public Interval(int l, int r) {
      this.left = l;
      this.right = r;
    }
  }

  @EpiTest(testDataFile = "minimum_points_covering_intervals.tsv")

  public static Integer findMinimumVisits(List<Interval> intervals) {
    // sort
    Collections.sort(intervals, (o1, o2) -> (o1.right - o2.right));
    int numVisit = 0, lastVisitTime = Integer.MIN_VALUE;
    for (int i = 0; i < intervals.size(); ++i) {
      // check if it is covered
      if (intervals.get(i).left <= lastVisitTime) {
        continue;  // cover
      } else {
        numVisit += 1;  // not cover
        lastVisitTime = intervals.get(i).right;
      }
    }
    return numVisit;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumPointsCoveringIntervals.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
