package net.cmoaciopm.java.util;

import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class TestHashMap {

    @Test
    public void testPut() {
        HashMap<String, String> map = new HashMap<>();
        String old = map.put("1", "dummy");
        assertNull(old);
        old = map.put("1", "One");
        assertThat(old, is("dummy"));
    }

    @Test
    public void testNullValue() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", null);
        map.put("2", null);
        map.put("3", "Three");
        assertThat(map.get("1"), is(nullValue()));
        assertThat(map.get("2"), is(nullValue()));
        assertThat(map.get("3"), is(not(nullValue())));
        assertThat(map.get("4"), is(nullValue()));
    }

     @Test
     public void testNullKey() {
         HashMap<String, String> map = new HashMap<>();
         map.put(null, "1");
         assertThat(map.get(null), is("1"));
         map.put(null, "2");
         assertThat(map.get(null), is("2"));
     }
}
