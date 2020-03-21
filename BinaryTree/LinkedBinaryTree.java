package BinaryTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * <h1>LinkedBinaryTree Class</h1>
 * This class implements a linked binary tree without balancing.
 * It does not have an insert policy so the class in not functional.
 * 
 * @author Antonio Lechuga
 * @version 1.0
 * @since 2020-03-18
 * @param <T>
 */
public class LinkedBinaryTree<T extends Comparable<T>> implements BinaryTreeADT<T> {

    private BinaryNode<T> sentinel, root;
    private int counter;

    LinkedBinaryTree() {
        sentinel = new BinaryNode<T>(null);
        root = null;
        counter = 0;
    }

    protected BinaryNode<T> getRoot() {
        return root;
    }

    protected BinaryNode<T> getSentinel() {
        return sentinel;
    }

    public boolean isEmpty() {
        return (counter == 0);
    }

    public int size() {
        return counter;
    }

    public int height() {

        if (isEmpty()) {
            return -1;
        }

        return getRoot().height();
    }

    protected void setCounter(int value) {
        this.counter = value;
    }

    public Iterator<T> inOrder() {
        ArrayList<T> list = new ArrayList<T>();
        inOrder(root, list);
        return list.iterator();
    }

    private void inOrder(BinaryNode<T> actual, ArrayList<T> list) {
        if (actual == null)
            return;
        inOrder(actual.getLeft(), list);
        list.add(actual.getElement());
        inOrder(actual.getRight(), list);
    }

    public Iterator<T> postOrder() {
        ArrayList<T> list = new ArrayList<T>();
        postOrder(root, list);
        return list.iterator();
    }

    private void postOrder(BinaryNode<T> actual, ArrayList<T> list) {
        if (actual == null)
            return;
        postOrder(actual.getLeft(), list);
        postOrder(actual.getRight(), list);
        list.add(actual.getElement());
    }

    public Iterator<T> preOrder() {
        ArrayList<T> list = new ArrayList<T>();
        preOrder(root, list);
        return list.iterator();
    }

    private void preOrder(BinaryNode<T> actual, ArrayList<T> list) {
        if (actual == null)
            return;
        list.add(actual.getElement());
        preOrder(actual.getLeft(), list);
        preOrder(actual.getRight(), list);
    }

    public Iterator<T> byLevel() {
        ArrayList<T> list = new ArrayList<T>();
        if (isEmpty())
            return list.iterator();
        
        LinkedList<BinaryNode<T>> queue = new LinkedList<BinaryNode<T>>();
        queue.add(root);
        while(!queue.isEmpty()) {
            BinaryNode<T> actual = queue.removeFirst();
            list.add(actual.getElement());
            if (actual.getLeft() != null)
                queue.add(actual.getLeft());
            if (actual.getRight() != null)
                queue.add(actual.getRight());
        }

        return list.iterator();
    }

    public boolean contains(T element) {
        Iterator<T> inOrderIterator = inOrder();
        while (inOrderIterator.hasNext()) {
            T temp = inOrderIterator.next();
            if (temp == element)
                return true;
        }
        return false;
    }

    public T find(T element) {
        Iterator<T> inOrderIterator = inOrder();
        while (inOrderIterator.hasNext()) {
            T temp = inOrderIterator.next();
            if (temp == element)
                return temp;
        }
        return null;
    }

    protected void setRoot(BinaryNode<T> node) {
        root = node;
        sentinel.setRight(root);
        root.setFather(sentinel);
        counter = node.numberOfDescendents() + 1;
    }

    /**
     * This method prints the tree by level. 
     * The node's element and its equilibrium 
     * factor is printed with the format 
     * [element(equilibrium factor)]
     */
    public void printTreeByLevel() {
        if (isEmpty()) {
            System.out.println("Empty tree");
            return;
        }
        
        LinkedList<BinaryNode<T>> queue = new LinkedList<BinaryNode<T>>();
        queue.add(root);
        while(!queue.isEmpty()) {
            BinaryNode<T> actual = queue.removeFirst();
            System.out.print("[" + actual.getElement() + "(" + actual.getEquilibriumFactor() + ")] ");
            if (actual.getLeft() != null)
                queue.add(actual.getLeft());
            if (actual.getRight() != null)
                queue.add(actual.getRight());
        }
        System.out.print("\n");
    }
}