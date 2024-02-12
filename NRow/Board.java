package NRow;

import java.util.Arrays;

public class Board {
  public final int width;
  public final int height;
  private int[][] boardState;

  Board(int width, int height) {
    this.width = width;
    this.height = height;
    this.boardState = new int[width][height];
  }

  public Board(Board other) {
    this.width = other.width;
    this.height = other.height;
    this.boardState = other.getBoardState();
  }

  public Board(int[][] state) {
    this.width = state.length;
    this.height = state[0].length;
    this.boardState = state;
  }

  public int getValue(int x, int y) {
    return boardState[x][y];
  }

  public int[][] getBoardState() {
    return Arrays.stream(boardState).map(int[]::clone).toArray(int[][]::new);
  }

  public boolean play(int x, int playerId) {
    for (int i = this.boardState[0].length - 1; i >= 0; i--) {
      if (this.boardState[x][i] == 0) {
        this.boardState[x][i] = playerId;
        return true;
      }
    }
    return false;
  }

  public boolean isValid(int x) {
    return getBoardState()[x][0] == 0;
  }

  public Board getNewBoard(int x, int playerId) {
    int[][] newBoardState = getBoardState();
    for(int i = newBoardState[0].length-1; i >= 0 ; i--) {
      if(newBoardState[x][i] == 0) {
        newBoardState[x][i] = playerId;
        return new Board(newBoardState);
      }
    }
    return new Board(newBoardState);
  }

  @Override
  public String toString() {
    String divider = " ";
    String divider2 = " ";
    String numberRow = "|";
    
    for (int i = 0; i < boardState.length; i++) {
      divider += "--- ";
      divider2 += "=== ";
      numberRow += " " + (i + 1) + " |";
    }

    String output = "";
    
    for (int i = 0; i < boardState[0].length; i++) {
      output += "\n" + divider + "\n";
      for (int j = 0; j < boardState.length; j++) {
        String node = " ";
        if (boardState[j][i] == 1) {
          node = "X";
        } else if (boardState[j][i] == 2) {
          node = "O";
        }
        output += "| " + node + " ";
      }
      output += "|";
    }
    output += "\n" + divider2 + "\n" + numberRow + "\n";
    
    return output;
  }
}
