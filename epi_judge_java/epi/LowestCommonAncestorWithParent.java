package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestorWithParent {

  public static BinaryTree<Integer> LCA(BinaryTree<Integer> node1,
                                        BinaryTree<Integer> node2) {
    int d1 = getDepth(node1);
    int d2 = getDepth(node2);
    // make node1 > node2, and move node1 first
    if (d1 < d2) {
      int depthTemp = d1;
      BinaryTree<Integer> nodeTemp = node1;
      node1 = node2;
      d1 = d2;
      node2 = nodeTemp;
      d2 = depthTemp;
    }
    // ascend d1 to d2
    while (d1 > d2) {
      node1 = node1.parent;
      d1 -= 1;
    }
    // by now d1 == d2
    while (node1 != node2) {
      node1 = node1.parent;
      node2 = node2.parent;
    }
    return node1;
  }

  // number of nodes
  private static int getDepth(BinaryTree<Integer> node) {
    int depth = 0;
    while (node != null) {
      node = node.parent;
      depth += 1;
    }
    return depth;
  }





  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> LCA(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
