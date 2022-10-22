package ca.cmpt276.as3.model;

import android.graphics.Path;

// Options Class
/* Stores settings using a Singleton from OptionsActivity for use in GameActivity */
public class Options {
    // Variables
    private int rows;
    private int columns;
    private int totalMines;


    // Singleton
    private static Options instance;
    private Options() {
        // Private call to prevent instantiation
    }

    public static Options getInstance() {
        if (instance == null) {
            instance = new Options();
        }
        return instance;
    }

    public static void setInstance(Options options) {
        instance = options;
    }

    // Getter methods
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
    public int getTotalMines() {
        return totalMines;
    }

    // Setter methods
    public void setRows(int rows) {
        this.rows = rows;
    }
    public void setColumns(int columns) {
        this.columns = columns;
    }
    public void setTotalMines(int totalMines) {
        this.totalMines = totalMines;
    }
}
