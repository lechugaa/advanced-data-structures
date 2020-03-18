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
    T element;                          // binary node's element
    BinaryNode<T> left, right;          // left and right children
    BinaryNode<T> father;               // father node

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
}