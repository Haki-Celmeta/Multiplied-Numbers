package com.example.higherorlower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Range;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList;

    public void generateTimeTables(int timeTablesNumber) {
        arrayList = new ArrayList<>();
        for (int j = 1; j <= 100; j++){
            arrayList.add(Integer.toString(j * timeTablesNumber));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = findViewById(R.id.mySeekBar);
        listView = findViewById(R.id.myListView);

        int max = 20;
        int startingPoint = 1;
        seekBar.setMax(max);
        seekBar.setProgress(startingPoint);
        generateTimeTables(startingPoint);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int min = 1;
                int timeTablesNumber;
                if(i < min){
                    timeTablesNumber = min;
                    seekBar.setProgress(min);
                } else {
                    timeTablesNumber = i;
                }

                Log.i("Info", Integer.toString(timeTablesNumber));
                generateTimeTables(timeTablesNumber);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "You clicked " + Integer.parseInt(arrayList.get(i)), Toast.LENGTH_SHORT).show();
            }
        });
    }
}