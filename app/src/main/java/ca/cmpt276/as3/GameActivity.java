package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import ca.cmpt276.as3.model.Options;

public class GameActivity extends AppCompatActivity {
    // Get Instance
    Options options = Options.getInstance();

    Button buttons[][] = new Button[options.getRows()][options.getColumns()];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);





        // Toolbar
        Toolbar toolbar = findViewById(R.id.tbGame);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Welcome screen");

        // Populate Dynamic Buttons
        populateButtons();



    }

    // Creates dynamic buttons
    private void populateButtons() {
        TableLayout grid = findViewById(R.id.buttonGrid);

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

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                // Prevent clipping
                button.setPadding(0, 0, 0, 0);

                button.setOnClickListener(v -> gridButtonClicked(FINAL_ROW, FINAL_COLUMN));

                tableRow.addView(button);
                buttons[rows][columns] = button;
            }

        }

    }

    // Button helper method
    private void gridButtonClicked(int row, int column) {
        Button button = buttons[row][column];
        button.setBackgroundResource(R.drawable.box);

        // Scale image properly
        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.box);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));


        // If mine, button.setBackgroundResource(R.drawable."name of mine img");
        // Else make it show the number

    }
}