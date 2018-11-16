package data;

public class TaskList {

    private Task[] list;
    private int size;
    private int capacity;

    public TaskList() {
        capacity = 8;
        list = new Task[capacity]; // O(8) = O(1)
        size = 0;
    }

    /**
     *
     * @return The number of Tasks on the list
     */
    public int size() {
        return size;
    }

    /**
     *
     * @return The current capacity of the List
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Add a Task to the list, doubling the capacity if required.
     *
     * @param t - The Task to be added to the list
     */
    public void add(Task t) {
        if (size < capacity / 2) {
            list[size] = t; // O(1)
            size++;
        } else {
            // Increase the capacity if the list is 50% full
            increaseCapacity();
            // Add the new task
            list[size] = t;
            size++;
        }
    }

    /**
     * Remove a Task from the given index, provided it is valid. Halves the
     * capacity if possible.
     *
     * @param index - The index of the Task to be removed
     */
    public void remove(int index) {
        if (index < size) {
            list[index] = null;

            for (int i = index; i < size; i++) {
                list[i] = list[i + 1];
            }

            size--;
            if (size < capacity / 2) {
                // Decrease the capacity if the list is less than 50% full after the removal
                decreaseCapacity();
            }

        }
    }

    /**
     *
     * @param index - The index to be fetched from
     * @return The Task at the given index, provided it is valid. Otherwise
     * null.
     */
    public Task get(int index) {
        // Return null if the index is out of bounds
        if (index >= size || index < 0) {
            return null;
        }

        return list[index];
    }

    /**
     * Checks whether or not the inputted Task is on the list.
     *
     * @param t - The Task to be searched for
     * @return True if the Task is on the list, False otherwise
     */
    public Boolean contains(Task t) {
        for (int i = 0; i < size; i++) {
            if (list[i] == t) {
                return true;
            }
        }

        return false;
    }

    /**
     * Swaps the contents of two indexes on the list, provided they are valid
     *
     * @param index1
     * @param index2
     */
    public void swap(int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        
        if (index1 < 0 || index2 < 0) {
            return;
        }
        
        if (index1 < size && index2 < size) {
            Task temp = list[index1];
            list[index1] = list[index2];
            list[index2] = temp;
        }
    }

    private void increaseCapacity() {
        // Doubles the capacity of the list
        capacity = capacity * 2;
        Task[] newList = new Task[capacity]; // O(2n) = O(n)
        for (int i = 0; i < size; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }

    private void decreaseCapacity() {
        // Halves the capacity of the list
        capacity = capacity / 2;
        Task[] newList = new Task[capacity];
        for (int i = 0; i < size; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }

    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < size; i++) {
            s += list[i] + ", ";
        }
        s += "]";
        return s;
    }

}
