package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
public class MinimumWaitingTime {
  @EpiTest(testDataFile = "minimum_waiting_time.tsv")

  public static int minimumTotalWaitingTime(List<Integer> serviceTimes) {
    // sorting
    Collections.sort(serviceTimes);
    int waitTime = 0, total = 0;
    // 1    2    3    5
    //      1    1+2  1+2+3   // each
    //      1    4    10      // total
    //      total: 1 + 3 + 6
    // start with the second task, its waiting time is the length of the first task
    for (int i = 1; i < serviceTimes.size(); ++i) {
      waitTime += serviceTimes.get(i - 1); // previous wait time + last wait time
      total += waitTime;
    }
    return total;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumWaitingTime.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
