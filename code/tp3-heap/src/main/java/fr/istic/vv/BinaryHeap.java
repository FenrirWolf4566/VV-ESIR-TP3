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
        int index = getMinElementIndex();

        swap(0, index);
        return null; // TODO
    }

    public T peek() { return array.get(getMinElementIndex()); }

    private int getMinElementIndex(){
        if(array.isEmpty())
            throw new NoSuchElementException();
        int index = 0;
        while(getChild1Index(index) < array.size())
            index = getChild1Index(index);
        return index;
    }

    public void push(T element) {
        array.add(element);

        int child = array.size()-1;
        int parent = getParentIndex(child);

        while(comparator.compare(array.get(parent), array.get(child)) < 0){
            swap(parent, child);

            child = parent;
            parent = getParentIndex(child);
        }
    }

    private void swap(int a, int b){
        T tmp = array.get(a);
        array.set(a, array.get(b));
        array.set(b, tmp);
    }

    public int count() { return array.size(); }

    private static int getChild1Index(int i){
        return 2*i+1;
    }
    private static int getChild2Index(int i){
        return 2*i+2;
    }
    private static int getParentIndex(int i){
        return (i-1)/2;
    }

}