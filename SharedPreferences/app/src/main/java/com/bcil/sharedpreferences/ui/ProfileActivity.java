package com.bcil.sharedpreferences.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bcil.sharedpreferences.R;
import com.bcil.sharedpreferences.utils.AppPreferences;

public class ProfileActivity extends AppCompatActivity {
    private AppCompatTextView appCompactTextViewUserType;
    private AppCompatTextView appCompactTextViewUserName;
    private AppCompatTextView appCompactTextViewUserPhoneNo;
    private AppCompatTextView appCompactTextViewUserEmail;


    private AppCompatButton appCompactButtonClearUser;
    private AppCompatButton appCompactButtonShowUser;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // initializing the views
        initViews();

        // initializing the listeners
        initListeners();

        // initializing the objects
        initObjects();

        // set preferences value to views
        showPrefsToViews();
    }
    private void initViews(){
        appCompactTextViewUserType = findViewById(R.id.appCompactTextViewUserType);
        appCompactTextViewUserName = findViewById(R.id.appCompactTextViewUserName);
        appCompactTextViewUserPhoneNo = findViewById(R.id.appCompactTextViewUserPhoneNo);
        appCompactTextViewUserEmail = findViewById(R.id.appCompactTextViewUserEmail);

        appCompactButtonClearUser = findViewById(R.id.appCompactButtonClearUser);
        appCompactButtonShowUser = findViewById(R.id.appCompactButtonShowUser);
        String email = appCompactTextViewUserEmail.getText().toString().trim();
        Intent dataIntent = new Intent(getApplicationContext(),UsersListActivity.class);
        dataIntent.putExtra("KEY_USER_EMAIL", email);
    }
    private void initListeners(){
        appCompactButtonClearUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appPreferences!=null){
                    appPreferences.clearPrefs();
                }
                // navigating to the profile making activity
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        appCompactButtonShowUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentList = new Intent(getApplicationContext(),UsersListActivity.class);
                startActivity(intentList);
            }
        });
    }
    /**
     * method to initializing the objects
     */

    private void initObjects(){
      appPreferences = new AppPreferences(getApplicationContext());
    }

    private void showPrefsToViews() {
        appCompactTextViewUserType.setText(appPreferences.getStringPrefs(AppPreferences.KEY_USER_TYPE));
        appCompactTextViewUserName.setText(appPreferences.getStringPrefs(AppPreferences.KEY_USER_NAME));
        appCompactTextViewUserEmail.setText(appPreferences.getStringPrefs(AppPreferences.KEY_USER_EMAIL));
        appCompactTextViewUserPhoneNo.setText(appPreferences.getStringPrefs(AppPreferences.KEY_USER_PHONE));
    }
}
