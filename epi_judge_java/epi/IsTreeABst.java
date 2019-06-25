package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeABst {
  @EpiTest(testDataFile = "is_tree_a_bst.tsv")

  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    return isBSTHelper(tree, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  private static boolean isBSTHelper(BinaryTreeNode<Integer> x, long lower, long upper) {
    if (x == null) {
      return true;
    } else if (x.data < lower || x.data > upper) {
      return false;
    } else {
      return isBSTHelper(x.left, lower, x.data) && isBSTHelper(x.right, x.data, upper);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
