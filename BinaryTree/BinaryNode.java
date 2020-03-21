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
    private int equilibriumFactor;

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
        equilibriumFactor = 0;
    }

    /* Getters and setters */
    public T getElement() {
        return element;
    }

    public void setElement(T newElement) {
        this.element = newElement;
    }

    public BinaryNode<T> getLeft() {
        return left;
    }

    public boolean hasLeft() {
        return getLeft() != null;
    }
    
    public void setLeft(BinaryNode<T> node) {
        left = node;
    } 

    public BinaryNode<T> getRight() {
        return right;
    }

    public boolean hasRight() {
        return getRight() != null;
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

    public int getEquilibriumFactor() {
        return equilibriumFactor;
    }

    public void setEquilibriumFactor(int ef) {
        equilibriumFactor = ef;
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
    
    public int height() {
        return height(this);
    }

    private int height(BinaryNode<T> actual) {

        if (!actual.hasLeft() && !actual.hasRight()) {
            return 0;
        } else if (!actual.hasLeft() && actual.hasRight()) {
            return height(actual.getRight()) + 1;
        } else if (actual.hasLeft() && !actual.hasRight()) {
            return height(actual.getLeft()) + 1;
        } else {
            return Math.max(height(actual.getLeft()), height(actual.getRight())) + 1;
        }

    }

    /**
     * This method joins a child node to a parent node. 
     * It takes into accoount if the node should be placed
     * to the left or right of the parent node and also
     * if the parent node is the sentinel node.
     * 
     * @param node to be added
     */
    public void hang(BinaryNode<T> node) {

        // case when the passed node is null
        if (node == null)
            return;

        // when node is asked to hang parent node
        if (node == getFather()) {
            if (this == getFather().getLeft()) {
                getFather().setLeft(null);
            } else {
                getFather().setRight(null);
            }
        }

        // case when the method is called from the sentinel node
        if (this.element == null) {
            this.setRight(node);
            node.setFather(this);
            return;
        }

        // for any other node in the tree
        if (node.getElement().compareTo(this.getElement()) < 0)
            this.setLeft(node);
        else
            this.setRight(node);

        node.setFather(this);
        return;
    }
}