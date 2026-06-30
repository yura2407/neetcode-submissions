class BinaryTree {

    static class TreeNode {
        int key;
        int val;
        TreeNode right;
        TreeNode left;

        public TreeNode(int key) {
            this.key = key;
        }

        public TreeNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    static TreeNode search(TreeNode root, int key) {
        TreeNode res = switch (root) {
            case null -> null;
            case TreeNode n when n.key == key -> root;
            case TreeNode n when n.key < key -> search(root.right, key);
            case TreeNode n when n.key > key -> search(root.left, key);
            default -> null;
        };
        return res;
    }

    static TreeNode insert(TreeNode root, int key, int val, boolean[] existsAlready) {
        if (root == null) {
            return new TreeNode(key, val);
        }
        if (root.key < key){
            root.right = insert(root.right, key, val, existsAlready);
        } else if (root.key > key){
            root.left = insert(root.left, key, val, existsAlready);
        } else {
            root.val = val;
            existsAlready[0] = true;
        }
        return root;
    }
    
    static TreeNode remove(TreeNode root, int key, boolean[] res) {
        if (root == null) {
            return null;
        }
        //System.out.println(root.key);
        if (root.key > key) {
            root.left = remove(root.left, key, res);
        } else if (root.key < key){
            root.right = remove(root.right, key, res);
        } else {
            res[0] = true;
            if (root.right == null) {
                return root.left;
            } else if (root.left == null) {
                return root.right;
            } else {
                TreeNode minLeafNode = minLeafNode(root.right);
                root.key = minLeafNode.key;
                root.val = minLeafNode.val;
                root.right = remove(root.right, minLeafNode.key, res);
            }
        }
        return root;
    }

    static List<TreeNode> traverseInOrder(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root!= null || !stack.isEmpty()){
            if (root != null){
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                res.add(root);
                root = root.right;
            }
        }
        return res;
    }

    private static TreeNode minLeafNode(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}


class HashTable {
    int capacity;
    int size = 0;
    BinaryTree.TreeNode[] arr;

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.arr = new BinaryTree.TreeNode[capacity];
    }

    public void insert(int key, int value) {
        if ((size + 1) * 2 >= capacity){
            this.resize();
        }
        insertTree(key, value);
    }

    private void insertTree(int key, int value) {
        boolean[] existsAlready = {false};
        this.arr[hash(key)] = BinaryTree.insert(this.arr[hash(key)], key, value, existsAlready);
        if (!existsAlready[0]){
            this.size++;
        }
    }

    public int get(int key) {
        BinaryTree.TreeNode res = BinaryTree.search(this.arr[hash(key)], key);
        return res == null ? -1 : res.val;
    }

    public boolean remove(int key) {
        boolean[] res = {false};
        this.arr[hash(key)] = BinaryTree.remove(this.arr[hash(key)], key, res);
        if (res[0]) {
            this.size--;
        }
        return res[0];
    }

    public int getSize() {
        return this.size;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void resize() {
        this.capacity *= 2;
        this.size = 0;
        BinaryTree.TreeNode[] tmp = this.arr;
        this.arr = new BinaryTree.TreeNode[capacity];
        for (BinaryTree.TreeNode cur : tmp){
            for (BinaryTree.TreeNode node : BinaryTree.traverseInOrder(cur)) {
                this.insertTree(node.key, node.val);
            }
        }
    }

    private int hash(int key){
        return key % this.capacity;
    }
}
