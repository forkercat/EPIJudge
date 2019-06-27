package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.function.BiPredicate;
public class KLargestValuesInBst {
  @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")

  // public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
  //   List<Integer> result = new ArrayList<>();
  //   if (tree == null || k <= 0) {
  //     return result;
  //   }
  //   helper(tree, k, result);
  //   return result;
  // }

  // private static void helper(BstNode<Integer> x, int k, List<Integer> result) {
  //   if (x == null) return;
  //   // k nodes are added to result list
  //   if (result.size() == k) return;
  //
  //   // go to right
  //   if (x.right == null) { // locate at the rightmost node
  //     result.add(x.data);
  //   } else { // go to right
  //     helper(x.right, k, result);
  //     // current node
  //     if (result.size() == k) return;
  //     result.add(x.data);
  //   }
  //
  //   // go to left
  //   if (x.left != null) {
  //     helper(x.left, k, result);
  //   }
  // }

  // private static void helper(BstNode<Integer> x, int k, List<Integer> result) {
  //   if (x == null || result.size() == k) {
  //     return;
  //   }
  //
  //   helper(x.right, k, result);
  //   if (result.size() < k) {
  //     result.add(x.data);
  //     helper(x.left, k, result);
  //   }
  // }


  /**
   * Iteration - Reverse Inorder Traversal
   */
  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {
    List<Integer> result = new ArrayList<>();
    if (tree == null || k <= 0) {
      return result;
    }

    Stack<BstNode<Integer>> stack = new Stack<>();
    BstNode<Integer> p = tree;
    while (p != null || !stack.isEmpty()) {
      // go to rightmost node
      while (p != null) {
        stack.push(p);
        p = p.right;
      } // now p -> null
      p = stack.pop(); // rightmost node


      if (result.size() < k) {
        result.add(p.data); // add
        if (result.size() == k) break;
      }

      p = p.left;
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
            .runFromAnnotations(args, "KLargestValuesInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
