class Node {
    int key;
    int val;
    Node left = null;
    Node right = null;
    public Node(int key, int val){
        this.key = key;
        this.val = val;
    }
}
class TreeMap {
    private Node root = null;
    public TreeMap() { 
    }

    public void insert(int key, int val) {
        this.root = insertH(this.root, new Node(key, val));
    }

    private Node insertH(Node root, Node target){
        if (root == null){
            return target;
        }
        if (target.key > root.key){
            root.right = insertH(root.right, target);
        }
        else if (target.key < root.key){
            root.left = insertH(root.left, target);
        }
        else {
            root = target;
        }
        return root;
    }

    public int get(int key) {
        return getH(this.root, key);
    }

    private int getH(Node root, int key){
        if (root == null){
            return -1;
        }
        if (key == root.key){
            return root.val;
        }
        else if (key > root.key){
            getH(root.right, key);
        }
        else if (key < root.key){
            getH(root.left, key);
        }
        return -1;
    }

    public int getMin() {
        Node root = getMinH(this.root);
        int res = (root == null) ? -1 : root.val;
        return res;
    }

    private Node getMinH(Node root){
        while (root != null && root.left != null){
            root = root.left;
        }
        return root;
    }

    public int getMax() {
        while (root != null && root.right != null){
            root = root.right;
        }
        int res = (root == null) ? -1 : root.val;
        return res;
    }

    public void remove(int key) {
       this.root = removeH(this.root, key);
    }

    private Node removeH(Node root, int key){
        if (root == null){
            return null;
        }
        if (key > root.key){
            root.right = removeH(root.right, key);
        }
        else if (key < root.key){
            root.left = removeH(root.left, key);
        }
        else {
            if (root.left == null){
                return root.right;
            }
            else if (root.right == null){
                return root.left;
            }
            else {
                Node replacement = getMinH(root.right);
                root.key = replacement.key;
                root.val = replacement.val;
                root.right = removeH(root.right, replacement.key);
            }
        }
        return root;
    }

    public List<Integer> getInorderKeys() {
        List<Integer> list = new ArrayList<Integer>();
        inorder(this.root, list);
        return list;
    }

    private void inorder(Node root, List<Integer> list){
        if (root == null){
            return;
        }
        inorder(root.left, list);
        list.add(root.key);
        inorder(root.right, list);
    }
}
