package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class Hanoi {

  private static final int NUM_PEGS = 3;

  public static List<List<Integer>> computeTowerHanoi(int numRings) {
    List<List<Integer>> result = new ArrayList<>();
    /** Recursion */
    // move(result, numRings, 0, 1, 2);
    /** Iteration */
    hanoiIteration(result, numRings);
    return result;
  }

  /**
   * Recursion
   */
  private static void move(List<List<Integer>> result, int n, int from, int by, int to) {
    if (n == 1) { // base case
      moveOne(result, from, to);
      return;
    }
    // move the top n-1 plates (from -> by, by to)
    move(result, n - 1, from, to, by);
    // move the one left
    moveOne(result, from, to);
    // move the top n-1 plates (by -> to, by from)
    move(result, n - 1, by, from, to);
  }

  private static void moveOne(List<List<Integer>> result, int from, int to) {
    List<Integer> list = new ArrayList<>();
    list.add(from); list.add(to);
    result.add(list);
  }

  /**
   * Iteration
   */
  private static void hanoiIteration(List<List<Integer>> result, int numRings) {
    Stack<Integer> nStack = new Stack<>();
    Stack<Integer> fromStack = new Stack<>();
    Stack<Integer> byStack = new Stack<>();
    Stack<Integer> toStack = new Stack<>();
    Stack<String> stat = new Stack<>();
    // Init
    nStack.push(numRings); fromStack.push(0); byStack.push(1); toStack.push(2); stat.push("1begin");
    while (nStack.size() > 0) {
      int n = nStack.pop(), from = fromStack.pop(), by = byStack.pop(), to = toStack.pop();
      String s = stat.pop();
      if (s.equals("1begin")) {
        if (n == 1) {
          moveOne(result, from, to);
          continue;
        }
        // save current status
        nStack.push(n); fromStack.push(from); byStack.push(by); toStack.push(to);
        stat.push("2topDone");
        // push new
        nStack.push(n - 1); fromStack.push(from); byStack.push(to); toStack.push(by);
        stat.push("1begin");
      } else if (s.equals("2topDone")) {
        // move One
        moveOne(result, from, to);
        // save current status
        nStack.push(n); fromStack.push(from); byStack.push(by); toStack.push(to);
        stat.push("3finish");
        // push new
        nStack.push(n - 1); fromStack.push(by); byStack.push(from); toStack.push(to);
        stat.push("1begin");
      } else { // 3finish

      }
    }
  }





  @EpiTest(testDataFile = "hanoi.tsv")
  public static void computeTowerHanoiWrapper(TimedExecutor executor,
                                              int numRings) throws Exception {
    List<Deque<Integer>> pegs = new ArrayList<>();
    for (int i = 0; i < NUM_PEGS; i++) {
      pegs.add(new LinkedList<>());
    }
    for (int i = numRings; i >= 1; --i) {
      pegs.get(0).addFirst(i);
    }

    List<List<Integer>> result =
        executor.run(() -> computeTowerHanoi(numRings));

    for (List<Integer> operation : result) {
      int fromPeg = operation.get(0);
      int toPeg = operation.get(1);
      if (!pegs.get(toPeg).isEmpty() &&
          pegs.get(fromPeg).getFirst() >= pegs.get(toPeg).getFirst()) {
        throw new TestFailure("Illegal move from " +
                              String.valueOf(pegs.get(fromPeg).getFirst()) +
                              " to " +
                              String.valueOf(pegs.get(toPeg).getFirst()));
      }
      pegs.get(toPeg).addFirst(pegs.get(fromPeg).removeFirst());
    }

    List<Deque<Integer>> expectedPegs1 = new ArrayList<>();
    for (int i = 0; i < NUM_PEGS; i++) {
      expectedPegs1.add(new LinkedList<Integer>());
    }
    for (int i = numRings; i >= 1; --i) {
      expectedPegs1.get(1).addFirst(i);
    }

    List<Deque<Integer>> expectedPegs2 = new ArrayList<>();
    for (int i = 0; i < NUM_PEGS; i++) {
      expectedPegs2.add(new LinkedList<Integer>());
    }
    for (int i = numRings; i >= 1; --i) {
      expectedPegs2.get(2).addFirst(i);
    }
    if (!pegs.equals(expectedPegs1) && !pegs.equals(expectedPegs2)) {
      throw new TestFailure("Pegs doesn't place in the right configuration");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Hanoi.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
