package BinaryTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * <h1>AVL Tree Class</h1>
 * 
 * Autobalancing Adelson-Velskii and Landis tree.
 * 
 * @author Antonio Lechuga
 * @version 1.0
 * @since 2020-03-20
 * 
 * @param <T>
 */
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    AVLTree() {
        super();
    }

    /* -------------------- INSERTION -------------------- */
    @Override
    public void insert(T element) {

        if (element == null)
            return;

        BinaryNode<T> node = new BinaryNode<T>(element);

        if (isEmpty()) {
            setRoot(node);
        } else {
            insert(getRoot(), node);
            setCounter(size() + 1);

            // updating equilibrium factors and balancing
            BinaryNode<T> parent;
           do {
                parent = node.getFather();
                if (node == parent.getLeft())
                   parent.setEquilibriumFactor(parent.getEquilibriumFactor() - 1);
                else
                    parent.setEquilibriumFactor(parent.getEquilibriumFactor() + 1);

                if (Math.abs(parent.getEquilibriumFactor()) == 1)
                    node = parent;
                if (Math.abs(parent.getEquilibriumFactor()) == 2) {
                    rotate(parent);
                    return;
                }
           } while (parent != getRoot() && parent.getEquilibriumFactor() != 0);
        }
    }

    private void insert(BinaryNode<T> actual, BinaryNode<T> node) {

        // normal insertion of the node in the tree
        if (node.getElement().compareTo(actual.getElement()) < 0) {
            if (actual.getLeft() == null)
                actual.hang(node);
            else
                insert(actual.getLeft(), node);
        } else {
            if (actual.getRight() == null)
                actual.hang(node);
            else
                insert(actual.getRight(), node);
        }
    }

    /* -------------------- DELETION -------------------- */
    @Override
    public T delete(T element) {
        BinaryNode<T> node = findNode(element);
        if (node == null)
            return null;

        setCounter(size() - 1);

        BinaryNode<T> affectedNode;

        if (node.getLeft() == null && node.getRight() == null) {
            // case when node is a leaf
            BinaryNode<T> parent = node.getFather();
            if (parent.getLeft() == node) {
                parent.setLeft(null);
                affectedNode = parent;
                affectedNode.setEquilibriumFactor(affectedNode.getEquilibriumFactor() + 1);
            } else {
                parent.setRight(null);
                affectedNode = parent;
                affectedNode.setEquilibriumFactor(affectedNode.getEquilibriumFactor() - 1);
            }
        } else {
            if (node.getLeft() != null && node.getRight() != null) {
                // node has both children
                BinaryNode<T> actual = node.getRight();

                while (actual.getLeft() != null)
                    actual = actual.getLeft();

                // getting affected node
                affectedNode = actual.getFather();
                if (affectedNode == node)
                    affectedNode.setEquilibriumFactor(affectedNode.getEquilibriumFactor() - 1);
                else
                    affectedNode.setEquilibriumFactor(affectedNode.getEquilibriumFactor() + 1);
                
                // reordering
                if (actual.getRight() == null) {
                    if (actual.getFather() == node)
                        actual.getFather().setRight(null);
                    else
                        actual.getFather().setLeft(null);
                } else {
                    actual.getFather().hang(actual.getRight());
                }
                node.setElement(actual.getElement());
            } else {
                // case when node has one child
                affectedNode = node.getFather();
                if (node == affectedNode.getRight())
                    affectedNode.setEquilibriumFactor(affectedNode.getEquilibriumFactor() - 1);
                else
                    affectedNode.setEquilibriumFactor(affectedNode.getEquilibriumFactor() + 1);

                if (node.getLeft() != null && node.getRight() == null)
                    node.getFather().hang(node.getLeft());
                else
                    node.getFather().hang(node.getRight());
            }
        }

        // traversing to root updating equilibrium factors
        while(Math.abs(affectedNode.getEquilibriumFactor()) != 1 && affectedNode != getSentinel()) {
            if (affectedNode.getEquilibriumFactor() == 0) {
                BinaryNode<T> parent = affectedNode.getFather();
                if (affectedNode == parent.getLeft())
                    parent.setEquilibriumFactor(parent.getEquilibriumFactor() + 1);
                else
                    parent.setEquilibriumFactor(parent.getEquilibriumFactor() - 1);

                affectedNode = parent;
            } else if (Math.abs(affectedNode.getEquilibriumFactor()) == 2) {
                rotate(affectedNode);
                break;
            }
        }

        return element;
    }

    @Override
    public T deleteMin() {
        T element = findMin();
        return delete(element);
    }

    @Override
    public T deleteMax() {
        T element = findMax();
        return delete(element);
    }

    /* -------------------- BALANCING -------------------- */
    private void rotate(BinaryNode<T> alpha) {

        BinaryNode<T> beta, gamma, A, B, C, D, parent;

        // determine case
        // left - left
        if (alpha.getEquilibriumFactor() == -2 && alpha.getLeft().getEquilibriumFactor() == -1) {
            beta = alpha.getLeft();
            gamma = beta.getLeft();
            A = gamma.getLeft();
            B = gamma.getRight();
            C = beta.getRight();
            D = alpha.getRight();
            parent = alpha.getFather();

            // rotating
            alpha.hang(C);
            beta.hang(gamma);
            beta.hang(alpha);

            if (parent == getSentinel()) {
                parent.setRight(beta);
                beta.setFather(parent);
            } else {
                parent.hang(beta);
            }

            // adjusting equilibrium factors
            alpha.setEquilibriumFactor(0);
            beta.setEquilibriumFactor(0);
        }

        // right - right
        if (alpha.getEquilibriumFactor() == 2 && alpha.getRight().getEquilibriumFactor() == 1) {
            beta = alpha.getRight();
            gamma = beta.getRight();
            A = alpha.getLeft();
            B = beta.getLeft();
            C = gamma.getLeft();
            D = gamma.getRight();
            parent = alpha.getFather();

            // rotating
            alpha.hang(B);
            beta.hang(alpha);
            beta.hang(gamma);

            if (parent == getSentinel()) {
                parent.setRight(beta);
                beta.setFather(parent);
            } else {
                parent.hang(beta);
            }

            // adjusting equilibrium factors
            alpha.setEquilibriumFactor(0);
            beta.setEquilibriumFactor(0);
        }


        // left - right
        if (alpha.getEquilibriumFactor() == -2 && alpha.getLeft().getEquilibriumFactor() == 1) {
            beta = alpha.getLeft();
            alpha.setLeft(null);
            gamma = beta.getRight();
            A = beta.getLeft();
            B = gamma.getLeft();
            C = gamma.getRight();
            D = alpha.getRight();
            parent = alpha.getFather();

            // rotating
            beta.hang(A);
            beta.hang(B);
            alpha.hang(C);
            alpha.hang(D);
            gamma.hang(beta);
            gamma.hang(alpha);

            if (parent == getSentinel()) {
                // sentinel
                parent.setRight(gamma);
                gamma.setFather(parent);
            } else {
                parent.hang(gamma);
            }

            // adjusting equilibrium factors
            if (gamma.getEquilibriumFactor() == -1) {
                beta.setEquilibriumFactor(0);
                alpha.setEquilibriumFactor(1);
            } else if (gamma.getEquilibriumFactor() == 0) {
                beta.setEquilibriumFactor(0);
                alpha.setEquilibriumFactor(0);
            } else {
                beta.setEquilibriumFactor(-1);
                alpha.setEquilibriumFactor(0);
            }

            gamma.setEquilibriumFactor(0);
        }

        // right - left
        if (alpha.getEquilibriumFactor() == 2 && alpha.getRight().getEquilibriumFactor() == -1) {
            beta = alpha.getRight();
            alpha.setRight(null);
            gamma = beta.getLeft();
            A = alpha.getLeft();
            B = gamma.getLeft();
            C = gamma.getRight();
            D = beta.getRight();
            parent = alpha.getFather();

            // rotating
            alpha.hang(A);
            alpha.hang(B);
            beta.hang(C);
            beta.hang(D);
            gamma.hang(alpha);
            gamma.hang(beta);

            if (parent == getSentinel()) {
                // sentinel
                parent.setRight(gamma);
                gamma.setFather(parent);
            } else {
                parent.hang(gamma);
            }

            // adjusting equilibrium factors
            if (gamma.getEquilibriumFactor() == -1) {
                alpha.setEquilibriumFactor(0);
                beta.setEquilibriumFactor(1);
            } else if (gamma.getEquilibriumFactor() == 0) {
                alpha.setEquilibriumFactor(0);
                beta.setEquilibriumFactor(0);
            } else {
                alpha.setEquilibriumFactor(-1);
                beta.setEquilibriumFactor(0);
            }

            gamma.setEquilibriumFactor(0);
        }
    }

    /* -------------------- DEBUGGING -------------------- */
    protected Iterator<Integer> EFByLevel() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (isEmpty())
            return list.iterator();
        
        LinkedList<BinaryNode<T>> queue = new LinkedList<BinaryNode<T>>();
        queue.add(getRoot());
        while(!queue.isEmpty()) {
            BinaryNode<T> actual = queue.removeFirst();
            list.add(actual.getEquilibriumFactor());
            if (actual.getLeft() != null)
                queue.add(actual.getLeft());
            if (actual.getRight() != null)
                queue.add(actual.getRight());
        }

        return list.iterator();
    }
}