package com.dorvis.chatbot;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment implements View.OnClickListener {
    private static View view;
    EditText etName, etEmail, etMobile;
    Button bt_update;
    Spinner spinner_Age;
    RadioButton rb_Male;
    RadioButton rb_Female;
    JSONObject response, profile_pic_data, profile_pic_url;

    public UserProfileFragment() {
        // Required empty public constructor



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_user_profile, container, false);
        Intent intent = new Intent();
        intent.getData();
        String jsondata = intent.getStringExtra("userProfile");
        Log.w("Jsondata", jsondata);
        etName = (EditText)view.findViewById(R.id.etUserName);
        etEmail = (EditText)view.findViewById(R.id.etUserEmail);
        etMobile = (EditText)view.findViewById(R.id.etUserMobile);
        bt_update = (Button)view.findViewById(R.id.bt_Update);
        rb_Male = (RadioButton)view.findViewById(R.id.rb_male);
        rb_Female  = (RadioButton)view.findViewById(R.id.rb_female);
        spinner_Age = (Spinner)view.findViewById(R.id.spinner_Age);
        try {
            response  = new JSONObject(jsondata);
            etName.setText(response.get("name").toString());
            etEmail.setText(response.get("email").toString());
            etMobile.setText(response.get("mobile").toString());


        }catch (Exception e){
            e.printStackTrace();

        }

        setListeners();
        return view;

    }

    private void setListeners() {
     bt_update.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

    }
}
