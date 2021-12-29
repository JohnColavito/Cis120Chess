package org.cis120.chess;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TreeSet;

public abstract class Piece {

    private BufferedImage img;
    private boolean isWhite;
    private int x, y;

    public Piece(String imgFile, boolean isWhite, int x, int y) {
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;

        try {
            if (img == null) {
                img = ImageIO.read(new File(imgFile));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.drawImage(this.img, x * 75 + 7, y * 75 + 5, null);
    }

    public abstract TreeSet<MyPoint> getMoves(Piece[][] board);

    public boolean getIsWhite() {
        return isWhite;
    }

    public Image getImage() {
        return img;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setChecked(boolean b) {
        setChecked(b);
    }

    public abstract String getName();
}
