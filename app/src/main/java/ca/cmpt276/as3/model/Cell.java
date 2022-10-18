package ca.cmpt276.as3.model;

public class Cell {
    // Variables - TODO: add a good name for mines left in row/column
    private int row;
    private int column;
    private boolean isMine;


    // Constructor
    public Cell() {
        this.row = -1;
        this.column = -1;
        this.isMine = false;
    }

    // Getter methods
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public boolean isMine() {
        return isMine;
    }

    // Setter methods

    public void setRow(int row) {
        this.row = row;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public void setMine(boolean mine) {
        isMine = mine;
    }
}
