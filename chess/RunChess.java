package org.cis120.chess;

/**
 * CIS 120 HW09 - Chess
 * Created by John Colavito in Fall 2021.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 *
 * In a Model-View-Controller framework, Game initializes the view,
 * implements a bit of controller functionality through the reset
 * button, and then instantiates a GameBoard. The GameBoard will
 * handle the rest of the game's view and controller functionality, and
 * it will instantiate a Chess object to serve as the game's model.
 */
public class RunChess implements Runnable {
    public void run() {
        // NOTE: the 'final' keyword denotes immutability even for local variables.

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("Chess");
        frame.setLocation(300, 50);

        final JFrame instructions = new JFrame("Instructions");
        instructions.setLocation(600, 300);
        final JPanel instructionsTextHolder = new JPanel();
        instructions.add(instructionsTextHolder);
        final JLabel text = new JLabel("<html>The game I implemented is chess.<br/>My version " +
                "of chess is slightly more simplified from actual chess,<br/>but it still has " +
                "pawn promotion, pawn double jumps, check detection, checkmate, etc.<br/>You " +
                "by selecting a piece.<br/>This highlights the piece and any legal moves.<br/>If " +
                "you want a different piece simply click it.<br/>With a piece selected, choose" +
                " the legal move that you would like to make.<br/>Now it is the other players " +
                "turn.<br/>Take turns until checkmate.</html>");
        text.setAlignmentX(Component.LEFT_ALIGNMENT);
        instructionsTextHolder.add(text);


        // Status panel
        final JPanel status_panel = new JPanel();
        status_panel.setLayout(new BoxLayout(status_panel, BoxLayout.Y_AXIS));
        status_panel.add(Box.createHorizontalGlue());
        frame.add(status_panel, BorderLayout.SOUTH);

        final JLabel status = new JLabel("Setting up...");
        status.setAlignmentX(Component.CENTER_ALIGNMENT);
        status_panel.add(status, status_panel);

        final JLabel times = new JLabel("Setting up...");
        times.setAlignmentX(Component.CENTER_ALIGNMENT);
        status_panel.add(times, status_panel);

        // Game board
        final GameBoard board = new GameBoard(status, times);
        frame.add(board, BorderLayout.CENTER);

        // Instructions button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        instructions.pack();
        instructions.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        final JButton instrButton = new JButton("Instructions");
        instrButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                instructions.setVisible(true);
            }
        });
        control_panel.add(instrButton);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start the game
        board.reset();
    }
}