package com.example.android.dw6_countbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "file.sav";
    private ListView counterListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);
        Button buttonDelete = (Button) findViewById(R.id.buttonDelete);

        counterListView =(ListView) findViewById(R.id.counterListView);








    }
}
