package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import ca.cmpt276.as3.model.Options;

public class OptionsActivity extends AppCompatActivity implements
        View.OnClickListener {
    // Get Instance
    Options options = Options.getInstance();


    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        super.onPanelClosed(featureId, menu);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.tbOptions);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Options");

        // Find buttons
        RadioGroup sizeOfBoard = findViewById(R.id.sizeOptions);
        RadioGroup minesOfBoard = findViewById(R.id.minesOptions);

        sizeOfBoard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.size1:
                        options.setRows(4);
                        options.setColumns(6);
                        Log.i("yes", "works?");
                        break;
                    case R.id.size2:
                        options.setRows(5);
                        options.setColumns(10);
                        break;
                    case R.id.size3:
                        options.setRows(6);
                        options.setColumns(15);
                        break;
                    default:
                        break;
                }
            }
        });

        minesOfBoard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.Mines6:
                        options.setTotalMines(6);
                        break;
                    case R.id.Mines10:
                        options.setTotalMines(10);
                        break;
                    case R.id.Mines15:
                        options.setTotalMines(15);
                        break;
                    case R.id.Mines20:
                        options.setTotalMines(20);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}