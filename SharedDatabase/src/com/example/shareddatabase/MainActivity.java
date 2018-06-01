package com.example.shareddatabase;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	 SharedPreferences spf;
	 SharedPreferences.Editor spe;
	 EditText et1,et2,et3,et4,et5,et6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spf=getSharedPreferences("myprefs",Context.MODE_PRIVATE );
        spe=spf.edit();
        
    }


    public void login(View v){
    	 et1=(EditText)findViewById(R.id.editText1);
    	 et2=(EditText)findViewById(R.id.editText2);
    	 spf.getString("name", "no vlaue");
    	 spf.getString("password", "no value");
    	 Toast.makeText(this, "login success", Toast.LENGTH_LONG).show();
    	 
    	
    }
 public void register(View v){
	 et3=(EditText)findViewById(R.id.editText3);
	 et4=(EditText)findViewById(R.id.editText4);
	 et5=(EditText)findViewById(R.id.editText5);
	 et6=(EditText)findViewById(R.id.editText6);
	 spe.putString("name",et3.getText().toString() );
	 spe.putString("password", et4.getText().toString());
	 spe.putString("mobileno", et5.getText().toString());
	 spe.putString("email", et6.getText().toString());
    	spe.commit();
    	Toast.makeText(getApplicationContext(), "register success", Toast.LENGTH_LONG).show();
    }
    
}
