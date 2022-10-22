package ca.cmpt276.as3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import ca.cmpt276.as3.model.Grid;
import ca.cmpt276.as3.model.Options;


// Game Class
/* Creates the UI when the user clicks Play Game */
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
    AlertDialog.Builder winMsg;
    MediaPlayer eatSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        grid = new Grid(options.getRows(), options.getColumns(), options.getTotalMines());

        // Find Id
        totalButtons = new Button[options.getRows()][options.getColumns()];
        totalScans = findViewById(R.id.totalScans);
        minesFound = findViewById(R.id.minesFound);
        winMsg = new AlertDialog.Builder(this);

        // If user clicks out of alert, return to Main Menu
        winMsg.setOnDismissListener(dialog -> finish());

        // Toolbar
        Toolbar toolbar = findViewById(R.id.tbGame);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Play Game");

        // Populate Dynamic Buttons
        populateButtons();
    }

    // Create dynamic buttons
    private void populateButtons() {
        // Initialization
        TableLayout grid = findViewById(R.id.buttonGrid);
        numOfScans = 0;
        numOfMinesFound = 0;
        minesFound.setText("Mines Found: " + numOfMinesFound +" of " + options.getTotalMines());

        // Set layout
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

        // Lock button sizes
        lockButtonSize();

        // If grid at [row][column] is a mine, show mine and decrement totalMines
        if (grid.cellAtCoord(row, column).isMine()) {
            button.setBackgroundResource(R.drawable.apple);
            numOfMinesFound++;

            // Scale image properly
            int newWidth = button.getWidth();
            int newHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource
                    (getResources(), R.drawable.apple);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap
                    (originalBitmap, newWidth, newHeight, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));

            // Update mines at grid cell
            grid.cellAtCoord(row, column).setMine(false);
            grid.decreaseNumOfMines(row, column);

            // Update scanned cells number and mines found text
            scannedTextCell(row, column);
            minesFound.setText
                    ("Mines Found: " + numOfMinesFound +" of " + options.getTotalMines());

            // Play sound
            playSound();

            // Display winning message when user finds all mines
            if (numOfMinesFound == options.getTotalMines()) {
                winMsg();
            }
        }
        // Else, scan cell and display # of mines in row/column
        else {
            button.setText
                    (Integer.toString(grid.cellAtCoord(row, column).getNumberOfHiddenMines()));

            // Update total scan
            if (!grid.cellAtCoord(row, column).isScanned()) {
                numOfScans++;
                totalScans.setText("Total scans: " + numOfScans);
                grid.cellAtCoord(row, column).setScanned(true);
                scannedTextCell(row, column);
            }
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
                totalButtons[row][i].setText
                        (Integer.toString(grid.cellAtCoord(row, i).getNumberOfHiddenMines()));
            }
            btnFlash(totalButtons[row][i]);
        }

        // Go through entire row
        for (int i = 0; i < options.getRows(); i++) {
            if (grid.cellAtCoord(i, column).isScanned()) {
                totalButtons[i][column].setText
                        (Integer.toString(grid.cellAtCoord(i, column).getNumberOfHiddenMines()));
            }
            btnFlash(totalButtons[i][column]);
        }
    }

    // Makes button selected rows/column flash
    private void btnFlash(Button btn) {
        YoYo.with(Techniques.Flash)
                .duration(300)
                .playOn(btn);
    }

    // Dialog message when user finds all mines
    private void winMsg() {
        // Allow user to exit by clicking outside of alert box
        winMsg.setCancelable(true);

        // Alert display
         winMsg.setTitle("Congratulations")
                .setMessage("You've found all the apples! Time to chomp!")
                .setIcon(R.drawable.apple)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
    }

    // Play sound method
    private void playSound() {
        eatSound = MediaPlayer.create(this, R.raw.minecraft_eat_sound);

        eatSound.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                eatSound.start();
            }
        });
        eatSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                eatSound.release();
            }
        });


    }
}