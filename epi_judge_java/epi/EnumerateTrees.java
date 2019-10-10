package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class EnumerateTrees {

  /**
   * Solution
   * Idea: Form the subproblems according to subtrees' size
   */
  /*
  public static List<BinaryTreeNode<Integer>> generateAllBinaryTrees(int numNodes) {
    List<BinaryTreeNode<Integer>> result = new ArrayList<>();
    if (numNodes == 0) {
      result.add(null);
    }
    for (int numLeft = 0; numLeft < numNodes; ++numLeft) {
      int numRight = numNodes - 1 - numLeft; // root = 1
      List<BinaryTreeNode<Integer>> leftSubtrees = generateAllBinaryTrees(numLeft);
      List<BinaryTreeNode<Integer>> rightSubtrees = generateAllBinaryTrees(numRight);
      for (BinaryTreeNode<Integer> left : leftSubtrees) {
        for (BinaryTreeNode<Integer> right : rightSubtrees) {
          result.add(new BinaryTreeNode<>(0, left, right));
        }
      }
    }
    return result;
  }
  */

  /**
   * Optimized
   */
  private static Map<Integer, List<BinaryTreeNode<Integer>>> map = new HashMap<>();
  public static List<BinaryTreeNode<Integer>> generateAllBinaryTrees(int numNodes) {
    List<BinaryTreeNode<Integer>> result = new ArrayList<>();
    if (numNodes == 0) {
      result.add(null);
    }
    for (int numLeft = 0; numLeft < numNodes; ++numLeft) {
      int numRight = numNodes - 1 - numLeft; // root = 1
      if (!map.containsKey(numLeft)) {
        map.put(numLeft, generateAllBinaryTrees(numLeft));
      }
      if (!map.containsKey(numRight)) {
        map.put(numRight, generateAllBinaryTrees(numRight));
      }
      List<BinaryTreeNode<Integer>> leftSubtrees = map.get(numLeft);
      List<BinaryTreeNode<Integer>> rightSubtrees = map.get(numRight);
      for (BinaryTreeNode<Integer> left : leftSubtrees) {
        for (BinaryTreeNode<Integer> right : rightSubtrees) {
          result.add(new BinaryTreeNode<>(0, left, right));
        }
      }
    }
    return result;
  }



  public static List<Integer> serializeStructure(BinaryTreeNode<Integer> tree) {
    List<Integer> result = new ArrayList<>();
    Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
    stack.push(tree);
    while (!stack.empty()) {
      BinaryTreeNode<Integer> p = stack.pop();
      result.add(p == null ? 0 : 1);
      if (p != null) {
        stack.push(p.left);
        stack.push(p.right);
      }
    }
    return result;
  }

  @EpiTest(testDataFile = "enumerate_trees.tsv")
  public static List<List<Integer>>
  generateAllBinaryTreesWrapper(TimedExecutor executor, int numNodes)
      throws Exception {
    List<BinaryTreeNode<Integer>> result =
        executor.run(() -> generateAllBinaryTrees(numNodes));

    List<List<Integer>> serialized = new ArrayList<>();
    for (BinaryTreeNode<Integer> x : result) {
      serialized.add(serializeStructure(x));
    }
    serialized.sort(new LexicographicalListComparator<>());
    return serialized;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EnumerateTrees.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
