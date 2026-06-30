class DynamicArray {

    private int[] array;
    private int curLen;
    private int capacity;

    public DynamicArray(int capacity) {
        this.array = new int[capacity];
        this.capacity = capacity;
    }

    public int get(int i) {
        return this.array[i];
    }

    public void set(int i, int n) {
        this.array[i] = n;
    }

    public void pushback(int n) {
        if (this.curLen == this.array.length) {
            resize();
        }
        this.array[curLen] = n;
        this.curLen++;
    }

    public int popback() {
        int res = this.array[this.curLen-1];
        this.curLen--;
        return res;
    }

    private void resize() {
        int[] newArray = new int[this.array.length * 2];
        System.arraycopy(this.array, 0, newArray, 0, this.array.length);
        this.array = newArray;
        this.capacity *= 2;
    }

    public int getSize() {
        return this.curLen;
    }

    public int getCapacity() {
        return this.capacity;
    }
}
