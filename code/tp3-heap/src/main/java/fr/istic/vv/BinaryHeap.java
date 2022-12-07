package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

class BinaryHeap<T> {

    List<T> array;
    Comparator<T> comparator;


    public BinaryHeap(Comparator<T> comparator) {
        array = new ArrayList<>();
        this.comparator = comparator;
    }

    public T pop() {
        // Replace the root of the heap with the last element on the last level.
        T e = peek();

        T last = array.remove(array.size()-1);

        if(array.size() == 0)
            return e;

        array.set(0, last);

        int index = 0;
        while(true){
            int left = getChildLeftIndex(index);
            int right = getChildRightIndex(index);

            int min = index;

            // Compare the new root with its children; if they are in the correct order, stop.
            if(left < array.size() && comparator.compare(array.get(left), array.get(min)) < 0)
                min = left;
            if(right < array.size() && comparator.compare(array.get(right), array.get(min)) < 0)
                min = right;

            // If not, swap the element with one of its children and return to the previous step.
            // (Swap with its smaller child in a min-heap and its larger child in a max-heap.)
            if(min != index){
                swap(index, min);
                index = min;
            }else{
                break;
            }
        }
        return e;
    }

    public T peek() {
        if(count() == 0)
            throw new NoSuchElementException();
        return array.get(0);
    }

    public void push(T element) {
        // Add at the bottom
        array.add(element);
        int index = array.size()-1;
        int parent = getParentIndex(index);
        // Compare added with parent, if they are in the correct order, stop.
        while(comparator.compare(array.get(index), array.get(parent)) < 0) {
            // If not, swap the element with its parent and return to the previous step.
            swap(parent, index);
            index = parent;
            parent = getParentIndex(index);
        }
    }

    private void swap(int a, int b){
        T tmp = array.get(a);
        array.set(a, array.get(b));
        array.set(b, tmp);
    }

    public int count() { return array.size(); }

    private static int getChildLeftIndex(int i){
        return 2*i+1;
    }
    private static int getChildRightIndex(int i){
        return 2*i+2;
    }
    private static int getParentIndex(int i){
        return (i-1)/2;
    }

}