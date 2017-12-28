package net.cmoaciopm.java.other;


import org.junit.Test;

import java.math.BigInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestShift {

    @Test
    public void test() {
        assertThat(1 << 0, is(pow(2, 0)));
        assertThat(1 << 1, is(pow(2, 1)));
        assertThat(1 << 2, is(pow(2, 2)));
        assertThat(1 << 3, is(pow(2, 3)));
    }

    @Test
    public void test2() {
        int i = 1;
        i <<= 2;
        assertThat(i, is(4));
    }

    private int pow(int base, int exponent) {
        return BigInteger.valueOf(base).pow(exponent).intValue();
    }
}
