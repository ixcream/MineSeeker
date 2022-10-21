package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.tbWelcome);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Apple Picker");

        // Button to main menu
        findViewById(R.id.btnMainMenu).setOnClickListener(v -> onRegisterClick());
    }

    private void onRegisterClick() {
        // Launch main menu
        Intent intent = MenuActivity.makeIntent(MainActivity.this);
        startActivity(intent);
    }
}