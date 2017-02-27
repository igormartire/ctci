import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        assert verify(partition(linkedList(8,7,1,7,2), 5), 5);
        System.out.println("Success");
    }

    private static Node partition(Node head, int partition) {
        if (head == null) {
            return head;
        }
        Node prev = head;
        Node curr = head.next;
        while (curr != null) {
            if (curr.data < partition) {
                prev.next = curr.next;
                curr.next = head;
                head = curr;
                curr = prev.next;
            }
            else {
                prev = curr;
                curr = curr.next;
            }
        }
        return head;
    }

    private static Node linkedList(int... values) {
        if (values.length == 0) return null;
        Node head = new Node(values[0]);
        Node lastNode = head;
        for (int value : Arrays.copyOfRange(values, 1, values.length)) {
            lastNode.next = new Node(value);
            lastNode = lastNode.next;
        }
        return head;
    }

    private static boolean verify(Node n, int partition) {
        boolean afterPartition = false;
        while (n != null) {
            if (n.data < partition) {
                if (afterPartition) {
                    return false;
                }
            }
            else {
                afterPartition = true;
            }
            n = n.next;
        }
        return true;
    }

}

class Node {
    int data;
    Node next = null;
    Node(int data) {
        this.data = data;
    }
}