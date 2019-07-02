package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class BstFromPreorder {

  // @EpiTest(testDataFile = "bst_from_preorder.tsv")
  // public static BstNode<Integer> rebuildBSTFromPreorder(List<Integer> preorderSequence) {
  //   if (preorderSequence == null) {
  //     return null;
  //   }
  //   return construct(preorderSequence, 0, preorderSequence.size() - 1);
  // }
  //
  // private static BstNode<Integer> construct(List<Integer> preorder, int lo, int hi) {
  //   if (preorder == null) {
  //     return null;
  //   }
  //
  //   if (lo > hi) {
  //     return null;
  //   }
  //
  //   BstNode<Integer> root = new BstNode<>(preorder.get(lo));
  //   int rightIdx = lo + 1;
  //
  //   while (rightIdx <= hi && preorder.get(rightIdx) <= root.data) {
  //     rightIdx += 1;
  //   }
  //
  //   // left
  //   root.left = construct(preorder, lo + 1, rightIdx - 1);
  //   root.right = construct(preorder, rightIdx, hi);
  //
  //   return root;
  // }


  private static Integer rootIdx;

  @EpiTest(testDataFile = "bst_from_preorder.tsv")
  public static BstNode<Integer> rebuildBSTFromPreorder(List<Integer> preorderSequence) {
    if (preorderSequence == null) {
      return null;
    }
    rootIdx = 0;
    return construct(preorderSequence, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static BstNode<Integer> construct(List<Integer> preorder, int lower, int upper) {
    if (rootIdx >= preorder.size()) {
      return null;
    }

    int rootVal = preorder.get(rootIdx);

    if (rootVal < lower || rootVal > upper) { // rootVal >= upper
      return null;
    }

    rootIdx += 1;

    BstNode<Integer> root = new BstNode<>(rootVal);
    root.left = construct(preorder, lower, rootVal);
    root.right = construct(preorder, rootVal, upper);

    return root;
  }



    public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromPreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
