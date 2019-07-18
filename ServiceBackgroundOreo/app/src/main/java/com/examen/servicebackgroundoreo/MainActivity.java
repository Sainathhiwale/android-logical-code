package com.examen.servicebackgroundoreo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText etInput;
    private Button btnStart;
    private Button btnStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etInput =  findViewById(R.id.etInput);
        btnStart =  findViewById(R.id.btnStart);
        btnStop  = findViewById(R.id.btnStop);
    }

    public void startService(View view) {
        String input = etInput.getText().toString().trim();
        Intent serviceIntent = new Intent(this,ExampleServices.class);
        serviceIntent.putExtra("inputExtra",input);
        startService(serviceIntent);
    }

    public void stopService(View view) {
        Intent serviceIntent = new Intent(this,ExampleServices.class);
        stopService(serviceIntent);
    }
}
