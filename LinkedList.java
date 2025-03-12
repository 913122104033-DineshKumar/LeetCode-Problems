class ListNode {
    int val;
    Node next;

    public ListNode (int val) {
        this.val = val;
        this.next = null;
    }

    public ListNode (int val, Node next) {
        this.val = val;
        this.next = next;
    } 

}

//Used for building the LinkedList Nodes in a proper way
public ListNode head = null; //Head pointer
public ListNode pointer = null; //Pointer is the iterator which is used to link the upcoming nodes after the nodes

public void buildLinkedList (int val) {
    ListNode temp = new ListNode(val); //Creating a Node
    if (head == null) {  //If the head is null that means no nodes in the List
        head = temp; //Now, head is assigned to the first node
        pointer = temp; //Now it copies the head node
    } else {
        pointer.next = temp; //Now, we are assigning the next node to the next of the current node if head -> 1 then 1.next -> temp
        pointer = pointer.next; // Moving the pointer to the next position after adding the Node to the List
    }
}

public void deleteFirstNode (ListNode head) { //To delete the first node of the List just move the head pointer to the head.next
    head = (head == null) ? null : head.next;    
}

public void deleteRandomNode (ListNode head, int target) { //To delete a Node with target value
    ListNode current = head; //Assigning a local variable to the head
    ListNode currentNextNode = null; //To capture the node which is next to the target node
    ListNode prev = null; // To capture the node which is previous to the target Node
    //Example 1 -> 2 -> 3, 3 is the target now
    while (current.val != target && current.next != null) {
        prev = current; // 1. prev = 1, 2. prev = 2
        currentNextNode = current.next.next; // 1. currentNextNode = 3 // 2. currentNextNode = null
        current = current.next; // 1. current = 2, 2. current = 3
    }
    prev.next = currentNextNode;
}

public ListNode reverseLinkedList (ListNode head) { //Reverse a Linked List using a Two pointer approach
    ListNode current = head; //Local head
    ListNode prev = null; //To make the list reverse and the final head will in this node
    while (current != null) {
        ListNode next = current.next; //The current.next link will be reversed in the next line so to capture the next of the current node
        current.next = prev; //Reversing the Link of the List
        prev = current; //Assigning the current to the prev
        current = next; //Moving to pointer to the next node
    }
    return prev; //Now this node have the reversed List's head
}

public boolean detectCycleinLinkedList (ListNode head) { //Tortoise and Hare Algorithm
    if (head == null || head.next == null) { //Base Case
        return false;
    }
    ListNode slow = head; //Slow pointer -> moves one node at a time
    ListNode fast = head; //Fast pointer -> moves two ndoes at a time
    while (fast != null && fast.next != null) {
        slow = slow.next; //One node movement
        fast = fast.next.next; //Two node movement
        if (slow == fast) { //If  the node are same, then it is a cycle
            return true;
        }
    }
    return false;
}

public ListNode findMiddleNode (ListNode head) { //Tortoise and Hare Algorithm
    if (head == null || head.next == null) {
        return head;
    }
    ListNode slow = head;
    ListNode fast = head;
    //Even case to find the first middle node use this below conditions or add this condition: fast.next.next != null to it to get the second middle Node
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow; //Will have the middle node
}

public ListNode mergeSortedLists (ListNode head1, ListNode head2) {
    ListNode current1 = head1; //Points to the first head
    ListNode current2 = head2; //Points to the second head
    //Using Merge Sort Merging Technique
    while (current1 != null && current2 != null) { 
        if (current1.val <= current2.val) {
            buildLinkedList(current1.val);  //Refer the above method
            current1 = current1.next; //Move the pointer1 to next node
        } else {
            buildLinkedList(current2.val);
            current2 = current2.next; //Move the pointer2 to next node 
        }
    }
    while (current1 != null) { //If the pointer1 is not finished
        buildLinkedList(current1.val);
        current1 = current1.next;
    }
    while (current2 != null) { //If the pointer2 is not finished
        buildLinkedList(current2.val);
        current2 = current2.next;
    }
    return head;
}

public ListNode intersectionOfLinkedList (ListNode l1, ListNode l2) {
    ListNode cur1 = l1;
    ListNode cur2 = l2;
    while (cur1 != cur2) {
        cur1 = (cur1 == null) ? l2 : cur1.next; //If cur1 becomes null, then move the pointer to l2 
        cur2 = (cur2 == null) ? l1 : cur2.next; //If cur2 becomes null, then move the pointer to l1
    }
    return cur1;
}

public ListNode removeDuplicates (ListNode head) {
    ListNode current = head;
    while (current != null && current.next != null) {
        if (current.val == current.next.val) {  //If the pointer vals are equal then skip the next pointer and move ahead of it
            current.next = current.next.next; // Skip the next node
        } else {
            current = current.next; //Move to next node
        }
    }
    return head;
}

public ListNode mergeSort (ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    ListNode midNode = findMiddleNode(head);
    ListNode rightHalf = midNode.next;
    midNode.next = null; //Breaking the link and separating into two halves
    ListNode leftSorted = mergeSort(head); //Has the left sorted list
    ListNode rightSorted = mergeSort(rightHalf); //Has the right sorted list
    return mergeSortedLists(leftSorted, rightSorted); //refer to the above method
}

public class Solution {
    //It is the one of the way to get the input from the console.
    public static void main (String[] args) { 
        Scanner scanner = new Scanner(System.in);
        ListNode dummy = new ListNode(-1);
        ListNode pointer = dummy;
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int val = scanner.nextInt();
            pointer.next = new ListNode(val);
            pointer = pointer.next;
        }
        reverseLinkedList(dummy.next); //This dummy.next will have the original head of the List
    }
}