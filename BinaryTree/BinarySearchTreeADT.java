package BinaryTree;

/**
 * <h1>BinarySearchTreeADT Interface</h1>
 * 
 * @param <T>
 */
public interface BinarySearchTreeADT<T extends Comparable<T>> extends BinaryTreeADT<T> {
    public void insert(T element);
    public T delete(T element);
    public T deleteMin();
    public T deleteMax();
    public T findMin();
    public T findMax();
}