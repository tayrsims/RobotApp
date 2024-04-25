package com.example.robotapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class HomeScreen extends AppCompatActivity {


    private static final String DEVICE_ADDRESS = "10:7C:61:2E:7E:9E";
    private static final UUID UUID_SERIAL_PORT_SERVICE = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket bluetoothSocket;
    private OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Initialize BluetoothAdapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Check if Bluetooth is supported on this device
        if (bluetoothAdapter == null) {
            // Bluetooth is not supported. Handle accordingly.
            // For example, display a message to the user or disable Bluetooth-related functionality.
        }

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

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommandToRobot("forward");
            }
        });

        reverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommandToRobot("reverse");
            }
        });

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommandToRobot("left");
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommandToRobot("right");
            }
        });
    }

    private void sendCommandToRobot(String command) {
        try {
            // Check if Bluetooth is supported and enabled
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
                Log.e("Bluetooth", "Bluetooth is not enabled or supported");
                return;
            }

            // Get the Bluetooth device by its address
            bluetoothDevice = bluetoothAdapter.getRemoteDevice(DEVICE_ADDRESS);

            // Establish a Bluetooth connection
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(UUID_SERIAL_PORT_SERVICE);
            bluetoothSocket.connect();

            // Get the output stream from the Bluetooth socket
            outputStream = bluetoothSocket.getOutputStream();

            // Send the command to the robot
            outputStream.write(command.getBytes());

        } catch (IOException e) {
            Log.e("Bluetooth", "Error occurred during Bluetooth communication", e);
            // Handle any errors that occur during Bluetooth communication
        } finally {
            // Close the Bluetooth socket and output stream to release resources
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bluetoothSocket != null) {
                    bluetoothSocket.close();
                }
            } catch (IOException e) {
                Log.e("Bluetooth", "Error occurred while closing Bluetooth resources", e);
            }
        }
    }
}
