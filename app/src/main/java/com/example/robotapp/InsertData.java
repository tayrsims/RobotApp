package com.example.robotapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertData extends AppCompatActivity {

    EditText insert_id, insert_name, insert_data;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        insert_id = findViewById(R.id.insert_id);
        insert_name = findViewById(R.id.insert_name);
        insert_data = findViewById(R.id.insert_data);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RobotDatabase RoboDB = new RobotDatabase(InsertData.this);
                RoboDB.addBook(insert_id.getText().toString().trim(),
                        insert_name.getText().toString().trim(),
                        Integer.valueOf(insert_data.getText().toString().trim()));
            }
        });
    }
}