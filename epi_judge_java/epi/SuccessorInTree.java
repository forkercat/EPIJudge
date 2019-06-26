package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class SuccessorInTree {

  // No right child cases:
  // [O] indicates a successor
  //
  //    [O]   O
  //    /      \
  //   O        O    <--- p
  //
  //     O        [O]      O    O
  //      \       /       /      \
  //      [O]    O      [O]       O
  //      /       \     /          \
  //     O         O   O            O    <--- p
  //
  // If "/" exists, there is a successor.

  public static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
    BinaryTree<Integer> p = node;

    // has right child
    if (p.right != null) { // find the leftmost node
      p = p.right;
      while (p.left != null) p = p.left;
      return p;
    }

    // has no right child
    while (p.parent != null) {
      if (p.parent.left == p) { // parent's left child == p
        return p.parent;
      }
      p = p.parent;
    }
    return null;
  }




  @EpiTest(testDataFile = "successor_in_tree.tsv")
  public static int findSuccessorWrapper(TimedExecutor executor,
                                         BinaryTree<Integer> tree, int nodeIdx)
      throws Exception {
    BinaryTree<Integer> n = BinaryTreeUtils.mustFindNode(tree, nodeIdx);

    BinaryTree<Integer> result = executor.run(() -> findSuccessor(n));

    return result == null ? -1 : result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SuccessorInTree.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
