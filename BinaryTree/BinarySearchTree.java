package BinaryTree;

public class BinarySearchTree<T extends Comparable<T>> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    BinarySearchTree() {
        super();
    }

    @Override
    public boolean contains(T element) {
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
            return contains(actual.getFather(), element);
        else
            return false;
    }

    @Override
    public T find(T element) {
        if (contains(this.getRoot(), element))
            return element;
        else
            return null;
    }

    public void insert(T element) {
        // TODO Auto-generated method stub

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