package com.mycompany.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class UtilityTest {

    private ByteArrayOutputStream buf;

    @Before
    public void setUp() {
        buf = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buf));
    }

    @Test
    public void printCharBoard() {
        char[] b = {'X', 'O', ' '};
        char[] full = new char[9];
        Arrays.fill(full, ' ');
        System.arraycopy(b, 0, full, 0, 3);
        Utility.print(full);
        String s = buf.toString();
        Assert.assertTrue(s.contains("X"));
        Assert.assertTrue(s.contains("O"));
    }

    @Test
    public void printIntBoard() {
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        Utility.print(a);
        Assert.assertTrue(buf.toString().contains("0"));
    }

    @Test
    public void printMoveList() {
        ArrayList<Integer> m = new ArrayList<>();
        m.add(1);
        m.add(3);
        Utility.print(m);
        Assert.assertTrue(buf.toString().contains("1"));
    }
}
