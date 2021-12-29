package org.cis120.chess;

import java.util.TreeSet;

public class Pawn extends Piece {

    private boolean doubleJumpable, enpesantable;


    public Pawn(String imgFile, boolean isWhite, int x, int y) {
        super(imgFile, isWhite, x, y);
        doubleJumpable = true;
        enpesantable = false;
    }

    @Override
    public TreeSet<MyPoint> getMoves(Piece[][] board) {
        TreeSet<MyPoint> l = new TreeSet<>();
        if (getIsWhite()) {
            if (doubleJumpable && board[getX()][getY() - 1] == null) {
                if (board[getX()][getY() - 2] == null) {
                    l.add(new MyPoint(getX(), getY() - 2));
                }
            }

            if (getY() - 1 >= 0) {
                if (board[getX()][getY() - 1] == null) {
                    l.add(new MyPoint(getX(), getY() - 1));
                }
            }

            if (getX() - 1 >= 0 && getY() - 1 >= 0) {
                if (board[getX() - 1][getY() - 1] != null) {
                    if (board[getX() - 1][getY() - 1].getIsWhite() != this.getIsWhite()) {
                        l.add(new MyPoint(getX() - 1, getY() - 1));
                    }
                }
                if (board[getX() - 1][getY()] != null && board[getX() - 1][getY()]
                        .getClass().equals(Pawn.class)) {
                    if (((Pawn) board[getX() - 1][getY()]).getEnpessantable()
                            && board[getX() - 1][getY()].getIsWhite() != this.getIsWhite()) {
                        l.add(new MyPoint(getX() - 1, getY() - 1));
                    }
                }
            }

            if (getX() + 1 < 8 && getY() - 1 >= 0) {
                if (board[getX() + 1][getY() - 1] != null) {
                    if (board[getX() + 1][getY() - 1].getIsWhite() != this.getIsWhite()) {
                        l.add(new MyPoint(getX() + 1, getY() - 1));
                    }
                }
                if (board[getX() + 1][getY()] != null && board[getX() + 1][getY()]
                        .getClass().equals(Pawn.class)) {
                    if (((Pawn) board[getX() + 1][getY()]).getEnpessantable()
                            && board[getX() + 1][getY()].getIsWhite() != this.getIsWhite()) {
                        l.add(new MyPoint(getX() + 1, getY() - 1));
                    }
                }
            }
        } else {
            if (doubleJumpable && board[getX()][getY() + 1] == null) {
                if (board[getX()][getY() + 2] == null) {
                    l.add(new MyPoint(getX(), getY() + 2));
                }
            }

            if (getY() + 1 < 8) {
                if (board[getX()][getY() + 1] == null) {
                    l.add(new MyPoint(getX(), getY() + 1));
                }
            }

            if (getX() - 1 >= 0 && getY() + 1 < 8) {
                if (board[getX() - 1][getY() + 1] != null) {
                    if (board[getX() - 1][getY() + 1].getIsWhite() != this.getIsWhite()) {
                        l.add(new MyPoint(getX() - 1, getY() + 1));
                    }
                }
                if (board[getX() - 1][getY()] != null && board[getX() - 1][getY()]
                        .getClass().equals(Pawn.class)) {
                    if (((Pawn) board[getX() - 1][getY()]).getEnpessantable()
                            && board[getX() - 1][getY()].getIsWhite()
                            != this.getIsWhite()) {
                        l.add(new MyPoint(getX() - 1, getY() + 1));
                    }
                }
            }

            if (getX() + 1 < 8 && getY() + 1 < 8) {
                if (board[getX() + 1][getY() + 1] != null) {
                    if (board[getX() + 1][getY() + 1].getIsWhite() != this.getIsWhite()) {
                        l.add(new MyPoint(getX() + 1, getY() + 1));
                    }
                }
                if (board[getX() + 1][getY()] != null
                        && board[getX() + 1][getY()].getClass().equals(Pawn.class)) {
                    if (((Pawn) board[getX() + 1][getY()]).getEnpessantable()
                            && board[getX() + 1][getY()].getIsWhite() != this.getIsWhite()) {
                        l.add(new MyPoint(getX() + 1, getY() + 1));
                    }
                }
            }
        }
        return l;
    }

    public void moved(int oldY, int newY) {
        if (Math.abs(oldY - newY) == 2) {
            enpesantable = true;
        }
        doubleJumpable = false;
    }

    public void setEnpessantable(boolean b) {
        enpesantable = false;
    }

    public boolean getEnpessantable() {
        return enpesantable;
    }
    public boolean getDoubleJumpable() {
        return doubleJumpable;
    }

    @Override
    public String getName() {
        if (getIsWhite()) {
            return "WP";
        } else {
            return "BP";
        }
    }
}
