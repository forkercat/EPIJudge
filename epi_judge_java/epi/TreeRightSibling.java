package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

public class TreeRightSibling {
  public static class BinaryTreeNode<T> {
    public T data;
    public BinaryTreeNode<T> left, right;
    public BinaryTreeNode<T> next = null; // Populates this field.

    public BinaryTreeNode(T data) { this.data = data; }
  }

  /**
   * Solution - Space: O(1)
   */
  public static void constructRightSibling(BinaryTreeNode<Integer> tree) {
    while (tree != null) {
      populateLowerLevelNextField(tree);
      tree = tree.left;
    }
  }

  private static void populateLowerLevelNextField(BinaryTreeNode<Integer> startNode) {
    if (startNode.left == null && startNode.right == null) {
      return;
    }
    BinaryTreeNode<Integer> p = startNode;
    while (p != null) {
      p.left.next = p.right; // left child
      p.right.next = (p.next != null) ? p.next.left : null; // right child
      p = p.next; // move to next
    }
  }


  /**
   * BFS method - Space: O(N)
   */
  /*
  public static void constructRightSibling(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return;
    }
    Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
    queue.offer(tree);

    while (queue.size() > 0) {
      int childSize = queue.size();
      for (int i = 0; i < childSize; ++i) {
        BinaryTreeNode<Integer> p = queue.poll();
        if (p.left == null && p.right == null) {
          continue;
        }
        p.left.next = p.right;
        p.right.next = (p.next != null) ? p.next.left : null;
        queue.offer(p.left);
        queue.offer(p.right);
      }
    }
  }
   */





  private static BinaryTreeNode<Integer>
  cloneTree(BinaryTree<Integer> original) {
    if (original == null) {
      return null;
    }
    BinaryTreeNode<Integer> cloned = new BinaryTreeNode<>(original.data);
    cloned.left = cloneTree(original.left);
    cloned.right = cloneTree(original.right);
    return cloned;
  }

  @EpiTest(testDataFile = "tree_right_sibling.tsv")
  public static List<List<Integer>>
  constructRightSiblingWrapper(TimedExecutor executor, BinaryTree<Integer> tree)
      throws Exception {
    BinaryTreeNode<Integer> cloned = cloneTree(tree);

    executor.run(() -> constructRightSibling(cloned));

    List<List<Integer>> result = new ArrayList<>();
    BinaryTreeNode<Integer> levelStart = cloned;
    while (levelStart != null) {
      List<Integer> level = new ArrayList<>();
      BinaryTreeNode<Integer> levelIt = levelStart;
      while (levelIt != null) {
        level.add(levelIt.data);
        levelIt = levelIt.next;
      }
      result.add(level);
      levelStart = levelStart.left;
    }
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeRightSibling.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
