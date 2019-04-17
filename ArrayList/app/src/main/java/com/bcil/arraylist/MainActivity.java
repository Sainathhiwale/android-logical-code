package com.bcil.arraylist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.bcil.arraylist.listener.AnimalListener;
import com.bcil.arraylist.listener.ContactListener;
import com.bcil.arraylist.listener.NumberListener;

public class MainActivity extends AppCompatActivity {
   LinearLayout llinerContact;
   LinearLayout llinerNumber;
   LinearLayout llinerAnimal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llinerNumber = (LinearLayout)findViewById(R.id.llinerNumber);
        llinerContact  = (LinearLayout)findViewById(R.id.llinerContact);
        llinerAnimal  = (LinearLayout)findViewById(R.id.llinerAnimal);
        llinerNumber.setOnClickListener(new NumberListener(this));
        llinerContact.setOnClickListener(new ContactListener(this));
        llinerAnimal.setOnClickListener(new AnimalListener(this));
    }
}
