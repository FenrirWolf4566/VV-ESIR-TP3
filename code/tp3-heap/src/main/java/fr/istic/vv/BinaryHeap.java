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
        return null; // TODO
    }

    public T peek() {
        if(count() == 0)
            throw new NoSuchElementException();
        return array.get(0);
    }

    public void push(T element) {
        //TODO
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