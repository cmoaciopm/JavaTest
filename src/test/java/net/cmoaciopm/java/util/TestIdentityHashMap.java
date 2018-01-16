package net.cmoaciopm.java.util;


import org.junit.Test;

import java.util.IdentityHashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class TestIdentityHashMap {
    @Test
    public void test() {
        IdentityHashMap<String, Integer> map = new IdentityHashMap<>(1);
        String a = "a";
        String anotherA = "a";
        String dupA = a;
        map.put(a, 1);
        assertThat(map.get(new String("a")), is(nullValue()));
        assertThat(map.get(a), is(1));
        assertThat(map.get("a"), is(1));
        assertThat(map.get(anotherA), is(1));
        assertThat(map.get(dupA), is(1));
    }
}
