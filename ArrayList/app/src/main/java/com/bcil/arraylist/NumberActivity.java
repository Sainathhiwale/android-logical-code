package com.bcil.arraylist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity {
    private static final String TAG = "NumberActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        Intent intent = getIntent();
        intent.getStringExtra("Data");

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
     //   int index = 0;
        LinearLayout rootView  = (LinearLayout)findViewById(R.id.llinerRoot);
        for (int index=0;index<words.size();index++){
            TextView textView = new TextView(this);
            textView.setText(words.get(index));
            rootView.addView(textView);
            Log.v(TAG,"Index:"+ index +"values:"+ words.get(index));
        }
       /* while (index<words.size()){
            TextView textView = new TextView(this);
            textView.setText(words.get(index));
            rootView.addView(textView);
            index++;
        }*/
       /* LinearLayout rootView  = (LinearLayout)findViewById(R.id.llinerRoot);
        TextView textView = new TextView(this);
        textView.setText(words.get(index));
        rootView.addView(textView);
        index = index+1;
        TextView textView2 = new TextView(this);
        textView2.setText(words.get(index));
        rootView.addView(textView2);
        index = index+1;
        TextView textView3 = new TextView(this);
        textView3.setText(words.get(index));
        rootView.addView(textView3);*/

       /* String [] words = new String[10];
        words[0]="one";
        words[1]="two";
        words[2]="three";
        words[3]="four";
        words[4]="five";
        words[5]="six";
        words[6]="seven";
        words[7]="eight";
        words[8]="nine";
        words[9]="ten";

        Log.v(TAG, "onCreate:words index is "+words[0]);
        Log.v(TAG, "onCreate:words index is "+words[1]);
        Log.v(TAG, "onCreate:words index is "+words[2]);
        Log.v(TAG, "onCreate:words index is "+words[3]);
        Log.v(TAG, "onCreate:words index is "+words[4]);
        Log.v(TAG, "onCreate:words index is "+words[5]);
        Log.v(TAG, "onCreate:words index is "+words[6]);
        Log.v(TAG, "onCreate:words index is "+words[7]);
        Log.v(TAG, "onCreate:words index is "+words[8]);
        Log.v(TAG, "onCreate:words index is "+words[9]);*/
    }
}
