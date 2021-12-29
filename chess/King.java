package org.cis120.chess;

import java.awt.*;
import java.util.TreeSet;

public class King extends Piece {

    private boolean isKing;
    private boolean checked;

    public King(String imgFile, boolean isWhite, int x, int y) {
        super(imgFile, isWhite, x, y);
        isKing = true;
        checked = false;
    }

    @Override
    public void draw(Graphics g) {
        if (checked) {
            g.setColor(new Color(153, 23, 17));
            g.fillOval(getX() * 75, getY() * 75, 75, 75);
        }
        g.drawImage(getImage(), getX() * 75 + 7, getY() * 75 + 5, null);
    }


    @Override
    public TreeSet<MyPoint> getMoves(Piece[][] board) {
        TreeSet<MyPoint> l = new TreeSet<>();
        for (int i = 1; i >= -1; i--) {
            for (int j = 1; j >= -1; j--) {
                if (!(i == 0 && j == 0)) {
                    try {
                        if (board[getX() + i][getY() + j] == null) {
                            l.add(new MyPoint(getX() + i, getY() + j));
                        } else {
                            if (board[getX() + i][getY() + j].getIsWhite() != this.getIsWhite()) {
                                l.add(new MyPoint(getX() + i, getY() + j));
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        continue;
                    }
                }
            }
        }
        return l;
    }

    public boolean isChecked(Piece[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].getMoves(board)
                        .contains(new MyPoint(getX(), getY()))) {
                    checked = true;
                    return true;
                }
            }
        }
        checked = false;
        return false;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean getIsKing() {
        return isKing;
    }

    @Override
    public String getName() {
        if (getIsWhite()) {
            return "WK";
        } else {
            return "BK";
        }
    }

}
