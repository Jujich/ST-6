package com.mycompany.app;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class GameTest {

    private Game game;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setProperty("java.awt.headless", "true");
        game = new Game();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void checkStatePlayingEmpty() {
        game.symbol = 'X';
        char[] b = new char[9];
        Arrays.fill(b, ' ');
        Assert.assertEquals(State.PLAYING, game.checkState(b));
    }

    @Test
    public void checkStateXWinTopRow() {
        game.symbol = 'X';
        char[] b = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        Assert.assertEquals(State.XWIN, game.checkState(b));
    }

    @Test
    public void checkStateOWinColumn() {
        game.symbol = 'O';
        char[] b = {'O', ' ', ' ', 'O', ' ', ' ', 'O', ' ', ' '};
        Assert.assertEquals(State.OWIN, game.checkState(b));
    }

    @Test
    public void checkStateDrawFull() {
        game.symbol = 'X';
        char[] b = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X'};
        Assert.assertEquals(State.DRAW, game.checkState(b));
    }

    @Test
    public void checkStateDiagWin() {
        game.symbol = 'X';
        char[] b = {'X', ' ', ' ', ' ', 'X', ' ', ' ', ' ', 'X'};
        Assert.assertEquals(State.XWIN, game.checkState(b));
    }

    @Test
    public void checkStateAntiDiagWin() {
        game.symbol = 'O';
        char[] b = {' ', ' ', 'O', ' ', 'O', ' ', 'O', ' ', ' '};
        Assert.assertEquals(State.OWIN, game.checkState(b));
    }

    @Test
    public void evaluateXWinForX() {
        game.symbol = 'X';
        char[] b = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        Player p = new Player();
        p.symbol = 'X';
        Assert.assertEquals(Game.INF, game.evaluatePosition(b, p));
    }

    @Test
    public void evaluateXWinForO() {
        game.symbol = 'X';
        char[] b = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        Player p = new Player();
        p.symbol = 'O';
        Assert.assertEquals(-Game.INF, game.evaluatePosition(b, p));
    }

    @Test
    public void evaluateDraw() {
        game.symbol = 'X';
        char[] b = {'X', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'X'};
        Player p = new Player();
        p.symbol = 'X';
        Assert.assertEquals(0, game.evaluatePosition(b, p));
    }

    @Test
    public void evaluateNonTerminal() {
        game.symbol = 'X';
        char[] b = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        Player p = new Player();
        p.symbol = 'X';
        Assert.assertEquals(-1, game.evaluatePosition(b, p));
    }

    @Test
    public void generateMovesListsEmptyCells() {
        char[] b = {'X', ' ', ' ', ' ', 'O', ' ', ' ', ' ', ' '};
        ArrayList<Integer> m = new ArrayList<>();
        game.generateMoves(b, m);
        Assert.assertEquals(7, m.size());
        Assert.assertTrue(m.contains(1));
        Assert.assertFalse(m.contains(0));
    }

    @Test
    public void minimaxChoosesMoveFromEmpty() {
        game.symbol = ' ';
        Arrays.fill(game.board, ' ');
        game.player1.symbol = 'X';
        game.player2.symbol = 'O';
        Player ai = game.player2;
        int move = game.MiniMax(game.board, ai);
        Assert.assertTrue(move >= 1 && move <= 9);
    }

    @Test
    public void minimaxBlocksImmediateWin() {
        game.symbol = 'X';
        char[] b = {'X', 'X', ' ', ' ', 'O', ' ', ' ', ' ', ' '};
        System.arraycopy(b, 0, game.board, 0, 9);
        game.player2.symbol = 'O';
        int move = game.MiniMax(game.board, game.player2);
        Assert.assertEquals(3, move);
    }

    @Test
    public void minimaxFromNearEnd() {
        game.symbol = 'X';
        char[] b = {'X', 'O', 'X', 'O', 'X', ' ', 'O', 'X', 'O'};
        System.arraycopy(b, 0, game.board, 0, 9);
        game.player2.symbol = 'O';
        int move = game.MiniMax(game.board, game.player2);
        Assert.assertEquals(6, move);
    }

    @Test
    public void playerDefaults() {
        Player p = new Player();
        Assert.assertFalse(p.selected);
        Assert.assertFalse(p.win);
    }
}
