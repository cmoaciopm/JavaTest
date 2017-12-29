package net.cmoaciopm.java.util;

import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestLinkedHashMap {
    @Test
    public void testInsertionOrder() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("1", "One");
        map.put("2", "Two");
        map.put("3", "Three");
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        assertThat(iterator.next(), is("1"));
        assertThat(iterator.next(), is("2"));
        assertThat(iterator.next(), is("3"));
        assertThat(iterator.hasNext(), is(false));

        Collection<String> values = map.values();
        iterator = values.iterator();
        assertThat(iterator.next(), is("One"));
        assertThat(iterator.next(), is("Two"));
        assertThat(iterator.next(), is("Three"));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void testInsertionOrder2() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("1", "One");
        map.put("2", "Two");
        map.put("3", "Three");
        map.put("2", "Four");
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        assertThat(iterator.next(), is("1"));
        assertThat(iterator.next(), is("2"));
        assertThat(iterator.next(), is("3"));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void testInsertionOrder3() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("1", "One");
        map.put("2", "Two");
        map.put("3", "Three");
        map.remove("2");
        map.put("2", "Two");
        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        assertThat(iterator.next(), is("1"));
        assertThat(iterator.next(), is("3"));
        assertThat(iterator.next(), is("2"));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void testAccessOrder() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>(3, 0.75f, true);
        map.put("1", "One");
        map.put("2", "Two");
        map.put("3", "Three");
        map.get("1");
        map.get("3");

        Set<String> keys = map.keySet();
        Iterator<String> iterator = keys.iterator();
        assertThat(iterator.next(), is("2"));
        assertThat(iterator.next(), is("1"));
        assertThat(iterator.next(), is("3"));
        assertThat(iterator.hasNext(), is(false));

        Collection<String> values = map.values();
        iterator = values.iterator();
        assertThat(iterator.next(), is("Two"));
        assertThat(iterator.next(), is("One"));
        assertThat(iterator.next(), is("Three"));
        assertThat(iterator.hasNext(), is(false));
    }
}
