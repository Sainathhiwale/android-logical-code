package com.dorvis.volley_jsonobjectrequest;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    public Activity mActivity;
    private Button button_request;
    private LinearLayout linearLayout;
    private TextView textView_display;
    final String BASE_URL = "http://pastebin.com/raw/2bW31yqa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the application context
        mContext = getApplicationContext();
        mActivity = MainActivity.this;

        button_request =(Button)findViewById(R.id.bt_reqest);
        textView_display =(TextView)findViewById(R.id.tv_display);
        linearLayout = (LinearLayout)findViewById(R.id.linear_layout);



        button_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Empty the TextView
                textView_display.setText(" ");

                // Initialize a new JsonObjectRequest instance
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BASE_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try {
                            // Get the JSON array
                            JSONArray array = response.getJSONArray("students");

                            // Loop through the array elements
                            for (int i=0;i<array.length();i++){
                                // Get current json object
                                JSONObject student= array.getJSONObject(i);

                                // Get the current student (json object) data
                                String firstName = student.getString("firstname");
                                String lastName = student.getString("lastname");
                                String age = student.getString("age");

                                textView_display.append(firstName+" "+lastName+" "+age);
                                textView_display.append("\n\n");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(linearLayout,"error",Snackbar.LENGTH_SHORT).show();

                    }
                }
                );

                // Add JsonObjectRequest to the RequestQueue
                requestQueue.add(jsonObjectRequest);

            }
        });


    }
}
