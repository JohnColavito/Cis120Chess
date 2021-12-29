package org.cis120.chess;

import java.util.TreeSet;

public class Knight extends Piece {

    public Knight(String imgFile, boolean isWhite, int x, int y) {
        super(imgFile, isWhite, x, y);
    }

    @Override
    public TreeSet<MyPoint> getMoves(Piece[][] board) {
        TreeSet<MyPoint> l = new TreeSet<>();
        for (int i = 2; i > -3; i--) {
            for (int j = 2; j > -3; j--) {
                if (Math.abs(i) == 2 ^ Math.abs(j) == 2) {
                    if (j != 0 && i != 0) {
                        try {
                            if (getX() + i < 8 && getX() + i >= 0 &&
                                    getY() + j < 8 && getY() + j >= 0) {
                                if (board[getX() + i][getY() + j] == null) {
                                    l.add(new MyPoint(getX() + i, getY() + j));
                                } else {
                                    if (board[getX() + i][getY() + j].
                                            getIsWhite() != this.getIsWhite()) {
                                        l.add(new MyPoint(getX() + i, getY() + j));
                                    }
                                }
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            continue;
                        }
                    }
                }
            }
        }
        return l;
    }

    @Override
    public String getName() {
        if (getIsWhite()) {
            return "WN";
        } else {
            return "BN";
        }
    }

}
