package net.cmoaciopm.java.util;


import org.junit.Test;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import sun.misc.JavaLangAccess;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class TestEnumMap {

    enum Shape {
       TRIANGLE,
       SQUARE,
       ROUND,
    }

    @Test
    public void test() {
        EnumMap<Shape, String> map = new EnumMap<>(Shape.class);
        map.put(Shape.SQUARE, "square");
        map.put(Shape.ROUND, "round");
        map.put(Shape.TRIANGLE, "triangle");
        assertThat(map.size(), is(3));
        Set<Shape> keySet = map.keySet();
        Iterator<Shape> keyIterator = keySet.iterator();
        assertThat(keyIterator.next(), is(Shape.TRIANGLE));
        assertThat(keyIterator.next(), is(Shape.SQUARE));
        assertThat(keyIterator.next(), is(Shape.ROUND));
        assertThat(keyIterator.hasNext(), is(false));
        Collection<String> values = map.values();
        Iterator<String> valueIterator = values.iterator();
        assertThat(valueIterator.next(), is("triangle"));
        assertThat(valueIterator.next(), is("square"));
        assertThat(valueIterator.next(), is("round"));
        assertThat(valueIterator.hasNext(), is(false));
    }

    @Test(expected = NullPointerException.class)
    public void testInsertNullKey() {
        EnumMap<Shape, String> map = new EnumMap<>(Shape.class);
        map.put(null, "abc");
    }

    @Test
    public void testInsertNullValue() {
        EnumMap<Shape, String> map = new EnumMap<>(Shape.class);
        map.put(Shape.TRIANGLE, null);
        assertThat(map.size(), is(1));
        assertThat(map.values().iterator().hasNext(), is(true));
        assertThat(map.values().iterator().next(), is(nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMapConstructor() {
        Map emptyMap = new HashMap();
        new EnumMap(emptyMap);
    }

    @Test(expected = ClassCastException.class)
    public void testNonEnumMapConstructor() {
        Map map = new HashMap();
        map.put("a", 1);
        new EnumMap(map);
    }

    @Test
    public void testNonEnumMaConstructor2() {
        Map map = new HashMap();
        map.put(Shape.TRIANGLE, 1);
        new EnumMap(map);
    }

    @Test
    public void testJavaLangAccess() {
        sun.misc.JavaLangAccess langAccess = sun.misc.SharedSecrets.getJavaLangAccess();
        Shape[] shapes = langAccess.getEnumConstantsShared(Shape.class);
        assertThat(shapes.length, is(3));
        assertThat(shapes[0], is(Shape.TRIANGLE));
        assertThat(shapes[1], is(Shape.SQUARE));
        assertThat(shapes[2], is(Shape.ROUND));
    }
}
