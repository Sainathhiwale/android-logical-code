package com.dorvis.implicitintent_intent_filter;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button bt_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_next = findViewById(R.id.bt_next);

        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // explicit intent to used tiggered the action and call second activity
               /* Intent secondActIntent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(secondActIntent);*

                second way to call activity with help of explicit intent

                 Intent intent = new Intent();
                intent.setComponent(new ComponentName(getApplicationContext(),SecondActivity.class));
                startActivity(intent);
                */

               //using the implicit intent to tiggered a action and call SecondActivity with help of intent-filter

               Intent intent = new Intent();
               intent.setAction("com.dorvis.sainath");// any value u can put here
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                startActivity(intent);

                //go to manifest file and add intent filter there

            }
        });
    }
}
