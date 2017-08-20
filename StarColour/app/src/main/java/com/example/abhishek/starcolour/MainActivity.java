package com.example.abhishek.starcolour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String[] colours = {"blue", "yellow", "green"};
//        ArrayList arrayList = new ArrayList(Arrays.asList(colours));
//        ListView listView = (ListView) findViewById(R.id.listView);
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
//        listView.setAdapter(arrayAdapter);

        ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayList<String> colour = new ArrayList<String>();

        colour.add("blue");
        colour.add("green");
        colour.add("red");
        colour.add("yellow");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, colour);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Move to next screen.
                Intent intent = new Intent(getApplicationContext(), secondActivity.class);
                // Pass infromation from one screen to other screen.
                intent.putExtra("name", colour.get(i));
                // startTheNewScreen.
                startActivity(intent);

            }
        });
    }
}
