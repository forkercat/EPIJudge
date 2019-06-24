package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsTreeBalanced {

  private static Map<BinaryTreeNode<Integer>, Integer> heightMap = new HashMap<>();

  @EpiTest(testDataFile = "is_tree_balanced.tsv")
  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    return isBalancedRec(tree);
    // return isBalancedSolution(tree);
  }


  /**
   * Brute-Force
   */
  public static boolean isBalancedRec(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return true;
    }
    if (!isBalancedRec(tree.left)) {
      return false;
    }
    if (!isBalancedRec(tree.right)) {
      return false;
    }
    int diff = Math.abs(height(tree.left) - height(tree.right));
    return (diff <= 1);
  }

  // okay
  private static int height(BinaryTreeNode<Integer> x) {
    if (x == null) return -1;  // should be -1
    if (heightMap.containsKey(x)) {
      return heightMap.get(x);
    }

    int ht = Math.max(height(x.left), height(x.right)) + 1;
    heightMap.put(x, ht);

    return ht;
  }

  /**
   * Solution
   */
  private static class BalanceStatusWithHeight {
    public boolean balanced;
    public int height;
    public BalanceStatusWithHeight(boolean balanced, int height) {
      this.balanced = balanced;
      this.height = height;
    }
  }

  public static boolean isBalancedSolution(BinaryTreeNode<Integer> tree) {
    return checkBalanced(tree).balanced;
  }

  private static BalanceStatusWithHeight checkBalanced(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return new BalanceStatusWithHeight(true, -1); // Base case
    }
    BalanceStatusWithHeight leftResult = checkBalanced(tree.left);
    if (!leftResult.balanced) {
      return leftResult; // left tree is not balanced
    }
    BalanceStatusWithHeight rightResult = checkBalanced(tree.right);
    if (!rightResult.balanced) {
      return rightResult; // right tree is not balanced
    }

    boolean isBalanced = Math.abs(leftResult.height - rightResult.height) <= 1;
    int height = Math.max(leftResult.height, rightResult.height) + 1;
    return new BalanceStatusWithHeight(isBalanced, height);
  }





  public static void main(String[] args) {


    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
