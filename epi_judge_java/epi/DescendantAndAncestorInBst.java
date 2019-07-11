package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class DescendantAndAncestorInBst {



  public static boolean pairIncludesAncestorAndDescendantOfM(BstNode<Integer> p1, BstNode<Integer> p2, BstNode<Integer> middle) {
    if (p1 == null || p2 == null || middle == null) {
      throw new IllegalArgumentException();
    }
    return check(p1, p2, middle) || check(p2, p1, middle);
  }


  private static boolean check(BstNode<Integer> ancestor, BstNode<Integer> descendant, BstNode<Integer> middle) {
    // check ancestor
    BstNode<Integer> p = ancestor;
    boolean isAncestor = false;
    while (p != null) { // from ancestor, search middle
      if (middle.data < p.data) {
        p = p.left;
      } else if (middle.data > p.data) {
        p = p.right;
      } else {
        isAncestor = true;
        break;
      }
    }
    System.out.println();
    System.out.println("isAncestor = " + ancestor.data + " => " + isAncestor);
    System.out.println("middle = " + middle.data);

    if (isAncestor == false) return false;
    else if (ancestor == middle) return false;

    // check descendant
    p = middle;
    boolean isDescendant = false;
    while (p != null) { // from middle, search descendant
      if (descendant.data < p.data) {
        p = p.left;
      } else if (descendant.data > p.data) {
        p = p.right;
      } else {
        isDescendant = true;
        break;
      }
    }

    if (isDescendant == false) return false;

    else if (descendant == middle) return false;

    // if (descendant == middle && ancestor == middle) return false;

    return true;
  }




  @EpiTest(testDataFile = "descendant_and_ancestor_in_bst.tsv")
  public static boolean pairIncludesAncestorAndDescendantOfMWrapper(
      TimedExecutor executor, BstNode<Integer> tree, int possibleAncOrDesc0,
      int possibleAncOrDesc1, int middle) throws Exception {
    final BstNode<Integer> candidate0 =
        BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc0);
    final BstNode<Integer> candidate1 =
        BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc1);
    final BstNode<Integer> middleNode =
        BinaryTreeUtils.mustFindNode(tree, middle);

    return executor.run(()
                            -> pairIncludesAncestorAndDescendantOfM(
                                candidate0, candidate1, middleNode));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DescendantAndAncestorInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
