package NRow.Heuristics;

import NRow.Board;

public abstract class Heuristic {
    protected int gameN;
    protected int evalCount = 0;

    public Heuristic(int gameN) {
        this.gameN = gameN;
    }

    public int getEvalCount() {
        return evalCount;
    }

    public int getBestAction(int player, Board board) {
        int[] utilities = evalActions(player, board);
        int bestAction = 0;
        for (int i = 0; i < utilities.length; i++) {
            bestAction = utilities[i] > utilities[bestAction] ? i : bestAction;
        }
        return bestAction;
    }

    public int[] evalActions(int player, Board board) {
        int[] utilities = new int[board.width];
        for (int i = 0; i < board.width; i++) {
            utilities[i] = evaluateAction(player, i, board);
        }
        return utilities;
    }


    protected int evaluateAction(int player, int action, Board board) {
        if (board.isValid(action)) {
            evalCount++;
            int value = evaluateBoard(player, board.getNewBoard(action, player));
            return value;
        } else return Integer.MIN_VALUE;
    }

    public int evaluateBoard(int player, Board board) {
        evalCount++;
        return evaluate(player, board);
    }

    public String toString() {
        return this.name();
    }

    protected abstract String name();

    protected abstract int evaluate(int player, Board board);
}
