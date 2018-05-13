package com.dorvis.volley_jsonarrayrequest;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
  private Button bt_request;
  private TextView textView_Display;
  private LinearLayout linearLayout;
  public  Activity mActivity;
  private Context mContext;

  final String BASE_URL ="http://pastebin.com/raw/Em972E5s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Get the application context
        mContext = getApplicationContext();
        mActivity = MainActivity.this;
        // get ui in activity file
        linearLayout = (LinearLayout)findViewById(R.id.linear_layout);
        bt_request = (Button)findViewById(R.id.bt_request);
        textView_Display = (TextView)findViewById(R.id.textview_display);

        // Set a click listener for button widget
        bt_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //empty the TextView
                textView_Display.setText(" ");

                // Initialize a new RequestQueue instance

                RequestQueue requestQueue = Volley.newRequestQueue(mContext);

                // Initialize a new  // Add JsonArrayRequest to the RequestQueue instance
                JsonArrayRequest  jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BASE_URL, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //textView_Display.setText(response.toString());

                        // Process the JSON

                        // Loop through the array elements
                        for (int i=0;i<response.length();i++) {
                            try {


                                // Get current json object
                                JSONObject jsonObject = response.getJSONObject(i);
                                // Get the current student (json object) data
                                String firstName = jsonObject.getString("firstname");
                                String lastName = jsonObject.getString("lastname");
                                String age = jsonObject.getString("age");
                                // Display the formatted json data in text view
                                textView_Display.append(firstName+"  "+lastName+"  "+age);
                                textView_Display.append("\n\n");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred
                        Snackbar.make(linearLayout,"error in request",Snackbar.LENGTH_SHORT).show();

                    }
                });
                // Add JsonArrayRequest to the RequestQueue
                requestQueue.add(jsonArrayRequest);
            }

        });
    }
}
