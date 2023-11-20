package Game;
import java.util.*;

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
      int j = rand.nextInt(i);
      Pair<Integer, Integer> temp = deck.get(i);
      deck.set(i, deck.get(j));
      deck.set(j, temp);
    }
    
  }

  public void play() {

  }
}