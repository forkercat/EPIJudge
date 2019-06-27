package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.HashMap;

public class SumRootToLeaf {
  // @EpiTest(testDataFile = "sum_root_to_leaf.tsv")

  // public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
  //   if (tree == null) return 0;
  //   return sum(tree, 0);
  // }
  //
  // private static int sum(BinaryTreeNode<Integer> node, int result) {
  //   if (node == null) return 0;
  //
  //   result = (result << 1) + node.data;
  //
  //   if (node.left == null && node.right == null) {
  //     return result;
  //   }
  //
  //   int x = sum(node.left, result);
  //   int y = sum(node.right, result);
  //
  //   return x + y;
  // }


  /** Brute-force */
  private static HashMap<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> map;
  private static ArrayList<BinaryTreeNode<Integer>> leaves;

  @EpiTest(testDataFile = "sum_root_to_leaf.tsv")
  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
    map = new HashMap<>();
    leaves = new ArrayList<>();
    findParentsAndLeaves(tree, null); // root's parent is null

    int sum = 0; // sum up
    for (BinaryTreeNode<Integer> leaf : leaves) {
      int val = 0;
      BinaryTreeNode<Integer> p = leaf;
      int k;
      for (k = 0, p = leaf; p != null; ++k, p = map.get(p)) {
        val = val + (p.data << k);
      }
      sum += val;
    }
    return sum;
  }

  // Preorder traversal
  private static void findParentsAndLeaves(BinaryTreeNode<Integer> tree,  BinaryTreeNode<Integer> parent) {
    if (tree == null) return;
    // store leaves
    if (tree.left == null && tree.right == null) leaves.add(tree);
    // map child-parent
    map.put(tree, parent);

    findParentsAndLeaves(tree.left, tree);
    findParentsAndLeaves(tree.right, tree);
  }





  public static void main(String[] args) {

    // BinaryTreeNode<Integer> b1 = new BinaryTreeNode<>(1);
    // BinaryTreeNode<Integer> b2 = new BinaryTreeNode<>(0);
    // BinaryTreeNode<Integer> b3 = new BinaryTreeNode<>(1);
    // BinaryTreeNode<Integer> b4 = new BinaryTreeNode<>(1);
    // BinaryTreeNode<Integer> b5 = new BinaryTreeNode<>(1);
    // b1.left = b2; b1.right = b3;
    // b2.left = b4; b2.right = b5;
    // System.out.println(b1.hashCode()); // 1802598046
    // System.out.println(b2.hashCode()); // 659748578
    // System.out.println(b3.hashCode()); // 240650537
    // System.out.println(b4.hashCode()); // 483422889
    // System.out.println(b5.hashCode()); // 2088051243
    /*
    // BinaryTreeNode<Integer> b1 = new BinaryTreeNode<>(1);
    // BinaryTreeNode<Integer> b2 = new BinaryTreeNode<>(2);
    // BinaryTreeNode<Integer> b3 = new BinaryTreeNode<>(3);
    // BinaryTreeNode<Integer> b4 = new BinaryTreeNode<>(4);
    // BinaryTreeNode<Integer> b5 = new BinaryTreeNode<>(5);
    b1.left = b2; b1.right = b3;
    b2.left = b4; b2.right = b5;
    System.out.println(b1.hashCode());
    System.out.println(b2.hashCode());
    System.out.println(b3.hashCode());
    System.out.println(b4.hashCode());
    System.out.println(b5.hashCode());


    System.out.println("Sum = " + sumRootToLeaf(b1));

    // findParentsAndLeaves(b1, null);
    System.out.println(map.get(b4) == b2);

    // System.out.println(leaves.get(3) == b3);

    */
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SumRootToLeaf.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
