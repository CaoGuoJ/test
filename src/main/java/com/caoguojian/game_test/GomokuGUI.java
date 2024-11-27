package com.caoguojian.game_test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GomokuGUI extends JFrame {
    private GomokuBoard board;
    private char currentPlayer = 'X';
    private boolean gameWon = false;

    public GomokuGUI(int size) {
        board = new GomokuBoard(size);

        setTitle("五子棋游戏");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (gameWon) return;

                int row = e.getY() / (getHeight() / size);
                int col = e.getX() / (getWidth() / size);

                if (board.makeMove(row, col, currentPlayer)) {
                    repaint();

                    if (board.checkWin(currentPlayer)) {
                        JOptionPane.showMessageDialog(GomokuGUI.this, "Player " + currentPlayer + " wins!");
                        gameWon = true;
                    } else if (board.isFull()) {
                        JOptionPane.showMessageDialog(GomokuGUI.this, "The game is a draw!");
                        gameWon = true;
                    } else {
                        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    }
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int cellSize = getHeight() / board.getSize();

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);

                char piece = board.getPiece(i, j);
                if (piece != '-') {
                    g.setColor(piece == 'X' ? Color.BLACK : Color.WHITE);
                    g.fillOval(j * cellSize + 10, i * cellSize + 10, cellSize - 20, cellSize - 20);
                }
            }
        }
    }

    public static void main(String[] args) {
        int size = 15; // 棋盘大小
        SwingUtilities.invokeLater(() -> {
            GomokuGUI gui = new GomokuGUI(size);
            gui.setVisible(true);
        });
    }
}