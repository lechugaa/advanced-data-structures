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
     * 
     * @param element to search
     * @return boolean whether element is contained in tree or not
     */
    @Override
    public boolean contains(T element) {
        if (this.isEmpty())
            return false;
        else
            return contains(getRoot(), element);
    }

    // internal method used for contains public method
    // it recursively searches for element comparing with actual.getElement()
    // to see if the search should continue to the right or the left
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
     * This method returns the node containing the given element.
     * @param element the node has to have
     * @return BinaryNode<T> containing the element or null
     */
    public BinaryNode<T> findNode(T element) {
        if (isEmpty())
            return null;
        else
            return findNode(element, getRoot());
    }


    // internal method used to implement public findNode
    private BinaryNode<T> findNode(T element, BinaryNode<T> actual) {
        if (element == null)
            return null;

        if (actual.getElement() == element)
            return actual;

        if (element.compareTo(actual.getElement()) < 0 && actual.getLeft() != null) 
            return findNode(element, actual.getLeft());
        else if (actual.getRight() != null)
            return findNode(element, actual.getRight());
        else
            return null;
    }

    /**
     * This method takes advantage of the structure of a search tree
     * to optimize search time.
     * 
     * @return element or null if it is not contained in tree.
     * @param element to search
     */
    @Override
    public T find(T element) {
        if (this.contains(element))
            return element;
        else
            return null;
    }

    /**
     * This method inserts a new node into the binary search tree.
     * It does not perform balancing.
     */
    public void insert(T element) {
        if (!isEmpty()) {
            insert(getRoot(), element);
            setCounter(size() + 1);
        } else if(element != null)
            setRoot(new BinaryNode<T>(element));
    }

    private void insert(BinaryNode<T> actual, T element) {
        if (element == null)
            return;

        if (element.compareTo(actual.getElement()) < 0) {
            if (actual.getLeft() == null)
                actual.hang(new BinaryNode<T>(element));
            else
                insert(actual.getLeft(), element);
        } else {
            if (actual.getRight() == null)
                actual.hang(new BinaryNode<T>(element));
            else
                insert(actual.getRight(), element);
        }
    }

    public T delete(T element) {
        BinaryNode<T> node = findNode(element);
        if (node == null)
            return null;

        setCounter(size() - 1);

        if (node.getLeft() == null && node.getRight() == null) {
            // case when node is a leaf
            BinaryNode<T> parent = node.getFather();
            if (parent.getLeft() == node)
                parent.setLeft(null);
            else
                parent.setRight(null);
        } else {
            if (node.getLeft() != null && node.getRight() != null) {
                // node has both children
                BinaryNode<T> actual = node.getRight();

                while (actual.getLeft() != null)
                    actual = actual.getLeft();

                if (actual.getRight() == null)
                    actual.getFather().setLeft(null);
                else
                    actual.getFather().hang(actual.getRight());

                node.setElement(actual.getElement());
            } else {
                // case when node has one child
                if (node.getLeft() != null && node.getRight() == null)
                    node.getFather().hang(node.getLeft());
                else
                    node.getFather().hang(node.getRight());
            }
        }
        return node.getElement();
    }

    public T deleteMin() {
        T element = findMin();
        return delete(element);
    }

    public T deleteMax() {
        T element = findMax();
        return delete(element);
    }

    public T findMin() {
        if (isEmpty())
            return null;
        return getMinNode(getRoot()).getElement();
    }

    private BinaryNode<T> getMinNode(BinaryNode<T> actual) {
        if (actual.getLeft() == null)
            return actual;
        else
            return getMinNode(actual.getLeft());
    }

    public T findMax() {
        if (isEmpty())
            return null;
        return getMaxNode(getRoot()).getElement();
    }

    private BinaryNode<T> getMaxNode(BinaryNode<T> actual) {
        if (actual.getRight() == null) 
            return actual;
        return getMaxNode(actual.getRight());
    }
    
}