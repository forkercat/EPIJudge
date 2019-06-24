package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class TreeWithParentInorder {
  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")


  // Traverse left, visit, traverse right
  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
    BinaryTree<Integer> prev = null, p = tree;
    List<Integer> result = new ArrayList<>();

    while (p != null) {
      BinaryTree<Integer> next;
      // The outer if controls the direction
      if (p.parent == prev) { // we came down from prev to p
        if (p.left != null) { // keep going left
          next = p.left;
        } else { // done with left
          result.add(p.data);
          next = (p.right != null) ? p.right : p.parent; // or prev
        }
      } else if (p.left == prev) { // done with left
        result.add(p.data);
        next = (p.right != null) ? p.right : p.parent;
      } else { // done with both children
        next = p.parent;
      }
      prev = p;
      p = next;
    }
    return result;
  }



  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
