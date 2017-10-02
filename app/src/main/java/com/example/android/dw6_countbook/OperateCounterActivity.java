package com.example.android.dw6_countbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

import static android.provider.Telephony.Mms.Part.FILENAME;
import static com.example.android.dw6_countbook.R.id.commentTextView;
import static com.example.android.dw6_countbook.R.id.counterName;
import static com.example.android.dw6_countbook.R.id.positionTextView;
import static com.example.android.dw6_countbook.R.id.reset_button;

public class OperateCounterActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private ListView counterListView;

    private ArrayList<Counter> counters = new ArrayList<Counter>();
    private ArrayAdapter<Counter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operate_counter);
        /**
         * reference https://stackoverflow.com/questions/5644543/passing-listview-row-positions-through-intents-to-another-class
         */
        Intent intent = getIntent();
        final int position = intent.getIntExtra("position", 0);
        loadFromFile();

        final Counter counter = counters.get(position);
        String comment = counter.getComment();
        String name = counter.getName();
        int currentValue = counter.getValue();
        int initialValue = counter.getInitialValue();

        // text views and buttons
        TextView counterName = (TextView) findViewById(R.id.counterName);
        TextView commentTextView = (TextView) findViewById(R.id.commentTextView);
        final TextView currentValueTextView = (TextView) findViewById(R.id.currentValueTextView);
        TextView initialValueTextView = (TextView) findViewById(R.id.initialValueTextView);
        Button inc_button = (Button) findViewById(R.id.inc_button);
        Button dec_button = (Button) findViewById(R.id.dec_button);
        Button edit_button = (Button) findViewById(R.id.edit_button);
        Button delete_button = (Button) findViewById(R.id.delete_button);
        Button reset_button = (Button) findViewById(R.id.reset_button);


        // display name, comment, current view, current value, and initial value, also display position for debug
        counterName.setText(name);
        commentTextView.setText(comment);
        TextView counterNameTextView = (TextView) findViewById(positionTextView);
        counterNameTextView.setText(String.valueOf(position));

        currentValueTextView.setText(String.valueOf(currentValue));
        initialValueTextView.setText(String.valueOf(initialValue));

        // java.util.ArrayList.set(int i, E element) replace item in index i with element
        // reference https://stackoverflow.com/questions/23981008/how-to-replace-existing-value-of-arraylist-element-in-java


        inc_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(Activity.RESULT_OK);
                counter.inc();
                counter.setDate();
                currentValueTextView.setText(Integer.toString(counter.getValue()));
                counters.set(position,counter);
                saveInFile();
            }
        });

        dec_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_OK);
                counter.dec();
                counter.setDate();
                currentValueTextView.setText(Integer.toString(counter.getValue()));
                counters.set(position,counter);
                saveInFile();
            }
        });



        delete_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_OK);
                //counters.set(position,counter);
                counters.remove(position);
                saveInFile();
                finish();
            }
        });

        reset_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_OK);
                counter.reset();
                counter.setDate();
                currentValueTextView.setText(Integer.toString(counter.getValue()));
                counters.set(position,counter);
                saveInFile();
            }
        });

        edit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_OK);
                Intent intent = new Intent(OperateCounterActivity.this, EditCounterActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

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
