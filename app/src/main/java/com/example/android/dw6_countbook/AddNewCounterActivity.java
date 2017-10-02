package com.example.android.dw6_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

public class AddNewCounterActivity extends AppCompatActivity {


    private static final String FILENAME = "file.sav";
    private ListView counterListView;


    private ArrayList<Counter> counters = new ArrayList<Counter>();
    private ArrayAdapter<Counter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_counter);

        final EditText counterNameEditText = (EditText) findViewById(R.id.counterNameEditText);
        final EditText setInitialValueEditText = (EditText) findViewById(R.id.setInitialValueEditText);
        final EditText commenntEditText = (EditText) findViewById(R.id.commenntEditText);
        final Button button2 = (Button) findViewById(R.id.button2);

        Intent intent = getIntent();
        //int position = intent.getIntExtra("position", 0);
        loadFromFile();


        // https://stackoverflow.com/questions/4384890/how-to-disable-an-android-button answer by Deepak Sharma
        // enable by btn.setEnabled(true);
        button2.setEnabled(false);



    }

    /**
     * load file, code from CMPUT301 Lab lonelytwitter source codes
     */


    public void saveNew(View v){
        EditText setInitialValueEditText = (EditText) findViewById(R.id.setInitialValueEditText);
    }


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


    /**
     * save file after adding new counter,code from CMPUT301 Lab lonelytwitter source codes
     */
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
