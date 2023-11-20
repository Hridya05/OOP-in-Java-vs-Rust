package Game;
import java.util.*;

public class HighLow extends Cards {
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

  public boolean hasWon(char player) {
    return false;
  }
}