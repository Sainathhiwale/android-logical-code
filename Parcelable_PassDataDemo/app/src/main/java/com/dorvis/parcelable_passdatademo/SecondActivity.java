package com.dorvis.parcelable_passdatademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView textViewMessage,textViewPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        textViewMessage=(TextView)findViewById(R.id.textViewMessage);
        textViewPerson=(TextView)findViewById(R.id.textViewPerson);

        String message=getIntent().getStringExtra("sampleKey");
        textViewMessage.setText(message);

        Person person=(Person)getIntent().getParcelableExtra("person");

        textViewPerson.setText(person.getFirstName()+", "+person.getLastName()+", "+person.getQualification());
    }
}
