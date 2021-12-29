package org.cis120.chess;

/**
 * CIS 120 HW09 - Chess
 * Created by John Colavito in Fall 2021.
 */

/**
 * This class is a model for Chess.
 *
 * Run this file to see the main method play a game of TicTacToe,
 * visualized with Strings printed to the console.
 */
public class Chess {

    private Piece[][] board;
    private int numTurns;
    private boolean playerIsWhite;
    private boolean gameOver;
    private Piece selectedPiece;
    private int whiteTimer, blackTimer;

    /**
     * Constructor sets up game state.
     */
    public Chess() {
        reset(500);
    }

    /**
     * playTurn allows players to play a turn. Returns true if the move is
     * successful and false if a player tries to play in a location that is
     * taken or after the game has ended. If the turn is successful and the game
     * has not ended, the player is changed. If the turn is unsuccessful or the
     * game has ended, the player is not changed.
     *
     * @param x column to play in
     * @param y row to play in
     * @return whether the turn was successful
     */
    public boolean playTurn(int x, int y) {
        for (int i = 0; i < 8; i++) {
            if (board[i][7] != null && !board[i][7].getIsWhite()
                    && board[i][7].getClass().equals(Pawn.class)) {
                board[i][7] = new Queen("files/blackQueen.png",
                        board[i][7].getIsWhite(), i, 7);
            }
        }
        for (int i = 0; i < 8; i++) {
            if (board[i][0] != null && board[i][0].getIsWhite()
                    && board[i][0].getClass().equals(Pawn.class)) {
                board[i][0] = new Queen("files/whiteQueen.png",
                        board[i][0].getIsWhite(), i, 0);
            }
        }
        if (gameOver) {
            return false;
        }
        try {
            if (selectedPiece == null && playerIsWhite == board[x][y].getIsWhite()) {
                selectedPiece = board[x][y];
            } else {
                try {
                    if (board[x][y] != null && playerIsWhite == board[x][y].getIsWhite()) {
                        selectedPiece = board[x][y];
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.print("Error: out of bounds");
                }
                if (selectedPiece.getMoves(board).contains(new MyPoint(x, y))) {
                    numTurns++;
                    board[selectedPiece.getX()][selectedPiece.getY()] = null;
                    board[x][y] = selectedPiece;
                    selectedPiece.move(x, y);
                    if (selectedPiece.getClass().equals(Pawn.class)) {
                        Pawn pawn = (Pawn) selectedPiece;
                        pawn.moved(selectedPiece.getY(), y);
                    }
                    selectedPiece = null;
                    if (checkWinner() == 0) {
                        playerIsWhite = !playerIsWhite;
                    }
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (board[i][j] != null) {
                                if (board[i][j].getClass().equals(King.class)) {
                                    if (((King) board[i][j]).isChecked(board)) {
                                        board[i][j].setChecked(true);
                                    }
                                }
//                                if (board[i][j].getClass().equals(Pawn.class)) {
//                                    Pawn pawn = (Pawn) selectedPiece;
//                                    pawn.setEnpessantable(false);
//                                }
                            }
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Error: no piece there");
        }
        return true;
    }

    /**
     * checkWinner checks whether the game has reached a win condition.
     * checkWinner only looks for horizontal wins.
     *
     * @return 0 if nobody has won yet, 1 if white has won, and 2 if black
     *         has won, 3 if the game hits stalemate
     */
    public int checkWinner() {
        boolean whiteAlive = false;
        boolean blackAlive = false;
        if (whiteTimer <= 0) {
            gameOver = true;
            return 2;
        }
        if (blackTimer <= 0) {
            gameOver = true;
            return 1;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != null && board[i][j].getClass().equals(King.class)) {
                    if (((King) board[i][j]).getIsKing() && board[i][j].getIsWhite()) {
                        whiteAlive = true;
                    } else if (((King) board[i][j]).getIsKing() && !board[i][j].getIsWhite()) {
                        blackAlive = true;
                    }
                }
            }
        }

        if (!blackAlive) {
            gameOver = true;
            return 1;
        } else if (!whiteAlive) {
            gameOver = true;
            return 2;
        } else {
            return 0;
        }
    }

    /**
     * printGameState prints the current game state
     * for debugging.
     */
    public void printGameState() {
        System.out.println("\n\nTurn: " + numTurns + "\n");
//        System.out.println(w + "\n");
        System.out.println("Time elapsed white: " + whiteTimer + ":\n");
        System.out.println("Time elapsed black: " + blackTimer + ":\n");
        if (checkWinner() == 1) {
            System.out.println("Winner: White\n");
        }
        if (checkWinner() == 2) {
            System.out.println("Winner: Black\n");
        }


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[j][i] != null) {
                    System.out.print(board[j][i].getName());
                } else {
                    System.out.print("  ");
                }
                System.out.print(" | ");
            }
            System.out.println("\n---------------------------------------");
        }
    }

    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset(int time) {
        board = new Piece[8][8];
        numTurns = 0;
        playerIsWhite = true;
        gameOver = false;
        whiteTimer = time;
        blackTimer = time;
        board[0][0] = new Rook("files/blackRook.png",false, 0, 0);
        board[1][0] = new Knight("files/blackKnight.png",false, 1, 0);
        board[2][0] = new Bishop("files/blackBishop.png",false, 2, 0);
        board[3][0] = new Queen("files/blackQueen.png",false, 3, 0);
        board[4][0] = new King("files/blackKing.png",false, 4, 0);
        board[5][0] = new Bishop("files/blackBishop.png",false, 5, 0);
        board[6][0] = new Knight("files/blackKnight.png",false, 6, 0);
        board[7][0] = new Rook("files/blackRook.png",false, 7, 0);
        for (int i = 0; i < 8; i++) {
            board[i][1] = new Pawn("files/blackPawn.png",false, i, 1);
        }

        board[0][7] = new Rook("files/whiteRook.png",true, 0, 7);
        board[1][7] = new Knight("files/whiteKnight.png",true, 1, 7);
        board[2][7] = new Bishop("files/whiteBishop.png",true, 2, 7);
        board[3][7] = new Queen("files/whiteQueen.png",true, 3, 7);
        board[4][7] = new King("files/whiteKing.png",true, 4, 7);
        board[5][7] = new Bishop("files/whiteBishop.png",true, 5, 7);
        board[6][7] = new Knight("files/whiteKnight.png",true, 6, 7);
        board[7][7] = new Rook("files/whiteRook.png",true, 7, 7);
        for (int i = 0; i < 8; i++) {
            board[i][6] = new Pawn("files/whitePawn.png",true, i, 6);
        }
    }

    /**
     * getCurrentPlayer is a getter for the player
     * whose turn it is in the game.
     * 
     * @return true if it's Player 1's turn,
     *         false if it's Player 2's turn.
     */
    public boolean getCurrentPlayer() {
        return playerIsWhite;
    }

    /**
     * getCell is a getter for the contents of the cell specified by the method
     * arguments.
     *
     * @param c column to retrieve
     * @param r row to retrieve
     * @return an integer denoting the contents of the corresponding cell on the
     *         game board. 0 = empty, 1 = Player 1, 2 = Player 2
     */
    public Piece getCell(int c, int r) {
        return board[r][c];
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public Piece[][] getBoard() {
        return board;
    }





    /**
     * This main method illustrates how the model is completely independent of
     * the view and controller. We can play the game from start to finish
     * without ever creating a Java Swing object.
     *
     * This is modularity in action, and modularity is the bedrock of the
     * Model-View-Controller design framework.
     *
     * Run this file to see the output of this method in your console.
     */
    public static void main(String[] args) {
        Chess c = new Chess();
        for (MyPoint point : c.board[6][7].getMoves(c.board)) {
            System.out.print(point.getX());
            System.out.println(" " + point.getY());
        }
        System.out.println("bada bada bing");
    }

    public void setTimers(int i) {
        whiteTimer = i;
        blackTimer = i;
    }

    public void whiteTimePass() {
        whiteTimer -= 1;
    }

    public void blackTimePass() {
        blackTimer -= 1;
    }

    public int getWhiteTimer() {
        return whiteTimer;
    }

    public int getBlackTimer() {
        return blackTimer;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public void setBlackTimer(int timer) {
        blackTimer = timer;
    }

    public void setWhiteTimer(int timer) {
        whiteTimer = timer;
    }

    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }
}
