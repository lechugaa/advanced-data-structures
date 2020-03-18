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

        n1.left = n2;
        n1.right = n3;

        n2.father = n1;
        n2.left = n4;
        n2.right = n5;

        n3.father = n1;
        n3.left = n6;
        n3.right = n7;

        n4.father = n2;
        n5.father = n2;
        n6.father = n3;
        n7.father = n3;

        System.out.println("n1 has " + n1.numberOfDescendents() + " descendents.");
        System.out.println("n2 has " + n2.numberOfDescendents() + " descendents.");
        System.out.println("n3 has " + n3.numberOfDescendents() + " descendents.");
        System.out.println("n4 has " + n4.numberOfDescendents() + " descendents.");
        System.out.println("n5 has " + n5.numberOfDescendents() + " descendents.");
        System.out.println("n6 has " + n6.numberOfDescendents() + " descendents.");
        System.out.println("n7 has " + n7.numberOfDescendents() + " descendents.");

    }
}