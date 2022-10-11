package ca.cmpt276.as3;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.tbMainMenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Main menu");

        // Buttons
//        btnPlay = findViewById(R.id.btnPlay);
//        btnPlay.setOnClickListener((View.OnClickListener) this);
//        findViewById(R.id.btnOptions).setOnClickListener(v -> onRegisterClick());
//        findViewById(R.id.btnHelp).setOnClickListener(v -> onRegisterClick());
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btnPlay:
//                Toast.makeText(this, "play", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                break;
//        }
//    };


    public static Intent makeIntent(Context context) {
        return new Intent(context, MenuActivity.class);
    }
}