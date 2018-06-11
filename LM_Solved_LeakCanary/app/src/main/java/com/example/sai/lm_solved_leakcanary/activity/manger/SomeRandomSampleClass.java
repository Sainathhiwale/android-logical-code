package com.example.sai.lm_solved_leakcanary.activity.manger;

/*
   created by sainath 12/6/2018
 */
import android.content.Context;

public class SomeRandomSampleClass {
    private static SomeRandomSampleClass instance;
    private Context mContext;

    private SomeRandomSampleClass(Context context){
        mContext=context;
    }

    public static SomeRandomSampleClass getInstance(Context context){
        if(instance ==null){
            instance =new SomeRandomSampleClass(context.getApplicationContext());
        }
        return instance;
    }
}