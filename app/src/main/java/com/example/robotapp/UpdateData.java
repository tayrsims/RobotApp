package com.example.robotapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {

    EditText update_num, update_name, update_data;
    Button update_button, delete_button;

    String id, num, name, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        update_num = findViewById(R.id.update_num2);
        update_name = findViewById(R.id.update_name2);
        update_data = findViewById(R.id.update_data2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar num after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(num);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                RobotDatabase RoboDB = new RobotDatabase(UpdateData.this);
                num = update_num.getText().toString().trim();
                name = update_name.getText().toString().trim();
                data = update_data.getText().toString().trim();
                RoboDB.updateData(id, Integer.parseInt(num), name, data);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("num") &&
                getIntent().hasExtra("name") && getIntent().hasExtra("data")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            num = getIntent().getStringExtra("num");
            name = getIntent().getStringExtra("name");
            data = getIntent().getStringExtra("data");

            //Setting Intent Data
            update_num.setText(num);
            update_name.setText(name);
            update_data.setText(data);
            Log.d("stev", num+" "+name+" "+data);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + num + " ?");
        builder.setMessage("Are you sure you want to delete " + num + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RobotDatabase RoboDB = new RobotDatabase(UpdateData.this);
                RoboDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}