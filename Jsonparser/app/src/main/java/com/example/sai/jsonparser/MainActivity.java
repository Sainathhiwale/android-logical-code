package com.example.sai.jsonparser;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL ="http://www.mocky.io/v2/5b1461cd310000970078bf60";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StringRequest request = new StringRequest(BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Code:",response);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Model [] models= gson.fromJson(response,Model[].class);

                recyclerView.setAdapter(new ContactAdapter(MainActivity.this,models));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"error in request",Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);

    }
}

/*

json data
[{
        "id": "1001",
        "name": "Hardik Lakhani",
        "email": "hardik@gmail.com",
        "address": "Ahmedabad India",
        "gender": "male",
        "mobile": "+91 1111111111",
          "home": "+91 1111111111",
          "office": "11 111111"
    },
    {
        "id": "1002",
        "name": "Jayveer Zala",
        "email": "jayveer@gmail.com",
        "address": "Mumbai India",
        "gender": "male",
         "mobile": "+91 2222222222",
          "home": "+91 2222222222",
          "office": "22 222222"
    },
    {
        "id": "1003",
        "name": "Dhaval Lakhani",
        "email": "dhaval@gmail.com",
        "address": "Rajkot India",
        "gender": "male",
         "mobile": "+91 4444444444",
          "home": "+91 4444444444",
          "office": "44 444444"
    },
    {
        "id": "1001",
        "name": "Hardik Lakhani",
        "email": "hardik@gmail.com",
        "address": "Ahmedabad India",
        "gender": "male",
        "mobile": "+91 1111111111",
          "home": "+91 1111111111",
          "office": "11 111111"
    },
    {
        "id": "1002",
        "name": "Jayveer Zala",
        "email": "jayveer@gmail.com",
        "address": "Mumbai India",
        "gender": "male",
         "mobile": "+91 2222222222",
          "home": "+91 2222222222",
          "office": "22 222222"
    },
    {
        "id": "1003",
        "name": "Dhaval Lakhani",
        "email": "dhaval@gmail.com",
        "address": "Rajkot India",
        "gender": "male",
         "mobile": "+91 4444444444",
          "home": "+91 4444444444",
          "office": "44 444444"
    }
    ]
 */
