package NRow.Players;

import NRow.Board;
import NRow.Game;
import NRow.Heuristics.Heuristic;
import NRow.Unit;

public class AlphaBetaPruningPlayer extends PlayerController{
    private final int depth;

    public AlphaBetaPruningPlayer(int playerId, int gameN, int depth, Heuristic heuristic) {
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
        return computeWithAlphaBeta(node, depth, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    /**
     * Compute the best move using alpha-beta pruning
     * @param node the node to compute the best move from
     * @param depth the depth of the tree
     * @param maxPlayer whether the player is the max player or not
     * @param alpha the alpha value
     * @param beta the beta value
     * @return the best move
     */
    public int computeWithAlphaBeta(Unit node, int depth, Boolean maxPlayer, int alpha, int beta) {
        if (depth == 0 || Game.winning(node.getBoard().getBoardState(), gameN) != 0) {// if the depth is 0 or the game is won
            int heuristicValue = this.heuristic.getBestAction(this.playerId, node.getBoard());// get the heuristic value
            node.setValue(heuristicValue);// set the value of the node to the heuristic value
            return heuristicValue;
        }

        int bestMove = -1; // initialized to an invalid value to detect errors
        int bestValue;

        if (maxPlayer) {// if the player is the max player
            bestValue = Integer.MIN_VALUE;// -infinity

            for (int i = 0; i < node.getBoard().width; i++) {// for each possible move
                if (!node.getBoard().isValid(i)) continue;// if the move is invalid, skip it

                Board newBoard = node.getBoard().getNewBoard(i, playerId);// get the new board
                Unit child = new Unit(newBoard);// create a new node with the new board
                child.setCol(i);// set the column of the node to the column of the move

                int childValue = computeWithAlphaBeta(child, depth - 1, false, alpha, beta); // compute the value of the child node

                if (childValue > bestValue) {// if the child value is greater than the best value
                    bestValue = childValue;
                    bestMove = i;
                }

                alpha = Math.max(alpha, bestValue); // update alpha

                if (alpha >= beta) break;  // Alpha-beta pruning
            }

        } else {// if the player is the min player
            bestValue = Integer.MAX_VALUE;

            for (int i = 0; i < node.getBoard().width; i++) {// for each possible move
                if (!node.getBoard().isValid(i)) continue;// if the move is invalid, skip it

                Board newBoard = node.getBoard().getNewBoard(i, playerId + 1);// get the new board
                Unit child = new Unit(newBoard);// create a new node with the new board
                child.setCol(i);// set the column of the node to the column of the move

                int childValue = computeWithAlphaBeta(child, depth - 1, true, alpha, beta); // compute the value of the child node

                if (childValue < bestValue) {// if the child value is less than the best value
                    bestValue = childValue;
                    bestMove = i;
                }

                beta = Math.min(beta, bestValue); // update beta

                if (alpha >= beta) break;  // Alpha-beta pruning
            }

        }
        node.setValue(bestValue);// set the value of the node to the best value

        return bestMove;
    }
}
