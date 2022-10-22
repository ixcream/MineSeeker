package ca.cmpt276.as3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

// Help Activity
/* Creates the UI when the user clicks Help */
public class HelpActivity extends AppCompatActivity {
    private TextView tvAuthors;
    private TextView resourcesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.tbHelp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Help");

        tvAuthors = findViewById(R.id.tvAuthors);
        tvAuthors.setMovementMethod(LinkMovementMethod.getInstance());

        resourcesList = findViewById(R.id.tvResourceList);
        resourcesList.setMovementMethod(LinkMovementMethod.getInstance());

    }
}