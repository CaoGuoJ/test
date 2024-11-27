package com.caoguojian.game_test;

public class GomokuBoard {
    private char[][] board;
    private int size;

    public GomokuBoard(int size) {
        this.size = size;
        this.board = new char[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean makeMove(int row, int col, char player) {
        if (board[row][col] == '-') {
            board[row][col] = player;
            return true;
        }
        return false;
    }

    public boolean checkWin(char player) {
        return checkHorizontal(player) || checkVertical(player) || checkDiagonal(player);
    }

    private boolean checkHorizontal(char player) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= size - 5; j++) {
                if (board[i][j] == player && board[i][j + 1] == player && board[i][j + 2] == player &&
                        board[i][j + 3] == player && board[i][j + 4] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertical(char player) {
        for (int i = 0; i <= size - 5; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == player && board[i + 1][j] == player && board[i + 2][j] == player &&
                        board[i + 3][j] == player && board[i + 4][j] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal(char player) {
        for (int i = 0; i <= size - 5; i++) {
            for (int j = 0; j <= size - 5; j++) {
                if (board[i][j] == player && board[i + 1][j + 1] == player && board[i + 2][j + 2] == player &&
                        board[i + 3][j + 3] == player && board[i + 4][j + 4] == player) {
                    return true;
                }
                if (board[i][j + 4] == player && board[i + 1][j + 3] == player && board[i + 2][j + 2] == player &&
                        board[i + 3][j + 1] == player && board[i + 4][j] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public char getPiece(int row, int col) {
        return board[row][col];
    }

    public int getSize() {
        return size;
    }
}