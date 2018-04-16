package net.cmoaciopm.java.lang;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestClass {

    @Test
    public void test() {
        assertThat(String.class.getSimpleName(), is("String"));
        assertThat(String.class.getName(), is("java.lang.String"));
        assertThat(String.class.getCanonicalName(), is("java.lang.String"));

        String[] stringArray = new String[10];
        assertThat(stringArray.getClass().getSimpleName(), is("String[]"));
        assertThat(stringArray.getClass().getName(), is("[Ljava.lang.String;"));
        assertThat(stringArray.getClass().getCanonicalName(), is("java.lang.String[]"));

        int[] array = new int[10];
        assertThat(array.getClass().getSimpleName(), is("int[]"));
        assertThat(array.getClass().getName(), is("[I"));
        assertThat(array.getClass().getCanonicalName(), is("int[]"));
    }
}