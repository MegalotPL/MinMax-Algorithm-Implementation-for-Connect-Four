package NRow.Players;

import NRow.Board;
import NRow.Heuristics.Heuristic;

public abstract class PlayerController {
  public final int playerId;
  protected int gameN;
  protected Heuristic heuristic;


  public PlayerController(int playerId, int gameN, Heuristic heuristic) {
    this.playerId = playerId;
    this.gameN = gameN;
    this.heuristic = heuristic;
  }

  public int getEvalCount() {
    return heuristic.getEvalCount();
  }

  @Override
  public String toString() {
    return (playerId == 2) ? "O" : "X"; 
  }

  public abstract int makeMove(Board board);
}
