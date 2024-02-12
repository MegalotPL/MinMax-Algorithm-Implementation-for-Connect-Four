package NRow;

import java.util.ArrayList;

/**
 * A unit in the tree
 */
public class Unit {

    private Board board;
    private ArrayList<Unit> children = new ArrayList<Unit>();
    private Integer value;
    private int col;

    public Unit(Board board) {
        this.board = board;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Unit> getChildren() {
        return children;
    }
    public void setCol(int col) {
        this.col = col;
    }
}
