package MinHeap;

public class MinHeapTester {

    public static void main(String[] args) {
        int[] numbers = {3, 4, 6, 2, 8, 1, 5, 7, 9, 15, 13, 10, 12, 11, 14};

        MinHeap<Integer> minHeap = new MinHeap<Integer>();

        for (int i = 0; i < numbers.length; i ++) {
            minHeap.insert(numbers[i]);
        }

        for (int i  = 0; i < numbers.length; i++) {
            System.out.println(minHeap.eraseMin());
        }

    }
}