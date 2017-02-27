class Node {
    int data;
    Node next = null;

    Node(int data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj != null) && (obj instanceof Node)) {
            Node other = (Node)obj;
            if (this.data == other.data) {
                if (this.next == null) {
                    return other.next == null;
                }
                else {
                    return this.next.equals(other.next);
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.data;
        result = 31 * result + (this.next == null ? 0 : this.next.hashCode());
        return result;
    }

    @Override 
    public String toString() {
        if (this.next == null) {
            return String.valueOf(this.data);
        }
        else {
            return this.data + " " + this.next.toString();
        }
    }
}

class Solution {
    public static void main(String[] args) {
        assert sum(linkedList(7,1,6), linkedList(5,9,2)).equals(linkedList(2,1,9));
        assert sum(linkedList(9,9), linkedList(9,9)).equals(linkedList(8,9,1));
        assert sum(linkedList(1,1), linkedList(1,1,1)).equals(linkedList(2,2,1));
        assert sum(linkedList(9,9), linkedList(9,9,9)).equals(linkedList(8,9,0,1))
            : "Actual: " + sum(linkedList(9,9), linkedList(9,9,9)) + ". Expected: " + linkedList(8,9,0,1);
        System.out.println("Success!");
    }

    private static Node sum(Node l1, Node l2) {
        Node head = null;
        Node tail = null;
        boolean overflow = false;
        while (l1 != null || l2 != null || overflow) {
            int sum = (l1 == null ? 0 : l1.data) + (l2 == null ? 0 : l2.data) + (overflow ? 1 : 0);
            int digit = sum % 10;
            overflow = (sum >= 10);
            if (head == null) {
                head = new Node(digit);
                tail = head;
            }
            else {
                tail.next = new Node(digit);
                tail = tail.next;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return head;
    }

    private static Node linkedList(int... values) {
        if (values.length == 0) return null;
        Node head = new Node(values[0]);
        Node lastNode = head;
        for (int i = 1; i < values.length; i++) {
            lastNode.next = new Node(values[i]);
            lastNode = lastNode.next;
        }
        return head;
    }
}