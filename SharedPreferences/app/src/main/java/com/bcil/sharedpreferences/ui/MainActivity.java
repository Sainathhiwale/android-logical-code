package com.bcil.sharedpreferences.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bcil.sharedpreferences.R;
import com.bcil.sharedpreferences.database.DatabaseHelper;
import com.bcil.sharedpreferences.model.User;
import com.bcil.sharedpreferences.utils.AppPreferences;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPhone;
    private RadioGroup radioGroupUserType;
    private AppCompatButton appCompactButtonSaveUser;

    private AppPreferences appPreferences;
    private User user;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing the views
        initViews();

        // initializing the listeners
        initListeners();

        // initializing the objects
        initObjects();

    }


    private void initViews() {
        textInputEditTextName = findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPhone = findViewById(R.id.textInputEditTextPhone);
        radioGroupUserType = findViewById(R.id.radioGroupUserType);
        appCompactButtonSaveUser = findViewById(R.id.appCompactButtonSaveUser);

    }

    private void initListeners() {
        appCompactButtonSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserData();
            }
        });
    }


    private void initObjects() {
        appPreferences = new AppPreferences(getApplicationContext());
        databaseHelper = new DatabaseHelper(this);
        user = new User();
        if (appPreferences.getBoolenPrefs(AppPreferences.KEY_SAVE_USER)) {
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
        }


    }

    private void saveUserData() {
        appPreferences.setBoolenPrefs(AppPreferences.KEY_SAVE_USER, true);
        appPreferences.setStringPrefs(AppPreferences.KEY_USER_NAME, textInputEditTextName.getText().toString().trim());
        appPreferences.setStringPrefs(AppPreferences.KEY_USER_EMAIL, textInputEditTextEmail.getText().toString().trim());
        appPreferences.setStringPrefs(AppPreferences.KEY_USER_PHONE, textInputEditTextPhone.getText().toString().trim());

        RadioButton radioButton = findViewById(radioGroupUserType.getCheckedRadioButtonId());
        if (radioButton != null) {
            appPreferences.setStringPrefs(AppPreferences.KEY_USER_TYPE, radioButton.getText().toString());
        }
        // navigating to profile activity
        postDataToSqlite();
        Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    private void postDataToSqlite() {
        user.setName(textInputEditTextName.getText().toString().trim());
        user.setEmail(textInputEditTextEmail.getText().toString().trim());
        user.setPhone(textInputEditTextPhone.getText().toString().trim());

        databaseHelper.addUser(user);
        Toast.makeText(getApplicationContext(),"Data inserted successfully",Toast.LENGTH_LONG).show();
    }
}
