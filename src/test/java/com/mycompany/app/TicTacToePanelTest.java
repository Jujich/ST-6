package com.mycompany.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class TicTacToePanelTest {

    static final class CapturingPanel extends TicTacToePanel {
        State lastFinish;

        CapturingPanel() {
            super(new GridLayout(3, 3));
        }

        @Override
        protected void onGameOver(State gameState) {
            lastFinish = gameState;
        }
    }

    @Before
    public void setUp() {
        System.setProperty("java.awt.headless", "true");
    }

    @Test
    public void panelConstructs() {
        TicTacToePanel p = new TicTacToePanel(new GridLayout(3, 3));
        Assert.assertNotNull(p);
    }

    @Test
    public void firstHumanMoveDoesNotFinish() throws Exception {
        CapturingPanel panel = new CapturingPanel();
        java.lang.reflect.Field f = TicTacToePanel.class.getDeclaredField("cells");
        f.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) f.get(panel);
        panel.actionPerformed(new ActionEvent(cells[0], ActionEvent.ACTION_PERFORMED, ""));
        Assert.assertNull(panel.lastFinish);
    }

    @Test
    public void playUntilTerminalInvokesHook() throws Exception {
        CapturingPanel panel = new CapturingPanel();
        java.lang.reflect.Field f = TicTacToePanel.class.getDeclaredField("cells");
        f.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) f.get(panel);
        int guard = 0;
        while (panel.lastFinish == null && guard++ < 30) {
            boolean moved = false;
            for (TicTacToeCell c : cells) {
                if (c.isEnabled()) {
                    panel.actionPerformed(new ActionEvent(c, ActionEvent.ACTION_PERFORMED, ""));
                    moved = true;
                    break;
                }
            }
            if (!moved) {
                break;
            }
        }
        Assert.assertNotNull(panel.lastFinish);
        Assert.assertTrue(
                panel.lastFinish == State.XWIN
                        || panel.lastFinish == State.OWIN
                        || panel.lastFinish == State.DRAW);
    }
}
