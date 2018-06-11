package com.example.sai.lm_solved_leakcanary.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sai.lm_solved_leakcanary.R;
import com.example.sai.lm_solved_leakcanary.activity.manger.SomeRandomSampleClass;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // SomeRandomSampleClass sampleObject= SomeRandomSampleClass.getInstance(this); // this keyword arise the memory leak problem in app and app will be crashes

        SomeRandomSampleClass sampleObject2= SomeRandomSampleClass.getInstance(getApplicationContext()); // getApplicationContext() method is used to solved the memory leak problem with help of leakcanery libaray
    }
}
