package org.cis120.chess;

/**
 * CIS 120 HW09 - Chess
 * Created by John Colavito in Fall 2021.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class instantiates a Chess object, which is the model for the game.
 * As the user clicks the game board, the model is updated. Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.
 *
 * In a Model-View-Controller framework, GameBoard stores the model as a field
 * and acts as both the controller (with a MouseListener) and the view (with
 * its paintComponent method and the status JLabel).
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private Chess c; // model for the game
    private JLabel status; // current status text
    private JLabel times;

    // Game constants
    public static final int BOARD_WIDTH = 600;
    public static final int BOARD_HEIGHT = 600;
    public static final int INTERVAL = 1000;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit, JLabel timesInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // The timer is an object which triggers an action periodically with the
        // given INTERVAL. We register an ActionListener with this timer, whose
        // actionPerformed() method is called each time the timer triggers. We
        // define a helper method called tick() that actually does everything
        // that should be done in a single timestep.
        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start(); // MAKE SURE TO START THE TIMER!

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        c = new Chess(); // initializes model for the game
        status = statusInit; // initializes the status JLabel
        times = timesInit;

        /*
         * Listens for mouseclicks. Updates the model, then updates the game
         * board based off of the updated model.
         */
        if (!c.getGameOver()) {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    Point p = e.getPoint();

                    // updates the model given the coordinates of the mouseclick
                    c.playTurn(p.x / 75, p.y / 75);

                    updateStatus(); // updates the status JLabel
                    repaint(); // repaints the game board
                }
            });
        }
    }

    void tick() {
        if (!c.getGameOver()) {
            if (c.getCurrentPlayer()) {
                c.whiteTimePass();
            } else if (!c.getCurrentPlayer()) {
                c.blackTimePass();
            }
            updateTimes();
            updateStatus();
        }
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        c.reset(500);
        try {
            status.setText("White's Turn");
        } catch (NullPointerException e) {
            System.out.println("Error: No Status");
        }
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        if (c.getCurrentPlayer()) {
            status.setText("White's Turn");
        } else {
            status.setText("Black's Turn");
        }

        int winner = c.checkWinner();
        if (winner == 1) {
            status.setText("White wins!!!");
        } else if (winner == 2) {
            status.setText("Black wins!!!");
        } else if (winner == 3) {
            status.setText("Stalemate.");
        }
    }
    private void updateTimes() {
        times.setText("White's Time: " + c.getWhiteTimer() +
                "       Black's Time: " + c.getBlackTimer());
    }


    /**
     * Draws the game board.
     * 
     * There are many ways to draw a game board. This approach
     * will not be sufficient for most games, because it is not
     * modular. All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper
     * methods. Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draws board grid
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    g.setColor(new Color(235, 204, 160));
                } else {
                    g.setColor(new Color(152, 116, 78));
                }
                g.fillRect(75 * i, 75 * j, 75, 75);
            }
        }
        if (c.getSelectedPiece() != null) {
            g.setColor(Color.gray);
            g.fillOval(c.getSelectedPiece().getX() * 75, c.getSelectedPiece().getY() * 75, 75, 75);
            for (MyPoint points : c.getSelectedPiece().getMoves(c.getBoard())) {
                g.fillOval(points.getX() * 75, points.getY() * 75, 75, 75);

            }
        }

            // Draws pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece state = c.getCell(j, i);
                if (state != null) {
                    state.draw(g);
                }
            }
        }
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }

    public Piece[][] getBoard() {
        return c.getBoard();
    }
    public void setBoard(Piece[][] board) {
        c.setBoard(board);
    }
    public Chess getC() {
        return c;
    }

    public int getWhiteTimer() {
        return c.getWhiteTimer();
    }
}
