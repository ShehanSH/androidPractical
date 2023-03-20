package com.example.btapp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private ArrayList<String> deviceList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        Button onButton = findViewById(R.id.onButton);
        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bluetoothAdapter == null) {
                    Toast.makeText(MainActivity.this, "Device does not support Bluetooth", Toast.LENGTH_SHORT).show();
                } else {
                    if (!bluetoothAdapter.isEnabled()) {
                        Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivity(enableBluetoothIntent);
                        Toast.makeText(MainActivity.this, "Bluetooth turned on", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Bluetooth is already on", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button visibleButton = findViewById(R.id.visibleButton);
        visibleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent makeDeviceVisibleIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                makeDeviceVisibleIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
                startActivity(makeDeviceVisibleIntent);
                Toast.makeText(MainActivity.this, "Device is now visible to other devices", Toast.LENGTH_SHORT).show();
            }
        });

        listView = findViewById(R.id.deviceList);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, deviceList);
        listView.setAdapter(arrayAdapter);

        Button listButton = findViewById(R.id.listButton);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bluetoothAdapter == null) {
                    Toast.makeText(MainActivity.this, "Device does not support Bluetooth", Toast.LENGTH_SHORT).show();
                } else {
                    if (!bluetoothAdapter.isEnabled()) {
                        Toast.makeText(MainActivity.this, "Please turn on Bluetooth first", Toast.LENGTH_SHORT).show();
                    } else {
                        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                        if (pairedDevices.size() > 0) {
                            for (BluetoothDevice device : pairedDevices) {
                                deviceList.add(device.getName() + "\n" + device.getAddress());
                            }
                            arrayAdapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Paired devices listed", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "No paired devices found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        Button offButton = findViewById(R.id.offButton);
        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bluetoothAdapter.isEnabled()) {
                    bluetoothAdapter.disable();

                    Toast.makeText(MainActivity.this, "Bluetooth turned off", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Bluetooth is already off", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
