package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class TreeFromPreorderInorder {
  @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")

  // public static BinaryTreeNode<Integer>
  // binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
  //   if (preorder == null || inorder == null) {
  //     return null;
  //   }
  //   int preIdx = 0, inIdx = 0;
  //   int preSize = preorder.size(), inSize = inorder.size();
  //   BinaryTreeNode<Integer> dummy = new BinaryTreeNode<>(-1);
  //   BinaryTreeNode<Integer> prev = dummy;
  //   while (preIdx < preSize && inIdx < inSize) {
  //     // preorder
  //     while (preIdx < preSize && preorder.get(preIdx) != inorder.get(inIdx)) {
  //       prev.left = new BinaryTreeNode<>(preorder.get(preIdx));
  //       preIdx += 1;
  //       prev = prev.left;
  //     } // preIdx outOfBound OR [preIdx] == [postIdx]
  //     if (preIdx < preSize) {
  //       prev.left = new BinaryTreeNode<>(preorder.get(preIdx));
  //       preIdx += 1;
  //     } else {
  //       break; // done since preIdx is out of bound
  //     }
  //
  //     // inorder
  //     while (inIdx < inSize && inorder.get(inIdx) != preorder.get(preIdx)) {
  //       inIdx += 1;
  //     }
  //     if (inIdx < inSize) {
  //       inIdx += 1;
  //     } else {
  //       break;
  //     }
  //   }
  //   return dummy.left;
  // }


  public static BinaryTreeNode<Integer> binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
    if (preorder == null || inorder == null) {
      return null;
    }
    int n = preorder.size();
    return construct(preorder, inorder, 0, n - 1, 0, n - 1);
  }

  private static BinaryTreeNode<Integer> construct(List<Integer> preorder, List<Integer> inorder, int preStart, int preEnd, int inStart, int inEnd) {
    if (preStart > preEnd) { // notice that (preEnd - preStart) == (inEnd - inStart)
      return null;
    }

    if (preStart == preEnd) { // only one - leaf node
      return new BinaryTreeNode<>(preorder.get(preStart));
    }

    // E.g.
    //         left      right
    // pre:  H B F E A  C D G I
    //       ^ -------  -------
    // In:   F B A E H  C D I G
    //       ------- ^  -------

    // root
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(preorder.get(preStart));

    int split = 0; // notice this is just an offset
    int rootVal = preorder.get(preStart); // write it first. I don't know why it fails in the condition yet. Strange.
    while (inorder.get(inStart + split) != rootVal) { // assume that the value must be found
      ++split;
    }

    // Note: preStart is not always the same as inStart, consider B F E A and F B A E
    // B is the root        left: F | right: A and E
    // pre:  H B F E A  =>  [preStart + 1,  preStart + split] => F
    //         -------  =>  [preStart + split + 1, preEnd] => E A
    //         ^
    // In:   F B A E H  =>  [inStart, inStart + split - 1] => F
    //       -------    =>  [inStart + split, inEnd] => A E
    //         ^
    // split = 1 (an offset from inStart)

    // build left
    root.left = construct(preorder, inorder, preStart + 1, preStart + split, inStart, inStart + split - 1);
    // build right
    root.right = construct(preorder, inorder, preStart + split + 1, preEnd, inStart + split + 1, inEnd);

    return root;
  }












  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
