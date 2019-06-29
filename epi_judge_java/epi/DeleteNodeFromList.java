package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class DeleteNodeFromList {

  // Assumes nodeToDelete is not tail.
  public static void deletionFromList(ListNode<Integer> nodeToDelete) {
    if (nodeToDelete == null) return;

    ListNode<Integer> p = nodeToDelete;
    if (p.next != null) { // its next is not null (actually it is guaranteed in the book)
      p.data = p.next.data;
      p.next = p.next.next; // delete its successor
    }
  }

  /** Wrong code, nodeToDelete is not the head */
  // public static void deletionFromList(ListNode<Integer> nodeToDelete) {
  //   if (nodeToDelete == null) {
  //     return;
  //   }
  //   ListNode<Integer> curr = nodeToDelete;
  //   while (curr.next != null) { // guarantee curr.next @NotNull
  //     if (curr.next.data == nodeToDelete.data) {
  //       // curr    curr.next    curr.next.next
  //       curr.next = curr.next.next; // delete
  //       return;
  //     }
  //     curr = curr.next;
  //   } // stops at the last node
  // }


  @EpiTest(testDataFile = "delete_node_from_list.tsv")
  public static ListNode<Integer> deleteListWrapper(TimedExecutor executor,
                                                    ListNode<Integer> head,
                                                    int nodeToDeleteIdx)
      throws Exception {
    ListNode<Integer> nodeToDelete = head;
    if (nodeToDelete == null)
      throw new RuntimeException("List is empty");
    while (nodeToDeleteIdx-- > 0) {
      if (nodeToDelete.next == null)
        throw new RuntimeException("Can't delete last node");
      nodeToDelete = nodeToDelete.next;
    }

    final ListNode<Integer> finalNodeToDelete = nodeToDelete;
    executor.run(() -> deletionFromList(finalNodeToDelete));

    return head;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteNodeFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
