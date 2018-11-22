package data;

import java.util.Comparator;

public class TaskQueue {

    private Task[] list;
    private int heapSize;
    private Comparator comp;

    public TaskQueue(int capacity, Comparator comp) {
        this.list = new Task[capacity * 2 + 1];
        this.heapSize = 0;
        this.comp = comp;
    }
    
    /**
     * Adds a Task to the Queue
     * @param t - The Task to be added
     */
    public void add(Task t) {
        heapSize++;
        int i = heapSize;

        while (i > 1 && comp.compare(list[parentKey(i)], t) < 0) {
            list[i] = list[parentKey(i)];
            i = parentKey(i);
        }

        list[i] = t;
    }
    
    /**
     * Returns AND deletes the Task with the highest priority
     * @return The Task with the highest priority
     * @see #peek() 
     */
    public Task poll() {
        // Return null if the heap is empty
        if (heapSize == 0) {
            return null;
        }

        Task max = list[1];
        list[1] = list[heapSize];
        heapSize--;

        heapify(1);

        return max;
    }
    
    /**
     * Returns the Task with the highest priority
     * @return The Task with the highest priority
     * @see #poll() 
     */
    public Task peek() {
        // Return null if the heap is empty
        if (heapSize == 0) {
            return null;
        }

        return list[1];
    }

    // maximum heap requirement
    // for all 1 < i <= heapSize : A[parent(i)] >= A[i]
    private void heapify(int index) {
        int left = leftKey(index);
        int right = rightKey(index);
        int largest = 0;

        if (right <= heapSize) {
            // Determine the "larger" child
            // in this application's use case the child with the longer processing time
            // This can, however be changed by using a different comparator
            if (comp.compare(list[left], list[right]) > 0) {
                largest = left;
            } else {
                largest = right;
            }

            if (comp.compare(list[index], list[largest]) < 0) {
                swap(index, largest);
                heapify(largest);
            }
        } else if (left == heapSize) {
            if (comp.compare(list[index], list[left]) < 0) {
                swap(index, left);
            }
        }
    }

    private void swap(int i1, int i2) {
        // The indexes should always be valid (checked in heapify)
        Task temp = list[i1];
        list[i1] = list[i2];
        list[i2] = temp;
    }

    private int parentKey(int index) {
        return (int) Math.floor(index / 2);
    }

    private int leftKey(int index) {
        return 2 * index;
    }

    private int rightKey(int index) {
        return 2 * index + 1;
    }
}
