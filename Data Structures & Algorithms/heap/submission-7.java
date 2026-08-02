class MinHeap {

    private ArrayList<Integer> heap = new ArrayList<>();

    public MinHeap() {
    }

    public void push(int val) {
        heap.add(val);
        int cur = heap.size() - 1;
        percolateUp(cur);
    }

    public Integer pop() {
        if (heap.size() == 0){
            return -1;
        }
        if (heap.size() == 1){
            return heap.remove(0);
        }
        int res = heap.get(0);
        heap.set(0, heap.remove(heap.size()-1));
        int cur = 0;
        percolateDown(cur);
        return res;
    }

    private void percolateUp(int cur){
        while (cur > 0 && heap.get(cur) < heap.get(getParent(cur))) {
            swap(getParent(cur), cur);
            cur = getParent(cur);
        }
    }
    
    private void percolateDown(int cur){
        while (getLeftChild(cur) < heap.size()){
            int minIdx = cur;
            if (heap.get(minIdx) > heap.get(getLeftChild(cur))){
                minIdx = getLeftChild(cur);
            }
            if (getRightChild(cur) < heap.size() && heap.get(minIdx) > heap.get(getRightChild(cur))){
                minIdx = getRightChild(cur);
            }
            if (minIdx != cur){
                swap(cur, minIdx);
                cur = minIdx;
            } else {
                break;
            }
        }
    }

    public Integer top() {
        return heap.size() > 0 ? heap.get(0) : -1;
    }

    public void heapify(List<Integer> nums) {
        heap.clear();
        heap.addAll(nums);
        int cur = heap.size() / 2;
        while (cur >= 0) {
            percolateDown(cur);
            cur--;
        }
    }

    private void swap(int parentIndex, int childIndex){
        int tmp = heap.get(parentIndex);
        heap.set(parentIndex, heap.get(childIndex));
        heap.set(childIndex, tmp);
    }
    
    private int getParent(int index){
        return (index - 1) / 2;
    }

    private int getLeftChild(int index) {
        return index * 2 + 1;
    }

    private int getRightChild(int index) {
        return index * 2 + 2;
    }
}
