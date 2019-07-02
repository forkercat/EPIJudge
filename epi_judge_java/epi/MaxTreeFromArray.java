/**
 * @author Junhao Wang
 * @date 07/01/2019
 */
package epi;

import java.util.ArrayList;
import java.util.List;

public class MaxTreeFromArray {

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    list.add(3); list.add(7); list.add(5); list.add(6); list.add(9); list.add(2); list.add(8);
    BinaryTreeNode<Integer> node = maxTreeFromArray(list);
    System.out.println(list);
    preorder(node);
  }

  private static void preorder(BinaryTreeNode<Integer> root) {
    if (root == null) return;
    System.out.println(root.data);
    preorder(root.left);
    preorder(root.right);
  }

  public static BinaryTreeNode<Integer> maxTreeFromArray(List<Integer> list) {
    if (list == null || list.size() == 0) {
      return null;
    }
    return construct(list, 0, list.size() - 1);
  }

  private static BinaryTreeNode<Integer> construct(List<Integer> list, int lo, int hi) {
    if (lo > hi) { // base case
      return null;
    }

    if (lo == hi) { // one node in the list
      return new BinaryTreeNode<>(list.get(lo));
    }

    // find the max
    int maxIdx = lo;
    for (int i = lo; i <= hi; ++i) {
      if (list.get(i) > list.get(maxIdx)) {
        maxIdx = i;
      }
    }

    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(list.get(maxIdx));
    root.left = construct(list, lo, maxIdx - 1);
    root.right = construct(list, maxIdx + 1, hi);

    return root;
  }





}
