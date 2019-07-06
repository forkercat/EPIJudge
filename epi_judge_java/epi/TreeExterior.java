package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;
public class TreeExterior {

  /** Solution */
  public static List<BinaryTreeNode<Integer>> exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
    List<BinaryTreeNode<Integer>> result = new ArrayList<>();
    if (tree == null) {
      return result;
    }
    result.add(tree);
    // 4 cases
    leftBoundary(tree.left, result);
    leaves(tree.left, result);
    leaves(tree.right, result);
    rightBoundary(tree.right, result);

    return result;
  }

  // left
  private static void leftBoundary(BinaryTreeNode<Integer> root, List<BinaryTreeNode<Integer>> result) {
    if (root == null || (root.left == null && root.right == null)) {
      return;
    }
    result.add(root);
    if (root.left != null) {
      leftBoundary(root.left, result);
    } else {
      leftBoundary(root.right, result);
    }
  }

  // right
  private static void rightBoundary(BinaryTreeNode<Integer> root, List<BinaryTreeNode<Integer>> result) {
    if (root == null || (root.left == null && root.right == null)) {
      return;
    }
    if (root.right != null) {
      rightBoundary(root.right, result);
    } else {
      rightBoundary(root.left, result);
    }
    result.add(root);
  }

  // leaves
  private static void leaves(BinaryTreeNode<Integer> root, List<BinaryTreeNode<Integer>> result) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      result.add(root);
      return;
    }
    leaves(root.left, result);
    leaves(root.right, result);
  }



  /**
   * Iteration Failed
   */
  // public static List<BinaryTreeNode<Integer>> exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
  //   List<BinaryTreeNode<Integer>> result = new ArrayList<>();
  //   if (tree == null) {
  //     return result;
  //   }
  //   // root
  //   result.add(tree);
  //
  //   BinaryTreeNode<Integer> p = tree.left;
  //   // left
  //   if (p != null) {
  //     while (p.left != null || p.right != null) {
  //       while (p.left != null) { // go to the leftmost node
  //         result.add(p);
  //         p = p.left;
  //       }
  //       result.add(p);
  //       // check right child
  //       if (p.right != null) {
  //         p = p.right;
  //         System.out.println(" ++"  + p.data);
  //       }
  //     }
  //   }
  //
  //   System.out.println(p.data);
  //   // System.out.println(p);
  //   // for (int i = 0; i < result.size(); ++i) {
  //   //   System.out.print(result.get(i).data + "  ");
  //   // }
  //   // System.out.println();
  //
  //
  //   // leaves
  //   int lastIdx = result.size() - 1;
  //   if (tree.left != null) {
  //     result.remove(lastIdx);
  //   }
  //   preorder(tree, result);
  //   // int lastIdx = result.size() - 1;
  //   // if (tree.right != null) {
  //   //   result.remove(lastIdx); // remove the "rightmost" node
  //   // }
  //
  //   // right (backward)
  //   LinkedList<BinaryTreeNode<Integer>> rightList = new LinkedList<>();
  //   p = tree.right;
  //   if (p != null) {
  //     while (p.left != null || p.right != null) {
  //       while (p.right != null) {
  //         rightList.addFirst(p);
  //         p = p.right;
  //       }
  //       // check left child
  //       if (p.left != null) {
  //         p = p.left;
  //       }
  //     }
  //   }
  //
  //   // concatenate
  //   result.addAll(rightList);
  //
  //   return result;
  // }
  //
  //
  // private static void preorder(BinaryTreeNode<Integer> root, List<BinaryTreeNode<Integer>> result) {
  //   if (root == null) {
  //     return;
  //   }
  //
  //   if (root.left == null && root.right == null) {
  //     int lastIdx = result.size() - 1;
  //     if (root != result.get(lastIdx)) { // not the "leftmost" node
  //       result.add(root);
  //     }
  //   }
  //
  //   preorder(root.left, result);
  //   preorder(root.right, result);
  // }



  /** Recursion Failed */
  // public static List<BinaryTreeNode<Integer>> exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
  //   List<BinaryTreeNode<Integer>> result = new ArrayList<>();
  //   if (tree == null) {
  //     return result;
  //   }
  //   exteriorHelper(tree, true, false, result);
  //   return result;
  // }
  //
  // private static void exteriorHelper(BinaryTreeNode<Integer> root, boolean leftBoundary, boolean rightBoundary, List<BinaryTreeNode<Integer>> result) {
  //   if (root == null) {
  //     return;
  //   }
  //
  //   // meet leaf
  //   if (root.left == null && root.right == null) {
  //     result.add(root);
  //     return;
  //   }
  //
  //   // left
  //   if (leftBoundary == true) {
  //     result.add(root);
  //   }
  //   exteriorHelper(root.left, leftBoundary, false, result);
  //
  //   // mid
  //   if (root == result.get(0) || rightBoundary == true) {
  //     exteriorHelper(root.right, false, true, result);
  //   } else {
  //     exteriorHelper(root.right, false, false, result);
  //   }
  //
  //   // right
  //   if (rightBoundary == true && root != result.get(0)) {
  //     result.add(root);
  //   }
  // }







  private static List<Integer> createOutputList(List<BinaryTreeNode<Integer>> L)
      throws TestFailure {
    if (L.contains(null)) {
      throw new TestFailure("Resulting list contains null");
    }
    List<Integer> output = new ArrayList<>();
    for (BinaryTreeNode<Integer> l : L) {
      output.add(l.data);
    }
    return output;
  }

  @EpiTest(testDataFile = "tree_exterior.tsv")
  public static List<Integer>
  exteriorBinaryTreeWrapper(TimedExecutor executor,
                            BinaryTreeNode<Integer> tree) throws Exception {
    List<BinaryTreeNode<Integer>> result =
        executor.run(() -> exteriorBinaryTree(tree));

    return createOutputList(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeExterior.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
