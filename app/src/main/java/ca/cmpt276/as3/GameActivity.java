package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import ca.cmpt276.as3.model.Grid;
import ca.cmpt276.as3.model.Options;

public class GameActivity extends AppCompatActivity {
    // Get Instance
    Options options = Options.getInstance();

    // Initialization
    Button[][] totalButtons;
    TextView totalScans;
    TextView minesFound;
    int numOfScans;
    int numOfMinesFound;
    Grid grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        grid = new Grid(options.getRows(), options.getColumns(), options.getTotalMines());

        totalButtons = new Button[options.getRows()][options.getColumns()];
        totalScans = findViewById(R.id.totalScans);
        minesFound = findViewById(R.id.minesFound);


        // Toolbar
        Toolbar toolbar = findViewById(R.id.tbGame);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Play Game");

        // Populate Dynamic Buttons
        populateButtons();
    }

    // Creates dynamic buttons
    private void populateButtons() {
        TableLayout grid = findViewById(R.id.buttonGrid);
        numOfScans = 0;
        numOfMinesFound = 0;
        minesFound.setText("Mines Found: " + numOfMinesFound +" of " + options.getTotalMines());


        for (int rows = 0; rows < options.getRows(); rows++) {
            TableRow tableRow = new TableRow(this);
            grid.addView(tableRow);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));

            for (int columns = 0; columns < options.getColumns(); columns++) {
                final int FINAL_ROW = rows;
                final int FINAL_COLUMN = columns;

                // Set Layout
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                // Prevent clipping
                button.setPadding(0, 0, 0, 0);

                // Set up buttons when clicked
                button.setOnClickListener(v -> gridButtonClicked(FINAL_ROW, FINAL_COLUMN));
                tableRow.addView(button);
                totalButtons[rows][columns] = button;
            }
        }
    }

    // Button helper method
    private void gridButtonClicked(int row, int column) {
        Button button = totalButtons[row][column];

        // TODO - get rid of this when hand in, was using this for testing
        // and might again later
        // Toast.makeText(getBaseContext(), "You pressed " + row + ", " + column, Toast.LENGTH_SHORT).show();

        // Lock button sizes
        lockButtonSize();

        // If isMine = true, show mine and decrement totalMines
        if (grid.cellAtCoord(row, column).isMine()) {
            button.setBackgroundResource(R.drawable.apple);
            numOfMinesFound++;

            // Scale image properly
            int newWidth = button.getWidth();
            int newHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.apple);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));

            // Update mines at grid cell
            grid.cellAtCoord(row, column).setMine(false);
            grid.decreaseNumOfMines(row, column);

            // Update scanned cells number and mines found text
            scannedTextCell(row, column);
            minesFound.setText("Mines Found: " + numOfMinesFound +" of " + options.getTotalMines());
        }
        // Else, scan cell and display # of mines in row/column
        else {
            button.setText(Integer.toString(grid.cellAtCoord(row, column).getNumberOfHiddenMines()));
            grid.cellAtCoord(row, column).setScanned(true);

            // Update total scan
            numOfScans++;
            totalScans.setText("Total scans: " + numOfScans);
        }
    }

    // Locks size of buttons
    private void lockButtonSize() {
        for (int rowCell = 0; rowCell < options.getRows(); rowCell++) {
            for (int columnCell = 0; columnCell < options.getColumns(); columnCell++) {
                Button button = totalButtons[rowCell][columnCell];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    // Update scanned cell text helper
    private void scannedTextCell(int row, int column) {
        // Go through entire column
        for (int i = 0; i < options.getColumns(); i++) {
            if (grid.cellAtCoord(row, i).isScanned()) {
                totalButtons[row][i].setText(Integer.toString(grid.cellAtCoord(row, i).getNumberOfHiddenMines()));
            }
        }

        // Go through entire row
        for (int i = 0; i < options.getRows(); i++) {
            if (grid.cellAtCoord(i, column).isScanned()) {
                totalButtons[i][column].setText(Integer.toString(grid.cellAtCoord(i, column).getNumberOfHiddenMines()));
            }
        }
    }
}