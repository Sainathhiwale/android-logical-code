package com.example.sai.spftest;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void registeruser(View view) {

        EditText et_name = (EditText)findViewById(R.id.et_Name);
        EditText et_password = (EditText)findViewById(R.id.et_Password);
        EditText et_address =(EditText)findViewById(R.id.et_Address);
        EditText et_age = (EditText)findViewById(R.id.et_Age);
        EditText et_mobile = (EditText)findViewById(R.id.et_Mobile);

        SharedPreferences spf = getSharedPreferences("myspf", Context.MODE_PRIVATE);

        //write the data(inserted into data spf)
        SharedPreferences.Editor spe = spf.edit();

        spe.putString("name",et_name.getText().toString());
        spe.putString("password",et_password.getText().toString());
        spe.putString("address",et_address.getText().toString());
        spe.putString("age",et_age.getText().toString());
        spe.putString("mobile",et_mobile.getText().toString());

        spe.commit();
        finish();

    }
}
