package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SearchFirstGreaterValueInBst {

  // public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree,
  //                                                      Integer k) {
  //   if (tree == null) {
  //     return null;
  //   }
  //   BstNode<Integer> p = tree;
  //   BstNode<Integer> succ = null;
  //   while (p != null) {
  //     if (k < p.data) { // go left
  //       succ = p;
  //       p = p.left;
  //     } else if (k > p.data) { // go right
  //       p = p.right;
  //     } else { // equal
  //       p = p.right;
  //     }
  //   }
  //   return succ;
  // }

  public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree,
                                                       Integer k) {
    if (tree == null) {
      return null;
    }

    if (k < tree.data) { // go left
      BstNode<Integer> succ = findFirstGreaterThanK(tree.left, k);
      if (succ != null) return succ;
      else return tree;
    } else {
      return findFirstGreaterThanK(tree.right, k);
    }
  }


  @EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
  public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree,
                                                 Integer k) {
    BstNode<Integer> result = findFirstGreaterThanK(tree, k);
    return result != null ? result.data : -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstGreaterValueInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
