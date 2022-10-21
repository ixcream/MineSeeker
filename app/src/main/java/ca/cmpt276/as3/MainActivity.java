package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

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

        TextView title = findViewById(R.id.tvTitle);
        ImageView apple1 = findViewById(R.id.imgApple1);
        ImageView apple2 = findViewById(R.id.imgApple2);

        // ANIMATION (cite: https://github.com/daimajia/AndroidViewAnimations)
        fadeAnimation(title);
        bounceAnimation(apple1);
        bounceAnimation(apple2);




    }

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

    private void onRegisterClick() {
        // Launch main menu
        Intent intent = MenuActivity.makeIntent(MainActivity.this);
        startActivity(intent);
    }
}