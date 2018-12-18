package com.bcil.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   private Button btnTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter=new IntentFilter();
        filter.addAction(Intent.ACTION_HEADSET_PLUG);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(new MyBroadcastReceiver(),filter);
        initView();
        initlisnter();
    }

    private void initlisnter() {
        btnTab.setOnClickListener(this);
    }

    private void initView() {
        btnTab = findViewById(R.id.btntab);
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(new MyBroadcastReceiver());
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btntab:
                openTablayout();
                break;
        }
    }

    private void openTablayout() {
        Intent intent = new Intent(getApplicationContext(),TabLayoutActivity.class);
        startActivity(intent);

    }
}
