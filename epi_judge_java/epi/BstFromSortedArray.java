package epi;
import epi.test_framework.*;

import java.util.List;
public class BstFromSortedArray {

  // Space: O(h)
  // public static BstNode<Integer> buildMinHeightBSTFromSortedArray(List<Integer> A) {
  //   //   if (A == null || A.size() == 0) {
  //   //     return null;
  //   //   }
  //   //   return construct(A, 0, A.size() - 1);
  //   // }
  //   //
  //   //
  //   // private static BstNode<Integer> construct(List<Integer> A, int lo, int hi) {
  //   //   if (lo > hi) {
  //   //     return null;
  //   //   }
  //   //   if (lo == hi) { // only one node
  //   //     return new BstNode<>(A.get(lo));
  //   //   }
  //   //   int mid = lo + (hi - lo) / 2;
  //   //   BstNode<Integer> root = new BstNode<>(A.get(mid));
  //   //   root.left = construct(A, lo, mid - 1);
  //   //   root.right = construct(A, mid + 1, hi);
  //   //   return root;
  //   // }

  private static int headIdx;
  public static BstNode<Integer> buildMinHeightBSTFromSortedArray(List<Integer> A) {
    if (A == null || A.size() == 0) {
      return null;
    }
    headIdx = 0;
    return construct(A, 0, A.size() - 1);
  }

  private static BstNode<Integer> construct(List<Integer> A, int lo, int hi) {
    if (lo > hi) {
      return null;
    }
    int mid = lo + (hi - lo) / 2;

    BstNode<Integer> left = construct(A, lo, mid - 1);

    BstNode<Integer> root = new BstNode<>(A.get(headIdx));

    headIdx += 1;

    BstNode<Integer> right = construct(A, mid + 1, hi);

    root.left = left;
    root.right = right;

    return root;
  }



  @EpiTest(testDataFile = "bst_from_sorted_array.tsv")
  public static int
  buildMinHeightBSTFromSortedArrayWrapper(TimedExecutor executor,
                                          List<Integer> A) throws Exception {
    BstNode<Integer> result =
        executor.run(() -> buildMinHeightBSTFromSortedArray(A));

    List<Integer> inorder = BinaryTreeUtils.generateInorder(result);

    TestUtils.assertAllValuesPresent(A, inorder);
    BinaryTreeUtils.assertTreeIsBst(result);
    return BinaryTreeUtils.binaryTreeHeight(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
