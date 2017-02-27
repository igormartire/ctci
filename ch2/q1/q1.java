import java.util.HashMap;

class Solution {
    public static void main(String[] args) {
        Node head;

        head = new Node(1);
        head.append(3, 2, 3, 4, 3, 4, 1, 1, 0, 0);
        removeDuplicates(head);
        assert head.toString().trim().equals("1 3 2 4 0");

        head = new Node(1);
        head.append(3, 2, 3, 4, 3, 4, 1, 1, 0, 0);
        removeDuplicatesRecursive(head);
        assert head.toString().trim().equals("1 3 2 4 0");

        head = new Node(1);
        head.append(3, 2, 3, 4, 3, 4, 1, 1, 0, 0);
        removeWithoutTemporaryBuffer(head);
        assert head.toString().trim().equals("1 3 2 4 0");

        System.out.println("Sucess!");
    }

    private static void removeDuplicates(Node head) {
        if (head == null || head.next == null) return;
        HashMap<Integer, Boolean> buf = new HashMap<>();
        buf.put(head.data, true);
        Node n = head;
        while(n.next != null) {
            if (buf.containsKey(n.next.data)) {
                n.next = n.next.next;
            }
            else {
                n = n.next;
                buf.put(n.data, true);
            }
        }
    }

    private static void removeDuplicatesRecursive(Node head) {
        if (head == null) return;
        HashMap<Integer, Boolean> buf = new HashMap<>();
        buf.put(head.data, true);
        head.next = removeDuplicatesRecursiveStep(head.next, buf);
    }

    private static Node removeDuplicatesRecursiveStep(Node head, HashMap<Integer, Boolean> buf) {
        if (head == null) return null;
        if (buf.containsKey(head.data)) {
            return removeDuplicatesRecursiveStep(head.next, buf);
        } 
        else {
            buf.put(head.data, true);
            head.next = removeDuplicatesRecursiveStep(head.next, buf);
            return head;
        }
    }

    private static void removeWithoutTemporaryBuffer(Node head) {
        Node seek;
        while (head != null) {
            seek = head;
            while (seek.next != null) {
                if (seek.next.data == head.data) {
                    seek.next = seek.next.next;
                }
                else {
                    seek = seek.next;
                }
            }
            head = head.next;
        }
    }
}

class Node {
    Node next = null;
    int data;

    Node(int data) {
        this.data = data;
    }

    void append(int data) {
        Node n;
        for (n = this; n.next != null; n = n.next);
        n.next = new Node(data);
    }

    void append(int... datas) {
        Node n;
        for (n = this; n.next != null; n = n.next);
        for (int d : datas) {
            n.next = new Node(d);
            n = n.next;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node n = this; n != null; n = n.next) {
            sb.append(n.data + " ");
        }
        return sb.toString();
    }
}