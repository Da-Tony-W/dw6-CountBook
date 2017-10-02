package com.example.android.dw6_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.android.dw6_countbook.R.id.counterName;
import static com.example.android.dw6_countbook.R.id.positionTextView;

public class OperateCounterActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operate_counter);

        /**
         * reference https://stackoverflow.com/questions/5644543/passing-listview-row-positions-through-intents-to-another-class
         */
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        TextView counterNameTextView = (TextView) findViewById(positionTextView);
        counterNameTextView.setText(String.valueOf(position));
        ;


    }

    /*
    private void displayPosition(String message){
        TextView counterNameTextView = (TextView) findViewById(counterName);
        counterNameTextView.setText("position");
    }
    */
}
