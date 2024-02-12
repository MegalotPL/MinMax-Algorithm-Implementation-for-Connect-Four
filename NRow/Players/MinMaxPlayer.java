package NRow.Players;

import NRow.Board;
import NRow.Game;
import NRow.Heuristics.Heuristic;
import NRow.Unit;

public class MinMaxPlayer extends PlayerController {
    private final int depth;

    public MinMaxPlayer(int playerId, int gameN, int depth, Heuristic heuristic) {
        super(playerId, gameN, heuristic);
        this.depth = depth;
    }

    /**
     * Make a move
     * @param board the board to make a move on
     * @return the column to make a move in
     */
    @Override
    public int makeMove(Board board) {
        Unit node = new Unit(board);
        return compute(node, depth, true);
    }

    /**
     * Compute the best move
     * @param node the node to compute the best move from
     * @param depth the depth of the tree
     * @param maxPlayer whether the player is the max player or not
     * @return the best move
     */
    public int compute(Unit node, int depth, Boolean maxPlayer) { // compute the best move
        if (depth == 0 || Game.winning(node.getBoard().getBoardState(), gameN) != 0) { // if the depth is 0 or the game is won
            int heuristicValue = this.heuristic.getBestAction(this.playerId, node.getBoard());
            node.setValue(heuristicValue);// set the value of the node to the heuristic value
            return heuristicValue;// return the heuristic value
        }

        int bestValue = maxPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int bestMove = -1; // initialized to an invalid value to detect errors

        for (int i = 0; i < node.getBoard().width; i++) {
            if (!node.getBoard().isValid(i)) continue; // if the move is invalid, skip it

            Board newBoard = node.getBoard().getNewBoard(i, maxPlayer ? playerId : playerId + 1);
            Unit child = new Unit(newBoard); // create a new node with the new board
            child.setCol(i); // set the column of the node to the column of the move

            int childValue = compute(child, depth - 1, !maxPlayer); // compute the value of the child node

            if (maxPlayer) { // if the player is the max player
                if (childValue > bestValue) {
                    bestValue = childValue;
                    bestMove = i;
                }
            } else { // if the player is the min player
                if (childValue < bestValue) {
                    bestValue = childValue;
                    bestMove = i;
                }
            }

            node.setValue(bestValue); // set the value of the node to the best value
            node.getChildren().add(child); // add the child to the node's children
        }

        return bestMove; // return the best move
    }
}
