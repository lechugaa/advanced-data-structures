package BinaryTree;

/**
 * <h1>Binary Search Tree Class</h1>
 * Implementation of a binary search tree
 * 
 * @author Antonio Lechuga
 * @since 2020-03-19
 * @version 1.0
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    // constructor
    BinarySearchTree() {
        super();
    }

    /**
     * This method takes advantage of the structure of a search tree
     * to optimize search time.
     */
    @Override
    public boolean contains(T element) {
        if (this.getRoot() == null)
            return false;
        else
            return contains(this.getRoot(), element);
    }

    private boolean contains(BinaryNode<T> actual, T element) {
        if (element == null)
            return false;

        if (element == actual.getElement())
            return true;

        if (element.compareTo(actual.getElement()) < 0 && actual.getLeft() != null)
            return contains(actual.getLeft(), element);
        else if (actual.getRight() != null)
            return contains(actual.getRight(), element);
        else
            return false;
    }

    /**
     * This method takes advantage of the structure of a search tree
     * to optimize search time.
     */
    @Override
    public T find(T element) {
        if (this.contains(element))
            return element;
        else
            return null;
    }

    /**
     * This mehtod inserts a new node into the binary search tree.
     */
    public void insert(T element) {
        if (getRoot() != null) {
            insert(this.getRoot(), element);
            setCounter(size() + 1);
        } else
            setRoot(new BinaryNode<T>(element));
    }

    private void insert(BinaryNode<T> actual, T element) {
        if (element == null)
            return;

        if (element.compareTo(actual.getElement()) < 0) {
            if (actual.getLeft() == null)
                actual.hang(element);
            else
                insert(actual.getLeft(), element);
        } else {
            if (actual.getRight() == null)
                actual.hang(element);
            else
                insert(actual.getRight(), element);
        }
    }

    public T delete(T element) {
        // TODO Auto-generated method stub
        return null;
    }

    public T deleteMin() {
        // TODO Auto-generated method stub
        return null;
    }

    public T deleteMax() {
        // TODO Auto-generated method stub
        return null;
    }

    public T findMin() {
        // TODO Auto-generated method stub
        return null;
    }

    public T findMax() {
        // TODO Auto-generated method stub
        return null;
    }
    
}