class MinHeap {

    private ArrayList<Integer> heap = new ArrayList<>();

    public MinHeap() {
        heap.add(Integer.MIN_VALUE);
    }

    private void reset(){
        heap.clear();
        heap.add(Integer.MIN_VALUE);
    }

    public void push(int val) {
        heap.add(val);
        int cur = heap.size() - 1;
        //Percolate up
        while (heap.get(cur) < heap.get(getParent(cur))) {
            swap(getParent(cur), cur);
            cur = getParent(cur);
        }
    }

    public Integer pop() {
        if (heap.size() < 2){
            return -1;
        }
        if (heap.size() == 2){
            return heap.remove(1);
        }
        int res = heap.get(1);
        heap.set(1, heap.remove(heap.size()-1));
        int cur = 1;
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
        return res;
    }

    public Integer top() {
        return heap.size() > 1 ? heap.get(1) : -1;
    }

    public void heapify(List<Integer> nums) {
        reset();
        heap.addAll(nums);
        int cur = (heap.size() - 1) / 2;
        while (cur > 0) {
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
            cur--;
        }
    }

    private void swap(int parentIndex, int childIndex){
        int tmp = heap.get(parentIndex);
        heap.set(parentIndex, heap.get(childIndex));
        heap.set(childIndex, tmp);
    }
    
    private int getParent(int index){
        return index/2;
    }

    private int getLeftChild(int index) {
        return index * 2;
    }

    private int getRightChild(int index) {
        return index * 2 + 1;
    }
}
