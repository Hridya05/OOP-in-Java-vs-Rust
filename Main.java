import java.util.Scanner;

abstract class Game {
    abstract void initialize();
    abstract void printResult(char player);
    abstract boolean hasWon(char player);
    abstract boolean isBoardFull();
}
class Board extends game {
    protected char[][] board;
    protected int rows;
    protected int cols;
    public Board() {
        rows = 3;
        cols = 3;
        initializeBoard();
    }
    public Board(int r, int c) {
        rows = r;
        cols = c;
        board = new char[rows][cols];
        initializeBoard();
    }

    protected void initializeBoard() {
        board = new char[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] = ' ';
            }
        }
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int row = 0; row < rows; row++) {
            System.out.print("| ");
            for (int col = 0; col < cols; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public boolean isBoardFull() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isGameOver() {
        return hasWon('X') || hasWon('O') || isBoardFull();
    }

    public boolean hasWon(char player) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // Row win
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // Column win
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // Diagonal win
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true; // Diagonal win
        }
        return false;
    }

    public void makeMove(int row, int col, char player) {
        if (isValidMove(row, col)) {
            board[row][col] = player;
        }
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols && board[row][col] == ' ';
    }
}

class ConnectFourBoard extends Board {
    private int nextRow[];
    char currentPlayer;
    public ConnectFourBoard() {
        super(6, 7);
    }

    @Override
    public void initializeBoard() {
        super.initializeBoard();
        currentPlayer = 'R';
        nextRow = new int[super.cols];
        for (int c = 0; c < cols; c++) {
            nextRow[c] = rows - 1;
        }
        // Additional initialization specific to Connect Four
    }

    @Override
    public boolean hasWon(char player) {
        // Check for horizontal win
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col <= board[row].length - 4; col++) {
                if (board[row][col] == player &&
                    board[row][col + 1] == player &&
                    board[row][col + 2] == player &&
                    board[row][col + 3] == player) {
                    return true;
                }
            }
        }

        // Check for vertical win
        for (int row = 0; row <= board.length - 4; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == player &&
                    board[row + 1][col] == player &&
                    board[row + 2][col] == player &&
                    board[row + 3][col] == player) {
                    return true;
                }
            }
        }

        // Check for diagonal win (from bottom-left to top-right)
        for (int row = 3; row < board.length; row++) {
            for (int col = 0; col <= board[row].length - 4; col++) {
                if (board[row][col] == player &&
                    board[row - 1][col + 1] == player &&
                    board[row - 2][col + 2] == player &&
                    board[row - 3][col + 3] == player) {
                    return true;
                }
            }
        }

        // Check for diagonal win (from top-left to bottom-right)
        for (int row = 0; row <= board.length - 4; row++) {
            for (int col = 0; col <= board[row].length - 4; col++) {
                if (board[row][col] == player &&
                    board[row + 1][col + 1] == player &&
                    board[row + 2][col + 2] == player &&
                    board[row + 3][col + 3] == player) {
                    return true;
                }
            }
        }

        return false;
    }
    @Override
    public boolean isBoardFull() {
        // Check if any column can accept additional moves
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == ' ') {
                return false;
            }
        }
        return true;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Player " + currentPlayer + ", enter column (1-7): ");
            int col = scanner.nextInt() - 1;
            int row = nextRow[col];
            if (isValidMove(row,col)) {
                makeMove(row, col, currentPlayer);
                printBoard();

                if (hasWon(currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                } else if (isBoardFull()) {
                    System.out.println("It's a draw!");
                    break;
                }

                currentPlayer = (currentPlayer == 'R') ? 'B' : 'R';
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }

}

class TicTacToeGame extends Board {
    // private Board board;
    private char currentPlayer;

    protected void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
    }
    
    public TicTacToeGame() {
        // board = new Board();
        // initializeBoard();
        currentPlayer = 'X';
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Player " + currentPlayer + ", enter row and column (e.g., 1 2): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            if (isValidMove(row, col)) {
                makeMove(row, col, currentPlayer);
                printBoard();

                if (hasWon(currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                } else if (isBoardFull()) {
                    System.out.println("It's a draw!");
                    break;
                }

                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        scanner.close();
    }
}

public class Main {
    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        game.play();
    }
}
