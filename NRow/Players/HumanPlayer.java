package NRow.Players;

import java.util.Scanner;

import NRow.Board;
import NRow.Heuristics.Heuristic;

public class HumanPlayer extends PlayerController {
  Scanner scanner = new Scanner(System.in);

  public HumanPlayer(int playerId, int gameN, Heuristic heuristic) {
    super(playerId, gameN, heuristic);
  }

  @Override
  public int makeMove(Board board) {
    System.out.println(board);
    
    if (heuristic != null)
      System.out.println("Heuristic: " + heuristic + " calculated the best move is: "
          + (heuristic.getBestAction(playerId, board) + 1));
    
    System.out.println("Player " + this + "\nWhich column would you like to play in?");
    
    int column = scanner.nextInt();
    
    System.out.println("Selected Column: " + column);
    return column - 1;
  }

}
