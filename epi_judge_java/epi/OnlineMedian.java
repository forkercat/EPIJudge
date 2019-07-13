package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class OnlineMedian {

  public static List<Double> onlineMedian(Iterator<Integer> sequence) {

    PriorityQueue<Integer> maxPQ = new PriorityQueue<>((n1, n2) -> (n2 - n1));
    PriorityQueue<Integer> minPQ = new PriorityQueue<>((n1, n2) -> (n1 - n2));

    List<Double> result = new ArrayList<>();

    while (sequence.hasNext()) {
      int num = sequence.next();

      // add num
      maxPQ.offer(num);
      // size check (max)
      if (maxPQ.size() - minPQ.size() > 1) {
        minPQ.add(maxPQ.poll());
      }
      // value check
      if (maxPQ.size() > 0 && minPQ.size() > 0 && maxPQ.peek() > minPQ.peek()) {
        minPQ.add(maxPQ.poll());
      }
      // size check (min)
      if (maxPQ.size() < minPQ.size()) {
        maxPQ.add(minPQ.poll());
      }

      // find median
      if (maxPQ.size() == 0 && minPQ.size() == 0) {
        throw new NoSuchElementException();
      }
      if (maxPQ.size() > minPQ.size()) { // odd
        result.add((double) maxPQ.peek());
      } else { // even
        result.add((maxPQ.peek() + minPQ.peek()) / 2.0);
      }
    }

    return result;
  }






  @EpiTest(testDataFile = "online_median.tsv")
  public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
    return onlineMedian(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "OnlineMedian.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
