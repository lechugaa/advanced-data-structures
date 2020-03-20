package BinaryTree;

public class BinarySearchTree<T extends Comparable<T>> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {

    BinarySearchTree() {
        super();
    }

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

    @Override
    public T find(T element) {
        if (this.contains(element))
            return element;
        else
            return null;
    }

    public void insert(T element) {
        if (getRoot() != null)
            insert(this.getRoot(), element);
        else
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