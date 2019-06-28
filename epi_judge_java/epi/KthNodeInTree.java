package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class KthNodeInTree {
  public static class BinaryTreeNode<T> {
    public T data;
    public BinaryTreeNode<T> left, right;
    public int size;

    public BinaryTreeNode(T data, BinaryTreeNode<T> left,
                          BinaryTreeNode<T> right, int size) {
      this.data = data;
      this.left = left;
      this.right = right;
      this.size = size;
    }
  }

  //   1
  //  / \
  // 2   3

  public static BinaryTreeNode<Integer>
  findKthNodeBinaryTree(BinaryTreeNode<Integer> tree, int k) {
    if (tree == null) return null;

    int t = size(tree.left); // #nodes in the left subtree

    if (k <= t) {
      return findKthNodeBinaryTree(tree.left, k);
    } else if (k == t + 1) { // this is an invariant
      return tree;
    } else { // go right
      return findKthNodeBinaryTree(tree.right, k - t - 1);
    }
  }

  private static int size(BinaryTreeNode<Integer> tree) {
    if (tree == null) return 0;
    return tree.size;
  }



  public static BinaryTreeNode<Integer>
  convertToTreeWithSize(BinaryTree<Integer> original) {
    if (original == null)
      return null;
    BinaryTreeNode<Integer> left = convertToTreeWithSize(original.left);
    BinaryTreeNode<Integer> right = convertToTreeWithSize(original.right);
    int lSize = left == null ? 0 : left.size;
    int rSize = right == null ? 0 : right.size;
    return new BinaryTreeNode<>(original.data, left, right, 1 + lSize + rSize);
  }

  @EpiTest(testDataFile = "kth_node_in_tree.tsv")
  public static int findKthNodeBinaryTreeWrapper(TimedExecutor executor,
                                                 BinaryTree<Integer> tree,
                                                 int k) throws Exception {
    BinaryTreeNode<Integer> converted = convertToTreeWithSize(tree);

    BinaryTreeNode<Integer> result =
        executor.run(() -> findKthNodeBinaryTree(converted, k));

    if (result == null) {
      throw new TestFailure("Result can't be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthNodeInTree.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
