package MinHeap;

public interface MinHeapADT<T> {
    public void insert(T element);
    public T eraseMin();
    public T getMin();
}