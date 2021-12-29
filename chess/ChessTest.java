package org.cis120.chess;

import org.cis120.Game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.fail;

public class ChessTest {

    @Test
    public void testMain() {
        String error = ("Error: Your submission must include a class called " +
                "\"Game\" in package \"org.cis120\" with a main method:\n" +
                "   public static void main(String[] args)");

        Class<Game> gameClass = Game.class;
        if (gameClass == null) {
            System.out.println(error + "\nBut there was no Game class.");
            fail(error + "\nBut there was no Game class.");
        }

        try {
            Class[] mainArgs = new Class[1];
            mainArgs[0] = (String[].class);

            Method gameMain = gameClass.getMethod("main", mainArgs);

            if (gameMain == null) {
                System.out.println(error + "\nBut no main method was found.");
                fail(error + "\nBut no main method was found.");
            }

            if (!gameMain.getReturnType().toString().equals("void")) {
                System.out.println(
                        error
                                + "\nThe Game class's main method should have return type void."
                );
                fail("The Game class's main method should have return type void.");
            }

            int modifiers = gameMain.getModifiers();

            if (!Modifier.isStatic(modifiers)) {
                System.out.println(error + "The main method should be static.");
                fail("The Game class's main method should be static.");
            }
        } catch (NoSuchMethodException e) {
            System.out.println(error + "\nBut there was no main method or it had the wrong type.");
            fail(error + "\nBut there was no main method or it had the wrong type.");
        } catch (Exception e) {
            System.out.println(
                    "Exception encountered while checking your Game class, please email the TAs."
            );
            e.printStackTrace();
            fail("Exception encountered while checking your Game class, please email the TAs.");
        }

        System.out.println(
                "\n\nYour code compiles and your org.cis120.Game.main method has the right type."
        );
        System.out.println("Next step is the demo with your TA. \n\n");
    }

    @Test
    public void testWhiteWins() {
        Chess c = new Chess();
        c.reset(500);
        Piece[][] board = c.getBoard();
        board[4][0] = null;
        c.setBoard(board);
        assertEquals(1, c.checkWinner(), "White Won");
    }

    @Test
    public void testBlackWins() {
        Chess c = new Chess();
        c.reset(500);
        Piece[][] board = c.getBoard();
        board[4][7] = null;
        c.setBoard(board);
        assertEquals(2, c.checkWinner(), "Black Won");
    }

    @Test
    public void testWhiteRunsOutOfTime() {
        Chess c = new Chess();
        c.reset(500);
        c.setWhiteTimer(0);
        assertEquals(2, c.checkWinner(), "Black Won");
    }

    @Test
    public void testBlackRunsOutOfTime() {
        Chess c = new Chess();
        c.reset(500);
        c.setBlackTimer(0);
        assertEquals(1, c.checkWinner(), "White Won");
    }

    @Test
    public void testWhitePawnBecomesQueen() {
        Chess c = new Chess();
        c.reset(500);
        Piece[][] board = c.getBoard();
        for (int i = 0; i < 8; i++) {
            board[i][0] = new Pawn("", true, i, 0);
        }
        c.setBoard(board);
        c.playTurn(0, 0);
        for (int i = 0; i < 8; i++) {
            assertEquals(Queen.class, c.getBoard()[i][0].getClass(), "Pawn " + i + " is Queen");
        }
    }

    @Test
    public void testBlackPawnBecomesQueen() {
        Chess c = new Chess();
        c.reset(500);
        Piece[][] board = c.getBoard();
        for (int i = 0; i < 8; i++) {
            board[i][7] = new Pawn("", false, i, 7);
        }
        c.setBoard(board);
        c.playTurn(0, 0);
        for (int i = 0; i < 8; i++) {
            assertEquals(Queen.class, c.getBoard()[i][7].getClass(), "Pawn " + i + " is Queen");
        }
    }

    @Test
    public void testAllPawnsCanDoubleJumpAtStart() {
        Chess c = new Chess();
        c.reset(500);
        c.playTurn(0, 0);
        for (int i = 0; i < 8; i++) {
            assertTrue(((Pawn) c.getBoard()[i][1]).getDoubleJumpable(),
                    "Pawn " + i + " can double jump");
            assertTrue(((Pawn) c.getBoard()[i][6]).getDoubleJumpable(),
                    "Pawn " + i + " can double jump");
        }
    }

    @Test
    public void testAllPawnsCantDoubleJump() {
        Chess c = new Chess();
        c.reset(500);
        for (int i = 0; i < 8; i++) {
            c.playTurn(i, 6);
            c.playTurn(i, 4);
            c.playTurn(i, 1);
            c.playTurn(i, 3);
        }
        for (int i = 0; i < 8; i++) {
            assertFalse(((Pawn) c.getBoard()[i][3]).getDoubleJumpable(),
                    "Pawn " + i + " can't double jump");
            assertFalse(((Pawn) c.getBoard()[i][4]).getDoubleJumpable(),
                    "Pawn " + i + " can't double jump");
        }
    }

    @Test
    public void testSelectWhitePiece() {
        Chess c = new Chess();
        c.reset(500);
        c.playTurn(0, 7);
        assertEquals("WR" , c.getSelectedPiece().getName(),
                "Piece selected");
    }

    @Test
    public void testSelectBlackPiece() {
        Chess c = new Chess();
        c.reset(500);
        c.playTurn(0, 0);
        assertNull(c.getSelectedPiece(), "No Piece selected");
        assertThrows(NullPointerException.class, () -> {
            c.getSelectedPiece().getName();
        }, "No piece selected");
    }

    @Test
    public void testSelectNoPiece() {
        Chess c = new Chess();
        c.reset(500);
        c.playTurn(0, 0);
        assertThrows(NullPointerException.class, () -> {
            c.getSelectedPiece().getName();
        }, "No piece selected");
    }

    @Test
    public void testCapturePiece() {
        Chess c = new Chess();
        c.reset(500);

        Piece[][] board = c.getBoard();
        board[1][2] = new Pawn("", true, 1, 2);
        c.setBoard(board);

        c.playTurn(1, 2);
        c.printGameState();

        System.out.print(c.playTurn(1, 1));
        c.printGameState();

    }

}
