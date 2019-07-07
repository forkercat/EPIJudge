package epi;

import epi.test_framework.*;

public class LowestCommonAncestor {

  private static BinaryTreeNode<Integer> result = null;

  public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node1,
                                            BinaryTreeNode<Integer> node2) {
    result = null;
    if (node1 == node2) {
      return node1;
    }
    findNode(tree, node1, node2);
    return result;
  }


  private static boolean findNode(BinaryTreeNode<Integer> root,
                                  BinaryTreeNode<Integer> node1,
                                  BinaryTreeNode<Integer> node2) {
    if (root == null) {
      return false;
    }

    int left = findNode(root.left, node1, node2) ? 1 : 0;
    int right = findNode(root.right, node1, node2) ? 1 : 0;
    int mid = (root == node1 || root == node2) ? 1 : 0;

    if (left + right + mid >= 2) {
      result = root;
    }

    return (left + right + mid) > 0;
  }



  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> LCA(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
