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
      if (prev == p.parent) { // we came down from prev to p
        if (p.left != null) { // keep going left
          next = p.left;
        } else { // done with left
          result.add(p.data);
          next = (p.right != null) ? p.right : p.parent; // or prev
        }
      } else if (prev == p.left) { // done with left
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

  // Visit, traverse left, traverse right
  public static List<Integer> preorderTraversal(BinaryTree<Integer> tree) {
    BinaryTree<Integer> prev = null, p = tree;
    List<Integer> result = new ArrayList<>();

    while (p != null) {
      BinaryTree<Integer> next;
      if (prev == p.parent) { // come down from prev to p
        // visit
        result.add(p.data);
        if (p.left != null) {
          next = p.left;
        } else if (p.right != null) {
          next = p.right;
        } else { // done with children
          next = p.parent;
        }
      } else if (prev == p.left) { // come up from left
        next = (p.right != null) ? p.right : p.parent;
      } else { // come up from right
        next = p.parent;
      }
      prev = p;
      p = next;
    }
    return result;
  }


  // Traverse left, right, visit
  public static List<Integer> postorderTraversal(BinaryTree<Integer> tree) {
    BinaryTree<Integer> prev = null, p = tree;
    List<Integer> result = new ArrayList<>();

    while (p != null) {
      BinaryTree<Integer> next;
      if (prev == p.parent) { // come down from prev to p (4 cases)
        //   O    O      O   O   <--- p
        //       / \    /     \
        //      O   O  O       O
        if (p.left != null) { // go to left
          next = p.left;
        } else if (p.right != null) { // go to right
          next = p.right;
        } else { // both are null
          result.add(p.data);
          next = p.parent;
        }
      } else if (prev == p.left) { // come up from left
        if (p.right != null) { // go right
          next = p.right;
        } else {
          result.add(p.data);
          next = p.parent;
        }
      } else { // come up from right
        result.add(p.data);
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
