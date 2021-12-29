package org.cis120.chess;

import java.util.TreeSet;

public class Bishop extends Piece {

    public Bishop(String imgFile, boolean isWhite, int x, int y) {
        super(imgFile, isWhite, x, y);
    }

    @Override
    public TreeSet<MyPoint> getMoves(Piece[][] board) {
        boolean finish = false;
        TreeSet<MyPoint> l = new TreeSet<>();
        for (int i = getX() + 1, j = getY() + 1; i < 8 && i >= 0 &&
                j < 8 && j >= 0 && (board[i][j] == null || board[i][j].getIsWhite()
                != this.getIsWhite()) && !finish; i++, j++) {
            l.add(new MyPoint(i, j));
            if (board[i][j] != null && board[i][j].getIsWhite() != this.getIsWhite()) {
                finish = true;
            }
        }
        finish = false;
        for (int i = getX() - 1, j = getY() + 1; i < 8 && i >= 0 &&
                j < 8 && j >= 0 && (board[i][j] == null || board[i][j].getIsWhite()
                != this.getIsWhite()) && !finish; i--, j++) {
            l.add(new MyPoint(i, j));
            if (board[i][j] != null && board[i][j].getIsWhite() != this.getIsWhite()) {
                finish = true;
            }
        }
        finish = false;
        for (int i = getX() - 1, j = getY() - 1; i < 8 && i >= 0 &&
                j < 8 && j >= 0 && (board[i][j] == null || board[i][j].getIsWhite()
                != this.getIsWhite()) && !finish; i--, j--) {
            l.add(new MyPoint(i, j));
            if (board[i][j] != null && board[i][j].getIsWhite() != this.getIsWhite()) {
                finish = true;
            }
        }
        finish = false;
        for (int i = getX() + 1, j = getY() - 1; i < 8 && i >= 0 &&
                j < 8 && j >= 0 && (board[i][j] == null || board[i][j].getIsWhite()
                != this.getIsWhite()) && !finish; i++, j--) {
            l.add(new MyPoint(i, j));
            if (board[i][j] != null && board[i][j].getIsWhite() != this.getIsWhite()) {
                finish = true;
            }
        }
        return l;
    }

    @Override
    public String getName() {
        if (getIsWhite()) {
            return "WB";
        } else {
            return "BB";
        }
    }

}
