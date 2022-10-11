package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.tbGame);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Welcome screen");
    }
}