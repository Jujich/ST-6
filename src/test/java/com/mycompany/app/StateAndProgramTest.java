package com.mycompany.app;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

public class StateAndProgramTest {

    @Test
    public void stateValues() {
        State[] v = State.values();
        Assert.assertEquals(4, v.length);
        Assert.assertEquals(State.PLAYING, State.valueOf("PLAYING"));
    }

    @Test
    public void programMainDeclared() throws Exception {
        Method m = Program.class.getMethod("main", String[].class);
        Assert.assertTrue(java.lang.reflect.Modifier.isStatic(m.getModifiers()));
    }
}
