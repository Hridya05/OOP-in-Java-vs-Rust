package Game;
import java.util.*;

public class ConnectFour extends Board {
  private int nextRow[];
  char currentPlayer;

  public ConnectFour() {
    super(6, 7);
    nextRow = new int[super.cols];
    currentPlayer = 'R';
    for (int c = 0; c < cols; c++) {
      nextRow[c] = rows - 1;
    }
    // initialize();
  }

  // @Override
  // public void initialize() {
  // // super.initialize();
  // // Additional initialization specific to Connect Four
  // currentPlayer = 'R';
  // for (int c = 0; c < cols; c++) {
  // nextRow[c] = rows - 1;
  // }
  // }

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
    for (int col = 0; col < cols; col++) {
      if (nextRow[col] >= 0) {
        return false;
      }
    }
    return true;
  }

  protected void nextPlayer() {
    currentPlayer = (currentPlayer == 'R') ? 'B' : 'R';
  }

  @Override
  public void printResult(char Player) {
    System.out.println("Player " + currentPlayer + " wins!");
  }
  
  @Override
  public void play() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("Player " + currentPlayer + ", enter column (1-7): ");
      int col = scanner.nextInt() - 1;
      int row = nextRow[col];
      if (isValidMove(row, col)) {
        makeMove(row, col, currentPlayer);
        printBoard();
        nextRow[col]--;
        if (hasWon(currentPlayer)) {
          printResult(currentPlayer);
          break;
        } else if (isBoardFull()) {
          System.out.println("It's a draw!");
          break;
        }

        nextPlayer();
      } else {
        System.out.println("Invalid move. Try again.");
      }
    }

    scanner.close();
  }

}
