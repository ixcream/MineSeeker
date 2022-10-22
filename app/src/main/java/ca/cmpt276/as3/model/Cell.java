package ca.cmpt276.as3.model;

// Cell Class
/* Stores a single Cell of a grid */
public class Cell {
    // Variables
    private int rowCell;
    private int columnCell;
    private int numberOfHiddenMines;
    private boolean isMine;
    private boolean isScanned;


    // Constructor
    public Cell() {
        this.rowCell = -1;
        this.columnCell = -1;
        this.numberOfHiddenMines = -1;
        this.isMine = false;
        this.isScanned = false;
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
    public boolean isScanned() {
        return isScanned;
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
    public void setMine(boolean mine) {
        isMine = mine;
    }
    public void setScanned(boolean scanned) {
        this.isScanned = scanned;
    }


    // Helper methods
    public void increaseNumOfHiddenMines() {
        this.numberOfHiddenMines = numberOfHiddenMines + 1;
    }
    public void decreaseNumOfHiddenMines() {
        this.numberOfHiddenMines = numberOfHiddenMines - 1;
    }
}
