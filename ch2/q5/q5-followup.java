class Node {
    int data;
    Node next = null;

    Node() {}

    Node(int data) {
        this.data = data;
    }

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
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
        assert sum(linkedList(6,1,7), linkedList(2,9,5)).equals(linkedList(9,1,2));
        assert sum(linkedList(9,9), linkedList(9,9)).equals(linkedList(1,9,8));
        assert sum(linkedList(1,1), linkedList(1,1,1)).equals(linkedList(1,2,2));
        assert sum(linkedList(9,9), linkedList(9,9,9)).equals(linkedList(1,0,9,8))
            : "Actual: " + sum(linkedList(9,9), linkedList(9,9,9)) + ". Expected: " + linkedList(1,0,9,8);
        System.out.println("Success!");
    }

    private static Node sum(Node l1, Node l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        
        Node p1 = l1;
        Node p2 = l2;
        while (p1 != null || p2 != null) {
            if (p1 == null) {
                l1 = new Node(0, l1);
                p2 = p2.next;
            }
            else if (p2 == null) {
                l2 = new Node(0, l2);
                p1 = p1.next;
            }
            else {
                p1 = p1.next;
                p2 = p2.next;
            }
        }

        Node head = new Node();
        boolean overflown = sumRecursive(l1, l2, head);
        if (overflown) {
            head = new Node(1, head);
        }
        return head;
    }

    /**
     * @param   l1 linked list to sum with l2 (must have same length as l2)
     * @param   l2 linked list to sum with l1 (must have same length as l1)
     * @return  `true` if sum overflown, `false` otherwise
     */
    private static boolean sumRecursive(Node l1, Node l2, Node dest) {
        boolean overflown = false;
        if (l1.next != null || l2.next != null) {
            assert l1.next != null && l2.next != null; // l1 and l2 must have same length
            dest.next = new Node();
            overflown = sumRecursive(l1.next, l2.next, dest.next);
        }
        int sum = l1.data + l2.data + (overflown ? 1 : 0);
        dest.data = sum % 10;
        return sum >= 10;
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