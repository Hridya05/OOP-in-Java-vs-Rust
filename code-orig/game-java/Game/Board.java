package Game;

abstract class Board implements Game {
  protected char[][] board;
  protected int rows;
  protected int cols;

  public Board() {
    rows = 3;
    cols = 3;
    initialize();
  }

  public Board(int r, int c) {
    rows = r;
    cols = c;
    board = new char[rows][cols];
    initialize();
  }

  public void initialize() {
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