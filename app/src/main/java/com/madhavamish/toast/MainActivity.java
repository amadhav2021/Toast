package com.madhavamish.toast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView top_left;
    TextView top_right;
    TextView bottom_left;
    TextView bottom_right;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    SeekBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        top_left = findViewById(R.id.top_left);
        top_right = findViewById(R.id.top_right);
        bottom_left = findViewById(R.id.bottom_left);
        bottom_right = findViewById(R.id.bottom_right);
        preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);

        editor = preferences.edit();

        int gryffindor, ravenclaw, hufflepuff, slytherin = 0;

        bar = findViewById(R.id.seek_bar);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float edited = (float)(14+progress/2.0);
                top_left.setTextSize(edited);
                top_right.setTextSize(edited);
                bottom_left.setTextSize(edited);
                bottom_right.setTextSize(edited);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView label = (TextView) v;
                Toast.makeText(getApplicationContext(), "Processing...", Toast.LENGTH_SHORT).show();
                String text = ((TextView) v).getText().toString();
                int val = preferences.getInt(text, 0) + 1;
                editor.putInt(text, val);
                editor.apply();
            }
        };

        top_left.setOnClickListener(click);
        top_right.setOnClickListener(click);
        bottom_left.setOnClickListener(click);
        bottom_right.setOnClickListener(click);
    }
}
