package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {
    final static int N = 1000;

    @Test
    void empty(){
        BinaryHeap<Integer> b = new BinaryHeap<>(Integer::compare);

        assertEquals(0, b.count());
        assertThrows(NoSuchElementException.class, b::pop);
        assertThrows(NoSuchElementException.class, b::peek);
    }

    @Test
    void oneElement(){
        BinaryHeap<Integer> b = new BinaryHeap<>(Integer::compare);

        b.push(1);
        assertEquals(1, b.count());
        assertEquals(1, b.peek());
        assertEquals(1, b.pop());

        assertEquals(0, b.count());
        assertThrows(NoSuchElementException.class, b::peek);
        assertThrows(NoSuchElementException.class, b::pop);
    }

    @Test
    void nElementsSorted(){
        for(int i=0; i<N; i++){
            BinaryHeap<Integer> b = new BinaryHeap<>(Integer::compare);

            for(int j=0; j<i; j++)
                b.push(j);
            for(int j=0; j<i; j++)
                assertEquals(j, b.pop(), "n=" + i);
        }
    }

    @Test
    void nElementsReverseSorted(){
        for(int i=0; i<N; i++){
            BinaryHeap<Integer> b = new BinaryHeap<>(Integer::compare);

            for(int j=0; j<i; j++)
                b.push(i-j-1);
            for(int j=0; j<i; j++)
                assertEquals(j, b.pop(), "n=" + i);
        }
    }

    @Test
    void random(){
        Random r = new Random(13597);

        for(int i=0; i<N; i++){
            List<Integer> list = new ArrayList<>();
            BinaryHeap<Integer> b = new BinaryHeap<>(Integer::compare);
            for(int j=0; j<i; j++) {
                int n = r.nextInt();
                list.add(n);
                b.push(n);
            }

            list.sort(Integer::compare);

            for(int j : list){
                assertEquals(j, b.pop(), "N=" + i);
            }
        }
    }

}