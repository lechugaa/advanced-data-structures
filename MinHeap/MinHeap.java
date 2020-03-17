package MinHeap;

import java.util.Arrays;

/**
 * <h1>MinHeap</h1>
 * The MinHeap class implements an array based binary min heap,
 * for simplicity, the first element of the array is always null.
 * MinHeap class has insert, eraseMin and getMin methods.
 * 
 * @author: Antonio Lechuga
 * @version: 1.0
 * @since: 2020-03-17
*/
public class MinHeap<T extends Comparable<T>> implements MinHeapADT<T> {

    private T[] data;
    private int counter;
    private int capacity;

    @SuppressWarnings("unchecked")
    MinHeap() {
        capacity = MinHeapConstants.DEFAULT_CAPACITY;
        data = (T[]) new Comparable[capacity];
        counter = MinHeapConstants.FIRST_INDEX;
    }

    /**
     * This method is used to insert a new element into the heap.
     * It performs the insertion while preseving the min heap 
     * structure.
     * @param element This is the element that is going to be inserted
     */
    public void insert(T element) {
        
        if (counter >= capacity) {
            extendCapacityByTimes(2);
        }

        data[counter] = element;
        int actualIndex = counter;
        int dadIndex = actualIndex >> 1;
        counter++;

        while(actualIndex > MinHeapConstants.FIRST_INDEX && data[actualIndex].compareTo(data[dadIndex]) < 0) {
            T temp = data[dadIndex];
            data[dadIndex] = data[actualIndex];
            data[actualIndex] = temp;

            actualIndex = dadIndex;
            dadIndex = actualIndex >> 1; 
        }
    }

    // extends capacity of array in case it is going to be exceeded
    private void extendCapacityByTimes(int factor) {
        capacity *= factor;
        data = Arrays.copyOf(data, capacity);
    }

    /**
         * This method erases de minimum element from the heap.
         * Ir performs the deletion while preserving the min heap
         * structre.
         * 
         * @return Deleted element
    */
    public T eraseMin() {
        
        if (counter == MinHeapConstants.FIRST_INDEX) 
            return null;

        T result = data[MinHeapConstants.FIRST_INDEX];
        counter--;
        data[MinHeapConstants.FIRST_INDEX] = data[counter];

        int dadIndex = MinHeapConstants.FIRST_INDEX;
        int leftSonIndex = dadIndex << 1;
        int rightSonIndex = leftSonIndex + 1;

        while (leftSonIndex < counter) {
            if (rightSonIndex < counter) {
                int minIndex = (data[leftSonIndex].compareTo(data[rightSonIndex]) < 0) ? leftSonIndex : rightSonIndex;
                if (data[minIndex].compareTo(data[dadIndex]) < 0) {
                    T temp = data[dadIndex];
                    data[dadIndex] = data[minIndex];
                    data[minIndex] = temp;
                    
                    dadIndex = minIndex;
                    leftSonIndex = dadIndex << 1;
                    rightSonIndex = leftSonIndex + 1;
                } else {
                    return result;
                }
            } else {
                if (data[leftSonIndex].compareTo(data[dadIndex]) < 0) {
                    T temp = data[dadIndex];
                    data[dadIndex] = data[leftSonIndex];
                    data[leftSonIndex] = temp;
                    return result;
                } else {
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * This method returns the minimum element in the heap
     * without affecting it. It is a read-only operation.
     * 
     * @return Minimum element in heap
     */
    public T getMin() {
        if (counter == 1) {
            return null;
        }
        return data[1];
    }
}

final class MinHeapConstants {
    public static int DEFAULT_CAPACITY = 10;
    public static int FIRST_INDEX = 1;
}