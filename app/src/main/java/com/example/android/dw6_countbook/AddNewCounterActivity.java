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
        loadFromFile();

        final EditText counterNameEditText = (EditText) findViewById(R.id.counterNameEditText);
        final EditText setInitialValueEditText = (EditText) findViewById(R.id.setInitialValueEditText);
        final EditText commenntEditText = (EditText) findViewById(R.id.commenntEditText);
        final Button button2 = (Button) findViewById(R.id.button2);
        Intent intent = getIntent();

        /** the following lines are commented out and should be ignored, failed to implement
         *  EditText only allows non negative number, and Integer.parseInt makes sure it's int so we are good for
         *  if I cannot get it to work as I intended, should be deleted before final push
         //int position = intent.getIntExtra("position", 0);
         // reference https://stackoverflow.com/questions/4384890/how-to-disable-an-android-button answer by Deepak Sharma
         // also https://stackoverflow.com/questions/8225245/enable-and-disable-button-according-to-the-text-in-edittext-in-android
         // button2.setEnabled(false);          // enable by btn.setEnabled(true);

         */

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // When an activity exits, it can call setResult(int), int RESULT_OK
                setResult(RESULT_OK);

                String name = counterNameEditText.getText().toString();

                // reference https://stackoverflow.com/questions/15037465/converting-edittext-to-int-android answer by Harshid
                int initialValue = Integer.parseInt(setInitialValueEditText.getText().toString());

                // if no comment was provided, would add empty comment
                String comment = commenntEditText.getText().toString();
                counters.add(new Counter(name, comment, initialValue));
                saveInFile();

                finish();
            }
        });


    }


/** this part of code is commented out and should be ignored, cannot make it work for now, might try to come back later
 *  if I cannot get it to work as I intended, should be deleted before final push
 public void saveNew(View v){
 Intent intent = getIntent();
 // name
 EditText counterNameEditText = (EditText) findViewById(R.id.counterNameEditText);
 // init val
 EditText setInitialValueEditText = (EditText) findViewById(R.id.setInitialValueEditText);
 // comment
 EditText commenntEditText = (EditText) findViewById(R.id.commenntEditText);
 Button button2 = (Button) findViewById(R.id.button2);

 int initValue;
 String comment = commenntEditText.getText().toString();
 String counterName = counterNameEditText.getText().toString();

 if (Integer.parseInt(counterNameEditText.getText().toString()) >= 0){
 initValue = Integer.parseInt(counterNameEditText.getText().toString());
 }

 // temp solution, if somehow user is able to enter negative value in EditText, set initial value to 0
 else {
 initValue = 0;
 }

 if (comment.isEmpty() && !counterName.isEmpty()){
 Counter newcounter = new Counter(counterName,initValue);
 counters.add(newcounter);
 }
 else if (!comment.isEmpty() && !counterName.isEmpty()){
 Counter newcounter = new Counter(counterName,comment,initValue);
 counters.add(newcounter);
 }


 saveInFile();
 finish();


 }

 */
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
