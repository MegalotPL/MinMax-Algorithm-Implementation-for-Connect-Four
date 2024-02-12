package NRow;

import NRow.Heuristics.SimpleHeuristic;
import NRow.Players.AlphaBetaPruningPlayer;
import NRow.Players.HumanPlayer;
import NRow.Players.MinMaxPlayer;
import NRow.Players.PlayerController;

public class App {
    public static void main(String[] args) throws Exception {
        int gameN = 4;
        int boardWidth = 8;
        int boardHeight = 8;

        PlayerController[] players = getPlayers(gameN);

        Game game = new Game(gameN, boardWidth, boardHeight, players);
        game.startGame();
    }

    /**
     * Get the players
     * @param n the number of pieces in a row to win
     * @return the players
     */
    private static PlayerController[] getPlayers(int n) {

        PlayerController human = new HumanPlayer(1, n, new SimpleHeuristic(n));

        MinMaxPlayer AIMinMaxGamer = new MinMaxPlayer(1, n, 4, new SimpleHeuristic(n));

        AlphaBetaPruningPlayer AIAlphaBetaGamer = new AlphaBetaPruningPlayer(2,n,4,new SimpleHeuristic(n));

        return new PlayerController[]{ AIMinMaxGamer, AIAlphaBetaGamer};
    }
}
