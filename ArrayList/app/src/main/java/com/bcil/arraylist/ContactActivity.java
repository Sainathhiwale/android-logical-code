package com.bcil.arraylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {
   ListView listView;
   LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> words = new ArrayList<String>();
        words.add("one");
        words.add("two");
        words.add("three");
        words.add("four");
        words.add("five");
        words.add("six");
        words.add("seven");
        words.add("eight");
        words.add("nine");
        words.add("ten");
        words.add("elevone");
        words.add("twelve");
        words.add("threetheen");
        words.add("fortheen");
        words.add("fitheen");
        words.add("sixtheen");
        words.add("seventheen");
        words.add("one");
        words.add("two");
        words.add("three");
        words.add("four");
        words.add("five");
        words.add("six");
        words.add("seven");
        words.add("eight");
        words.add("nine");
        words.add("ten");
        words.add("elevone");
        words.add("twelve");
        words.add("threetheen");
        words.add("fortheen");
        words.add("fitheen");
        words.add("sixtheen");
        words.add("seventheen");
        words.add("one");
        words.add("two");
        words.add("three");
        words.add("four");
        words.add("five");
        words.add("six");
        words.add("seven");
        words.add("eight");
        words.add("nine");
        words.add("ten");
        words.add("elevone");
        words.add("twelve");
        words.add("threetheen");
        words.add("fortheen");
        words.add("fitheen");
        words.add("sixtheen");
        words.add("seventheen");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,words);
        listView.setAdapter(arrayAdapter);



    }

}
