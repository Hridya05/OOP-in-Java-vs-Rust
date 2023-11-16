import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.util.Pair;

interface Game {
  abstract void initialize();
  abstract void printResult(char player);
  abstract boolean hasWon(char player);
  abstract public void play();
}

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

abstract class Cards implements Game {
  protected List<Pair<Integer, Integer>> deck;

  public Cards() {
    deck = new ArrayList<>();
    initialize();
  }

  public void initialize() {
    // deck = new ArrayList<>();
    for (int i = 1; i <= 13; i++) {
      for (int j = 1; j <= 4; j++) {
        Pair<Integer, Integer> p = new Pair<Integer, Integer>(j, i);
        deck.add(p);
      }
    }
  }

  public void shuffleDeck() {
    Random rand = new Random();
    for (int i = deck.size() - 1; i > 0; i--) {
      int j = rand.nextInt(i + 1);
      Pair<Integer, Integer> temp = deck.get(i);
      deck.set(i, deck.get(j));
      deck.set(j, temp);
    }
  }

  public void play() {

  }
}

class ConnectFour extends Board {
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

class TicTacToeGame extends Board {
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

class HighLow extends Cards {
  private int currentCard;
  private int score;

  public HighLow() {
    super();
    score = 0;
    // currentCard = 0;
  }

  @Override
  public void initialize() {
    // deck = new ArrayList<>();
    for (int i = 1; i <= 13; i++) {
      for (int j = 1; j <= 1; j++) {
        Pair<Integer, Integer> p = new Pair<>(j, i);
        deck.add(p);
      }
    }
  }

  @Override
  public void play() {
    shuffleDeck();
    currentCard = (deck.remove(deck.size() - 1)).getValue();

    Scanner scanner = new Scanner(System.in);

    while (!deck.isEmpty()) {
      System.out.println("Current card is " + currentCard + ". Will the next card be higher (h) or lower (l)?");
      String guess = scanner.nextLine().trim();

      if (!guess.equals("h") && !guess.equals("l")) {
        System.out.println("Invalid input. Please enter 'h' or 'l'.");
        continue;
      }

      int nextCard = (deck.remove(deck.size() - 1)).getValue();
      System.out.println("Next card is " + nextCard);

      if ((guess.equals("h") && nextCard > currentCard) || (guess.equals("l") && nextCard < currentCard)) {
        System.out.println("Correct guess!");
        score++;
      } else {
        System.out.println("Wrong guess. Game over.");
        printResult('-');
        break;
      }

      currentCard = nextCard;
    }

    if (deck.isEmpty()) {
      System.out.println("No more cards left. Game over!");
      printResult('-');
    }
  }

  @Override
  public void printResult(char player) {
    System.out.println("Congratulations! You scored: " + score + " points.");
  }
}

public class Main {
  public static void main(String[] args) {
    // TicTacToeGame game = new TicTacToeGame();
    // game.play();
    // ConnectFour game2 = new ConnectFour();
    // game2.play();
    HighLow game3 = new HighLow();
    game3.play();
  }
}
