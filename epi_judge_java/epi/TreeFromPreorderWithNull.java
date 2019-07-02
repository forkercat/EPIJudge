package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.List;
public class TreeFromPreorderWithNull {

  private static Integer subtreeIdx;

  public static BinaryTreeNode<Integer> reconstructPreorder(List<Integer> preorder) {
    if (preorder == null || preorder.size() == 0) {
      return null;
    }
    subtreeIdx = 0;
    return construct(preorder);
  }

  private static BinaryTreeNode<Integer> construct(List<Integer> preorder) {
    Integer subtreeKey = preorder.get(subtreeIdx);
    ++subtreeIdx; // update idx after visit the key
    if (subtreeKey == null) {
      return null;
    }
    BinaryTreeNode<Integer> root = new BinaryTreeNode<>(subtreeKey);
    root.left = construct(preorder);
    root.right = construct(preorder);
    return root;
  }


  @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
  public static BinaryTreeNode<Integer>
  reconstructPreorderWrapper(TimedExecutor executor, List<String> strings)
      throws Exception {
    List<Integer> ints = new ArrayList<>();
    for (String s : strings) {
      if (s.equals("null")) {
        ints.add(null);
      } else {
        ints.add(Integer.parseInt(s));
      }
    }

    return executor.run(() -> reconstructPreorder(ints));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderWithNull.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
