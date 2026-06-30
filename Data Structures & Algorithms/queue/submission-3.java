class Deque {
    Node head;
    Node tail;

    class Node {
        Node next;
        Node prev;
        final int val;

        public Node(int val){
            this.val = val;
        }
    }

    public Deque() {
        
    }

    public boolean isEmpty() {
        return head == null || tail == null;
    }

    public void append(int value) {
       Node newNode = new Node(value);
       if (tail == null) {
            head = newNode;
            tail = newNode;
       } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
       }
    }

    public void appendleft(int value) {
        Node newNode = new Node(value);
       if (head == null) {
            head = newNode;
            tail = newNode;
       } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
       }
    }

    public int pop() {
        if (isEmpty()){
            return -1;
        } else if (head == tail) {
            int val = tail.val;
            head = tail = null;
            return val;
        } else {
            int val = tail.val;
            tail = tail.prev;
            tail.next = null;
            return val;
        }
    }

    public int popleft() {
        if (isEmpty()){
            return -1;
        } else if (head == tail) {
            int val = head.val;
            head = tail = null;
            return val;
        } else {
            int val = head.val;
            head = head.next;
            head.prev = null;
            return val;
        }
    }
}
