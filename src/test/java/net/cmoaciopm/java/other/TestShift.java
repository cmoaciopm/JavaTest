package net.cmoaciopm.java.other;


import org.junit.Test;

import java.math.BigInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
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

    @Test
    public void test3() {
        assertThat(-4 >> 1, is(-2));
        assertThat(-4 >>> 1, is(not(-2)));
        assertThat((1 + 2147483647), is(-2147483648));
        //            1: 00000000 00000000 00000000 00000001
        //  +2147483647: 01111111 11111111 11111111 11111111
        //  ================================================
        //  -2147483648: 10000000 00000000 00000000 00000000  // Overflow
        //      /2
        //  ================================================
        //  -1073741824: 11000000 00000000 00000000 00000000  // Signed divide, same as >> 1.
        assertThat((1 + 2147483647) >> 1, is(-1073741824));
        //            1: 00000000 00000000 00000000 00000001
        //  +2147483647: 01111111 11111111 11111111 11111111
        //  ================================================
        //  -2147483648: 10000000 00000000 00000000 00000000  // Overflow
        //      >>> 1
        //  ================================================
        //  +1073741824: 01000000 00000000 00000000 00000000  // Unsigned shift right.
        assertThat((1 + 2147483647) >>> 1, is(+1073741824));
    }

    private int pow(int base, int exponent) {
        return BigInteger.valueOf(base).pow(exponent).intValue();
    }
}
