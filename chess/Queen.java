package org.cis120.chess;

import java.util.TreeSet;

public class Queen extends Piece {

    public Queen(String imgFile, boolean isWhite, int x, int y) {
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
        finish = false;
        for (int i = getX() + 1; i < 8 && (board[i][getY()] == null || board[i][getY()].getIsWhite()
                != this.getIsWhite()) && !finish; i++) {
            l.add(new MyPoint(i, getY()));
            if (board[i][getY()] != null && board[i][getY()].getIsWhite() != this.getIsWhite()) {
                finish = true;
            }
        }
        finish = false;
        for (int i = getX() - 1; i >= 0 &&
                (board[i][getY()] == null || board[i][getY()].getIsWhite()
                != this.getIsWhite()) && !finish; i--) {
            l.add(new MyPoint(i, getY()));
            if (board[i][getY()] != null && board[i][getY()].getIsWhite() != this.getIsWhite()) {
                finish = true;
            }
        }
        finish = false;
        for (int j = getY() + 1; j < 8 && (board[getX()][j] == null || board[getX()][j].getIsWhite()
                != this.getIsWhite()) && !finish; j++) {
            l.add(new MyPoint(getX(), j));
            if (board[getX()][j] != null && board[getX()][j].getIsWhite() != this.getIsWhite()) {
                finish = true;
            }
        }
        finish = false;
        for (int j = getY() - 1; j >= 0 &&
                (board[getX()][j] == null || board[getX()][j].getIsWhite()
                != this.getIsWhite()) && !finish; j--) {
            l.add(new MyPoint(getX(), j));
            if (board[getX()][j] != null &&
                    board[getX()][j].getIsWhite() != this.getIsWhite()) {
                finish = true;
            }
        }
        return l;
    }

    @Override
    public String getName() {
        if (getIsWhite()) {
            return "WQ";
        } else {
            return "BQ";
        }
    }

}
