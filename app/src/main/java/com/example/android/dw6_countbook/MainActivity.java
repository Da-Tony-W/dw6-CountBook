package com.example.android.dw6_countbook;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.dw6_countbook.AddNewCounterActivity;
import com.example.android.dw6_countbook.Counter;
import com.example.android.dw6_countbook.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private ListView counterListView;


    private ArrayList<Counter> counters = new ArrayList<Counter>();
    private ArrayAdapter<Counter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //loadFromFile();       // loadFromFile() in onStart()


        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        //Button buttonDelete = (Button) findViewById(R.id.buttonDelete);

        counterListView = (ListView) findViewById(R.id.counterListView);
        /**
         * reference: Java for Android  2nd Edition by Budi Kurniavan Chinese version Chapter 31, Listing 31.4
         * and https://stackoverflow.com/questions/5644543/passing-listview-row-positions-through-intents-to-another-class
         */

        counterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    final View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, OperateCounterActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);

            }
        });


    }


    /**
     note to self: use android:onClick="addNewCounter"
     https://android-developers.googleblog.com/2009/10/ui-framework-changes-in-android-16.html

     buttonAdd.setOnClickListener(new View.OnClickListener() {
    @Override public void onClick(View v) {

    }
    });
     */




    /**
     * start AddNewCounterActivity
     * reference: https://developer.android.com/training/basics/firstapp/starting-activity.html#CreateActivity
     *
     * @param view
     */
    public void addNewCounter(View view) {
        Intent intent = new Intent(this, AddNewCounterActivity.class);

        startActivity(intent);
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();


        // reference: Java for Android  2nd Edition by Budi Kurniavan Chinese version Chapter 31.2
        adapter = new ArrayAdapter<Counter>(this,
                android.R.layout.simple_list_item_1, counters);
        //R.layout.list_item, counters);
        counterListView.setAdapter(adapter);

        //String[] counters = loadFromFile();
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        //       R.layout.list_item, counters);
        //oldTweetsList.setAdapter(adapter);
    }


    /**
     * load file, code from CMPUT301 Lab lonelytwitter source codes
     */

    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Counter>>() {
            }.getType();
            counters = gson.fromJson(in, listType);

            //google/gson/blob/master

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            counters = new ArrayList<Counter>();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(counters, writer);
            writer.flush();


            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }


}
