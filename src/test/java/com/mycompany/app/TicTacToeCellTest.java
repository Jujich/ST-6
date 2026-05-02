package com.mycompany.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TicTacToeCellTest {

    @Before
    public void setUp() {
        System.setProperty("java.awt.headless", "true");
    }

    @Test
    public void cellGeometryAndMarker() {
        TicTacToeCell c = new TicTacToeCell(4, 1, 2);
        Assert.assertEquals(4, c.getNum());
        Assert.assertEquals(1, c.getCol());
        Assert.assertEquals(2, c.getRow());
        Assert.assertEquals(' ', c.getMarker());
        c.setMarker("X");
        Assert.assertEquals('X', c.getMarker());
    }
}
