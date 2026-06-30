class Node {
    int val;
    Node next;

    public Node(int val){
        this.val = val;
    }
}

class LinkedList {
    
    private Node head;
    private Node tail;
    private ArrayList<Integer> list = new ArrayList<>();

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public int get(int index) {
        Node curNode = head;
        if (head == null) return -1;
        for (int cur = 0; cur < index; cur++){
            if (curNode == null || curNode.next == null){
                return -1;
            }
            curNode = curNode.next;
        }
        return curNode.val;
    }

    public void insertHead(int val) {
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
        if (tail == null){
            tail = newNode;
        }
    }

    public void insertTail(int val) {
        Node newNode = new Node(val);
        if (tail == null){
            tail = newNode;
            head = newNode;
            return;
        }
        tail.next = newNode;
        tail = tail.next;
    }

    public boolean remove(int index) {
        if (index == 0){
            if (head == null){
                return false;
            } else {
                head = head.next;
                return true;
            }
        }
        Node curNode = head;
        for (int cur = 0; cur < index-1; cur++){
            if (curNode == null || curNode.next == null){
                return false;
            }
            curNode = curNode.next;
        }
        if (curNode.next == null){
            return false;
        } else if (curNode.next == tail) {
            tail = curNode;
            tail.next = null;
            return true;
        } else {
            curNode.next = curNode.next.next;
            return true;
        }
    }

    public ArrayList<Integer> getValues() {
        this.list.clear();
        Node curNode = head;
        while (curNode != null) {
            this.list.add(curNode.val);
            curNode = curNode.next;
        }
        return this.list;

    }
}
