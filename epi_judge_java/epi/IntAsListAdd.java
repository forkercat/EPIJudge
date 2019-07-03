package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntAsListAdd {
  @EpiTest(testDataFile = "int_as_list_add.tsv")

  public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1, ListNode<Integer> L2) {
    return add(L1, L2, 0);
  }

  private static ListNode<Integer> add(ListNode<Integer> L1, ListNode<Integer> L2, int carry) {
    if (L1 == null && L2 == null) {
      if (carry > 0) {
        return new ListNode<>(carry, null);
      } else {
        return null;
      }
    }
    int x = (L1 == null) ? 0 : L1.data;
    int y = (L2 == null) ? 0 : L2.data;
    int sum = x + y + carry;
    ListNode retNode = new ListNode(sum % 10, null);
    if (L1 != null) L1 = L1.next;
    if (L2 != null) L2 = L2.next;
    retNode.next = add(L1, L2, sum / 10);
    return retNode;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsListAdd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
