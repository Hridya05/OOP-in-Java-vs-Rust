import java.util.*;
import Game.*;

public class Main {
  public static void main(String[] args) {
    System.out.println("Which game would you like to play?");
    System.out.println("1. Tic Tac Toe");
    System.out.println("2. Connect Four");
    System.out.println("3. High Low");
    Scanner input = new Scanner(System.in);
    int choice = input.nextInt();
    if (choice == 1) {
      TicTacToeGame game = new TicTacToeGame();
      game.play();
    } else if (choice == 2) {
      ConnectFour game2 = new ConnectFour();
      game2.play();
    } else if (choice == 3) {
      HighLow game3 = new HighLow();
      game3.play();
    } else {
      System.out.println("Invalid choice");
    }
    System.out.println("Press any key to exit!");
    Scanner input2 = new Scanner(System.in);
    String exit = input2.nextLine();
    System.exit(0);
    // TicTacToeGame game = new TicTacToeGame();
    // game.play();
    // ConnectFour game2 = new ConnectFour();
    // game2.play();
    // HighLow game3 = new HighLow();
    // game3.play();
    
  }
}
