package com.dorvis.parcelable_passdatademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonNextActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNextActivity =findViewById(R.id.bt_next);

        buttonNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondActIntent = new Intent(MainActivity.this,SecondActivity.class);
                secondActIntent.putExtra("samplekey","hello sending the data ");

                Person person = new Person();
                person.setFirstName("sainath");
                person.setLastName("Hiwale");
                person.setQualification("MCA");

                secondActIntent.putExtra("person",person);
                startActivity(secondActIntent);
            }
        });
    }
}
