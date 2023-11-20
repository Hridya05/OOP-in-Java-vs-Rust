package Game;
interface Game {
  abstract void initialize();
  abstract void printResult(char player);
  abstract boolean hasWon(char player);
  abstract public void play();
}