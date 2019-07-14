package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;
public class KLargestInHeap {
  // @EpiTest(testDataFile = "k_largest_in_heap.tsv")
  // Brute-Force - O(KlogN)
  /*
  public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {
    PriorityQueue<Integer> maxPQ = new PriorityQueue<>((n1, n2) -> (n2 - n1));
    List<Integer> result = new ArrayList<>();
    for (int val : A) {
      maxPQ.add(val);
    }
    while (k > 0) {
      result.add(maxPQ.poll());
      k -= 1;
    }
    return result;
  }
   */

  private static class Entry {
    Integer index;
    Integer value;
    Entry(Integer i, Integer v) {
      index = i;
      value = v;
    }
  }

  @EpiTest(testDataFile = "k_largest_in_heap.tsv")
  public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {
    if (k <= 0) {
      return Collections.emptyList();
    }
    PriorityQueue<Entry> candidateMaxPQ = new PriorityQueue<>((o1, o2) -> o2.value - o1.value);
    candidateMaxPQ.add(new Entry(0, A.get(0)));
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < k; ++i) {
      Integer candidateIdx = candidateMaxPQ.peek().index;
      result.add(candidateMaxPQ.poll().value);
      Integer leftChildIdx = candidateIdx * 2 + 1;
      if (leftChildIdx < A.size()) {
        candidateMaxPQ.add(new Entry(leftChildIdx, A.get(leftChildIdx)));
      }
      Integer rightChildIdx = candidateIdx * 2 + 2;
      if (rightChildIdx < A.size()) {
        candidateMaxPQ.add(new Entry(rightChildIdx, A.get(rightChildIdx)));
      }
    }
    return result;
  }


  @EpiTestComparator
  public static BiPredicate<List<Integer>, List<Integer>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestInHeap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
