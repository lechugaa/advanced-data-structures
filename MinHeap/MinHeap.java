package MinHeap;

import java.util.Arrays;

public class MinHeap<T extends Comparable<T>> implements MinHeapADT<T> {

    private T[] data;
    private int counter;
    private int capacity;

    MinHeap() {
        capacity = MinHeapConstants.DEFAULT_CAPACITY;
        data = (T[]) new Comparable[capacity];
        counter = MinHeapConstants.FIRST_INDEX;
    }

    @Override
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

    private void extendCapacityByTimes(int factor) {
        capacity *= factor;
        data = Arrays.copyOf(data, capacity);
    }

    @Override
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

    @Override
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