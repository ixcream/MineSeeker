package ca.cmpt276.as3.model;

import java.util.Random;

public class Grid {
    private int row;
    private int column;
    private int numOfMines;
    private Cell[][] grid;

    public Grid(int rows, int columns, int mines) {
        this.row = rows;
        this.column = columns;
        this.numOfMines = mines;

        this.grid = new Cell[row][column];

        // initialize all the cells
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                grid[i][j].setRowCell(i);
                grid[i][j].setColumnCell(j);
                grid[i][j].setNumberOfHiddenMines(0);
                grid[i][j].setMine(false);
            }
        }

        // Generate mines
        minePlacer();
    }

    public Cell cellAtCoord(int i, int j) {
        if (i >= row || i < 0 || j < 0 || j >= column) {
            throw new RuntimeException("invalid coords");
        }
        return grid[i][j];
    }

    // PRE: The min parameter is inclusive and the max is exclusive.
    private int randomInt(int min, int max) {
        Random randomInt = new Random();
        return randomInt.nextInt(max - min) + min;
    }

    // Increases the numOfMines of each cell in given row and column
    public void increaseNumOfMines(int curRow, int curCol) {
        if (curRow >= row || curRow < 0 || curCol < 0 || curCol >= column) {
            throw new RuntimeException("invalid coords");
        }

        // increase all the cells in the row
        for (int i = 0; i < curCol; i++) {
            cellAtCoord(curRow, i).increaseNumOfHiddenMines();
        }

        // increase all the cells in the column
        for (int i = 0; i < curRow; i++) {
            cellAtCoord(i, curCol).increaseNumOfHiddenMines();
        }
    }

    // Decrease the numOfMines of each cell in given row and column
    public void decreaseNumOfMines(int curRow, int curCol) {
        if (curRow >= row || curRow < 0 || curCol < 0 || curCol >= column) {
            throw new RuntimeException("invalid coords");
        }

        // decrease all the cells in the row
        for (int i = 0; i < curCol; i++) {
            cellAtCoord(curRow, i).decreaseNumOfHiddenMines();
        }

        // decrease all the cells in the column
        for (int i = 0; i < curRow; i++) {
            cellAtCoord(i, curCol).decreaseNumOfHiddenMines();
        }
    }

    // Random mine generator
    private void minePlacer() {
        int i = 0;

        while (i < numOfMines) {
            int ranRow = randomInt(0, row);
            int ranCol = randomInt(0, column);

            // Only set cells that aren't already mines into mines
            if (!cellAtCoord(ranRow, ranCol).isMine()) {
                cellAtCoord(ranRow, ranCol).setMine(true);
                increaseNumOfMines(ranRow, ranCol);
                i++;
            }
        }
    }
}
