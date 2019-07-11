package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SortedArraysMerge {

  static class PQEntry {
    int index;
    int data;

    PQEntry(int i, int d) {
      index = i;
      data = d;
    }
  }

  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
  public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {

    List<Integer> result = new ArrayList<>();
    Comparator<PQEntry> comp = (PQEntry p1, PQEntry p2) -> (p1.data - p2.data);
    PriorityQueue<PQEntry> pq = new PriorityQueue<>(comp);
    int[] indexArray = new int[sortedArrays.size()];


    // offer the first elements
    for (int i = 0; i < sortedArrays.size(); ++i) {
      int index = indexArray[i];
      if (index < sortedArrays.get(i).size()) { // valid
        PQEntry entry = new PQEntry(i, sortedArrays.get(i).get(index));
        pq.offer(entry);
      }
    }

    // pq
    while (pq.size() > 0) {
      PQEntry entry = pq.remove();
      result.add(entry.data);
      indexArray[entry.index] += 1;  // update
      if (indexArray[entry.index] < sortedArrays.get(entry.index).size()) { // offer the next element
        int newVal = sortedArrays.get(entry.index).get(indexArray[entry.index]);
        PQEntry newEntry = new PQEntry(entry.index, newVal);
        pq.offer(newEntry);
      }
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
