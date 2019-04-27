package leatcode;

import java.util.Iterator;
import java.util.Objects;

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        NextNodeCreator nextNodeCreator = new NextNodeCreator();
        Iter iter1 = new Iter(l1);
        Iter iter2 = new Iter(l2);
        int overflow = 0;
        while (iter1.hasNext() || iter2.hasNext()) {
            int first = iter1.hasNext() ? iter1.next().val : 0;
            int second = iter2.hasNext() ? iter2.next().val : 0;
            nextNodeCreator.next().val = (first + second + overflow) % 10;
            overflow = (first + second + overflow) / 10;
        }
        if (overflow == 1) {
            nextNodeCreator.next().val = 1;
        }
        return nextNodeCreator.result();
    }

    class NextNodeCreator {
        private ListNode firstNode = new ListNode(0);
        private ListNode next;

        ListNode next() {
            if (next == null) {
                next = firstNode;
                return next;
            } else {
                next.next = new ListNode(0);
                next = next.next;
                return next;
            }
        }

        ListNode result() {
            return firstNode;
        }
    }

    class Iter implements Iterator<ListNode> {

        private ListNode next;

        Iter(ListNode next) {
            this.next = next;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public ListNode next() {
            ListNode temp = next;
            next = next.next;
            return temp;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListNode listNode = (ListNode) o;
        return val == listNode.val &&
                Objects.equals(next, listNode.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }
}