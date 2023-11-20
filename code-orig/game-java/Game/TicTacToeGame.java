package Game;
import java.util.*;

public class TicTacToeGame extends Board {
  // private Board board;
  private char currentPlayer;

  // protected void initialize() {
  // for (int row = 0; row < 3; row++) {
  // for (int col = 0; col < 3; col++) {
  // board[row][col] = ' ';
  // }
  // }
  // }

  public TicTacToeGame() {
    // board = new Board();
    // initialize();
    currentPlayer = 'X';
  }

  public void printResult(char player) {
    System.out.println("Player " + player + " wins!");
  }

  @Override
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
          printResult(currentPlayer);
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
