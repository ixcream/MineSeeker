package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;

import ca.cmpt276.as3.model.Options;

// Main Menu Class
/* Displays the UI for the Main Menu */
public class MenuActivity extends AppCompatActivity implements
View.OnClickListener {
    ImageButton btnPlay;
    ImageButton btnOptions;
    ImageButton btnHelp;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String NAME = "options";
    Options options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        loadData();

        // Toolbar
        Toolbar toolbar = findViewById(R.id.tbMainMenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Main menu");

        // Buttons
        btnPlay = findViewById(R.id.btnPlay);
        btnOptions = findViewById(R.id.btnOptions);
        btnHelp = findViewById(R.id.btnHelp);

        btnPlay.setOnClickListener(this);
        btnOptions.setOnClickListener(this);
        btnHelp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent play = new Intent(MenuActivity.this, GameActivity.class);
        Intent options = new Intent(MenuActivity.this, OptionsActivity.class);
        Intent help = new Intent(MenuActivity.this, HelpActivity.class);
        switch (v.getId()) {
            case R.id.btnPlay:
                startActivity(play);
                break;
            case R.id.btnOptions:
                startActivity(options);
                break;
            case R.id.btnHelp:
                startActivity(help);
                break;
            default:
                break;
        }
    };

    @Override
    public void onBackPressed() {
        saveData();
        finish();
    };

    public static Intent makeIntent(Context context) {
        return new Intent(context, MenuActivity.class);
    }

    // Saves data for next launch
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(options);
        editor.putString(NAME, json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(NAME, "");

        if (!json.isEmpty()) {
            Options newOptions = gson.fromJson(json, Options.class);
            Options.setInstance(newOptions);
        }
        options = Options.getInstance();
    }
}