package net.cmoaciopm.java.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TestArrayList {

    @Test
    public void testAdd() {
        ArrayList<String> list = new ArrayList<>(100);
        assertEquals(list.size(), 0);
        list.add("1st");
        assertEquals(list.size(), 1);
        list.add(1, "2nd");
        assertEquals(list.size(), 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAdd2() {
        ArrayList<String> list = new ArrayList<>(100);
        list.add(1, "abc");
    }

    @Test
    public void testIterator() {
        ArrayList<String> list = new ArrayList<>(3);
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> iterator = list.iterator();
        assertEquals(iterator.hasNext(), true);
        assertEquals(iterator.next(), "a");
        assertEquals(iterator.next(), "b");
        assertEquals(iterator.next(), "c");
        assertEquals(iterator.hasNext(), false);
    }

    @Test(expected = IllegalStateException.class)
    public void testIterator2() {
        ArrayList<String> list = new ArrayList<>(3);
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> iterator = list.iterator();
        iterator.remove();
    }

    @Test
    public void testIterator3() {
        ArrayList<String> list = new ArrayList<>(3);
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.remove();
        assertEquals(list.size(), 2);
        assertEquals(list.get(0), "b");
        assertEquals(list.get(1), "c");
    }

    @Test
    public void testIterator4() {
        ArrayList<String> list = new ArrayList<>(3);
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();
        assertEquals(list.size(), 2);
        assertEquals(list.get(0), "a");
        assertEquals(list.get(1), "c");
    }

    @Test(expected = IllegalStateException.class)
    public void testIterator5() {
        ArrayList<String> list = new ArrayList<>(3);
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();
        iterator.remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIterator6() {
        ArrayList<String> list = new ArrayList<>(3);
        list.add("a");
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.next();
    }

    @Test
    public void testIterator7() {
        ArrayList<String> list = new ArrayList<>(3);
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        assertEquals(list.size(), 0);
    }

    @Test
    public void testListIterator() {
        ArrayList<String> list = new ArrayList<>(3);
        list.add("a");
        list.add("b");
        list.add("c");
        ListIterator<String> iterator = list.listIterator();
        assertEquals(iterator.previousIndex(), -1);
        assertEquals(iterator.nextIndex(), 0);
        assertFalse(iterator.hasPrevious());
        iterator.next();
        assertEquals(iterator.previousIndex(), 0);
        assertEquals(iterator.nextIndex(), 1);
    }

    @Test(expected = NoSuchElementException.class)
    public void testListIterator2() {
        ArrayList<String> list = new ArrayList<>(3);
        list.add("a");
        list.add("b");
        list.add("c");
        ListIterator<String> iterator = list.listIterator();
        iterator.previous();
    }

    @Test
    public void testListIterator3() {
        ArrayList<String> list = new ArrayList<>(3);
        list.add("a");
        list.add("b");
        list.add("c");
        ListIterator<String> iterator = list.listIterator(1);
        assertEquals(iterator.previousIndex(), 0);
        assertEquals(iterator.nextIndex(), 1);
        assertTrue(iterator.hasPrevious());
        assertEquals(iterator.previous(), "a");
        assertEquals(iterator.next(), "a");
    }

    @Test(expected = IllegalStateException.class)
    public void testListIterator4() {
        ArrayList<String> list = new ArrayList<>(3);
        list.add("a");
        list.add("b");
        list.add("c");
        ListIterator<String> iterator = list.listIterator(1);
        iterator.set("x");
    }

    @Test
    public void testListIterator5() {
        ArrayList<String> list = new ArrayList<>(3);
        list.add("a");
        list.add("b");
        list.add("c");
        ListIterator<String> iterator = list.listIterator(1);
        iterator.next();
        iterator.set("x");
        assertEquals(list.size(), 3);
        assertEquals(list.get(0), "a");
        assertEquals(list.get(1), "x");
        assertEquals(list.get(2), "c");
    }
}
