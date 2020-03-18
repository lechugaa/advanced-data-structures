package BinaryTree;

public class BinaryNodeTester {
    public static void main(String[] args) {
        BinaryNode<Integer> n1 = new BinaryNode<Integer>(1);
        BinaryNode<Integer> n2 = new BinaryNode<Integer>(2);
        BinaryNode<Integer> n3 = new BinaryNode<Integer>(3);
        BinaryNode<Integer> n4 = new BinaryNode<Integer>(4);
        BinaryNode<Integer> n5 = new BinaryNode<Integer>(5);
        BinaryNode<Integer> n6 = new BinaryNode<Integer>(6);
        BinaryNode<Integer> n7 = new BinaryNode<Integer>(7);

        n1.setLeft(n2);
        n1.setRight(n3);

        n2.setFather(n1);
        n2.setLeft(n4);
        n2.setRight(n5);

        n3.setFather(n1);
        n3.setLeft(n6);
        n3.setRight(n7);

        n4.setFather(n2);
        n5.setFather(n2);
        n6.setFather(n3);
        n7.setFather(n3);

        System.out.println("n1 has " + n1.numberOfDescendents() + " descendents.");
        System.out.println("n2 has " + n2.numberOfDescendents() + " descendents.");
        System.out.println("n3 has " + n3.numberOfDescendents() + " descendents.");
        System.out.println("n4 has " + n4.numberOfDescendents() + " descendents.");
        System.out.println("n5 has " + n5.numberOfDescendents() + " descendents.");
        System.out.println("n6 has " + n6.numberOfDescendents() + " descendents.");
        System.out.println("n7 has " + n7.numberOfDescendents() + " descendents.");

    }
}