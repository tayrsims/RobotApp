package com.example.robotapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class HomeScreen extends AppCompatActivity {

    private Button settingsButton, homeButton, dataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        ImageButton forwardButton = findViewById(R.id.forwardBtn);
        ImageButton leftButton = findViewById(R.id.leftBtn);
        ImageButton rightButton = findViewById(R.id.rightBtn);
        ImageButton reverseButton = findViewById(R.id.reverseBtn);

        Button settingsButton = findViewById(R.id.settingsButton);
        Button homeButton = findViewById(R.id.homeButton);
        Button dataButton = findViewById(R.id.dataButton);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open settings activity or perform settings-related actions
                Intent intent = new Intent(HomeScreen.this, Settings.class);
                startActivity(intent);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the home screen (Optional: If you want to refresh the current screen)
                Intent intent = new Intent(HomeScreen.this, HomeScreen.class);
                startActivity(intent);
                finish(); // Optional: finish the current activity to prevent stacking
            }
        });

        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open data activity or perform data-related actions
                Intent intent = new Intent(HomeScreen.this, DataMain.class);
                startActivity(intent);
            }
        });
    }
}
