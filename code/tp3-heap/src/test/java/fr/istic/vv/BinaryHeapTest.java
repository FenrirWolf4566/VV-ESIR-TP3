package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {
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

}