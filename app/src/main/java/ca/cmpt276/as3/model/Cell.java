package ca.cmpt276.as3.model;

public class Cell {
    // Variables
    private int rowCell;
    private int columnCell;
    private int numberOfHiddenMines;
    private boolean isMine;


    // Constructor
    public Cell() {
        this.rowCell = -1;
        this.columnCell = -1;
        this.numberOfHiddenMines = -1;
        this.isMine = false;
    }

    // Getter methods
    public int getRowCell() {
        return rowCell;
    }
    public int getColumnCell() {
        return columnCell;
    }
    public int getNumberOfHiddenMines() {
        return numberOfHiddenMines;
    }
    public boolean isMine() {
        return isMine;
    }

    // Setter methods
    public void setRowCell(int rowCell) {
        this.rowCell = rowCell;
    }
    public void setColumnCell(int columnCell) {
        this.columnCell = columnCell;
    }
    public void setNumberOfHiddenMines(int numberOfHiddenMines) {
        this.numberOfHiddenMines = numberOfHiddenMines;
    }
    public void increaseNumOfHiddenMines() {
        this.numberOfHiddenMines = numberOfHiddenMines + 1;
    }

    public void decreaseNumOfHiddenMines() {
        this.numberOfHiddenMines = numberOfHiddenMines - 1;
    }
    public void setMine(boolean mine) {
        isMine = mine;
    }
}
