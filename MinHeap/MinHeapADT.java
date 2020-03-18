package MinHeap;

/**
 * <h1>MinHeapADT Interface</h1>
 * The MinHeapADT interface is the basic layout 
 * for a binary min heap implementation.
 * 
 * @param <T>
 */
public interface MinHeapADT<T extends Comparable<T>> {
    public void insert(T element);
    public T eraseMin();
    public T getMin();
}