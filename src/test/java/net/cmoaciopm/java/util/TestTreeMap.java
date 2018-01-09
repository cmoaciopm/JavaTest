package net.cmoaciopm.java.util;


import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestTreeMap {

    @Test
    public void test() {
        TreeMap<Integer, String> map = generateSimpleTreeMap();
        Set<Map.Entry<Integer, String>> entrySet =  map.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            assertThat(entry.getKey(), is(new Integer(i)));
            assertThat(entry.getValue(), is(String.valueOf(i)));
            i++;
        }
    }

    @Test
    public void testFirst() {
        TreeMap<Integer, String> map = generateSimpleTreeMap();
        assertThat(map.firstKey(), is(new Integer(1)));
        assertThat(map.firstEntry().getValue(), is(String.valueOf(1)));
    }

    @Test
    public void testLast() {
        TreeMap<Integer, String> map = generateSimpleTreeMap();
        assertThat(map.lastKey(), is(new Integer(5)));
        assertThat(map.lastEntry().getValue(), is(String.valueOf(5)));
    }

    @Test
    public void testCeiling() {
        TreeMap<Integer, String> map = generateSimpleTreeMap();
        Integer key = new Integer(3);
        assertThat(map.ceilingKey(key), is(new Integer(3)));
        assertThat(map.ceilingEntry(key).getValue(), is(String.valueOf(3)));
    }

    @Test
    public void testFloor() {
        TreeMap<Integer, String> map = generateSimpleTreeMap();
        Integer key = new Integer(3);
        assertThat(map.floorKey(key), is(new Integer(3)));
        assertThat(map.floorEntry(key).getValue(), is(String.valueOf(3)));
    }

    @Test
    public void testHigher() {
        TreeMap<Integer, String> map = generateSimpleTreeMap();
        Integer key = new Integer(3);
        assertThat(map.higherKey(key), is(new Integer(4)));
        assertThat(map.higherEntry(key).getValue(), is(String.valueOf(4)));
    }

    @Test
    public void testLower() {
        TreeMap<Integer, String> map = generateSimpleTreeMap();
        Integer key = new Integer(3);
        assertThat(map.lowerKey(key), is(new Integer(2)));
        assertThat(map.lowerEntry(key).getValue(), is(String.valueOf(2)));
    }



    private TreeMap<Integer, String> generateSimpleTreeMap() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(3, "3");
        map.put(5, "5");
        map.put(1, "1");
        map.put(2, "2");
        map.put(4, "4");
        return map;
    }
}
