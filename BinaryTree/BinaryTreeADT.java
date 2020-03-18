package BinaryTree;

import java.util.Iterator;

/**
 * <h1>BinaryTreeADT Interface</h1>
 * 
 * Abstract Data Type for a binary tree.
 * 
 * @param <T>
 */
public interface BinaryTreeADT<T> {
    public boolean isEmpty();
    public int size();
    public boolean contains(T element);
    public T find(T element);
    public Iterator<T> inOrder();
    public Iterator<T> postOrder();
    public Iterator<T> preOrder();
    public Iterator<T> byLevel();
}