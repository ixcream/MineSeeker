package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.widget.Toolbar;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView title;
    private ImageView apple1;
    private ImageView apple2;
    private Timer timer;
    private boolean isButtonClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.tbWelcome);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Apple Picker");
        title = findViewById(R.id.tvTitle);
        apple1 = findViewById(R.id.imgApple1);
        apple2 = findViewById(R.id.imgApple2);
        isButtonClicked = false;

        // Button to main menu
        findViewById(R.id.btnMainMenu).setOnClickListener(v -> onRegisterClick());

        // ANIMATION (cite: https://github.com/daimajia/AndroidViewAnimations)
        fadeAnimation(title);
        bounceAnimation(apple1);
        bounceAnimation(apple2);

        //4 SECOND TIMER
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!isButtonClicked) {
                    startMainMenu();
                }
            }
        }, 4000);
    }

    @Override
    public void onBackPressed() {
        isButtonClicked = true;
        finish();
    };

    private void fadeAnimation(TextView text) {
        YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .playOn(text);
    }

    private void bounceAnimation(ImageView img) {
        YoYo.with(Techniques.Bounce)
                .duration(700)
                .repeat(10)
                .playOn(img);
    }

    private void startMainMenu() {
        Intent intent = MenuActivity.makeIntent(MainActivity.this);
        startActivity(intent);
        finish();
    }

    private void onRegisterClick() {
        // Launch main menu
        isButtonClicked = true;
        startMainMenu();
    }
}