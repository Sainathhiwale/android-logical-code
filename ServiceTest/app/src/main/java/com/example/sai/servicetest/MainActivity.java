package com.example.sai.servicetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_startService;
    private Button bt_stopService;
    Intent intentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      Log.i("Mainactivity thread id", String.valueOf(Thread.currentThread().getId()));
        bt_startService = (Button)findViewById(R.id.bt_startService);
        bt_stopService = (Button)findViewById(R.id.bt_stopService);

        bt_startService.setOnClickListener(this);
        bt_stopService.setOnClickListener(this);

        intentService = new Intent(getApplicationContext(),MyServices.class);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_startService:

                startService(intentService);
                break;
            case R.id.bt_stopService:
                break;


        }
    }
}
