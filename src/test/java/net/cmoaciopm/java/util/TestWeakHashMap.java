package net.cmoaciopm.java.util;


import org.junit.Test;

import java.util.WeakHashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestWeakHashMap {

    @Test
    public void test() throws InterruptedException {
        int length = 100;
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        for (int i = 0; i < length; i++) {
            map.put(new Integer(i), String.valueOf(i));
        }
        assertThat(map.size(), is(length));
        System.gc();

        Thread.sleep(1000);
        assertThat(map.size(), is(0));
    }
}
