package BinaryTree;

/**
 * <h1>BinaryNode Class</h1>
 * 
 * This class implements a binary node. It can contain an element T
 * or have a null value and can point to two other binary nodes.
 * 
 * @author Antonio Lechuga
 * @version 1.0
 * @since 2020-03-17
 * @param <T>
 */
public class BinaryNode<T extends Comparable<T>> {
    private T element;                          // binary node's element
    private BinaryNode<T> left, right;          // left and right children
    private BinaryNode<T> father;               // father node

    /**
     * This method initializes a new BinaryNode instance.
     * It generates a node with the given element and with 
     * its pointers pointing to null.
     * 
     * @param element to add to the newly created binary node.
     */
    BinaryNode(T element) {
        this.element = element;
        left = null;
        right = null;
        father = null;
    }

    /* Getters and setters */
    public T getElement() {
        return element;
    }

    public BinaryNode<T> getLeft() {
        return left;
    }
    
    public void setLeft(BinaryNode<T> node) {
        left = node;
    } 

    public BinaryNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryNode<T> node) {
        right = node;
    }

    public BinaryNode<T> getFather() {
        return father;
    }

    public void setFather(BinaryNode<T> node) {
        father = node;
    } 

    /**
     * This method calculates the number of descendents a binary
     * node has.
     * 
     * @return number of descendents as an integer.
     */
    public int numberOfDescendents() {
        int counter = 0;
        if (left != null)
            counter = left.numberOfDescendents() + 1;
        if (right != null)
            counter += right.numberOfDescendents() + 1;

        return counter;
    }

    /**
     * This method joins a child node to a parent node. 
     * It takes into accoount if the node should be placed
     * to the left or right of the parent node and also
     * if the parent node is the sentinel node.
     * 
     * @param node to be added
     */
    public void hang(T element) {

        // case when the passed element is null
        if (element == null)
            return;

        BinaryNode<T> node = new BinaryNode<T>(element);

        // case when the method is called from the sentinel node
        if (this.element == null) {
            this.setRight(node);
            node.setFather(this);
            return;
        }

        // for any other node in the tree
        if (element.compareTo(this.getElement()) < 0)
            this.setLeft(node);
        else
            this.setRight(node);

        node.setFather(this);
        return;
    }
}