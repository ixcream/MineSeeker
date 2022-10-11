package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.tbOptions);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Options");
    }
}