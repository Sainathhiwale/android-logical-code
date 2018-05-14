package com.dorvis.volley_networkcache;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {
    private Button bt_request;
    private TextView textView_display;
    private LinearLayout linearLayout;
    private Context mContext;
    private Activity mActivity;
    private String BASE_URL = "https://android--examples.blogspot.com";
    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the application context
        mContext = getApplicationContext();
        mActivity = MainActivity.this;


        // Get the widget reference from XML layout
        linearLayout = (LinearLayout)findViewById(R.id.linear_layout);
        bt_request = (Button)findViewById(R.id.bt_request);
        textView_display = (TextView)findViewById(R.id.tv);
        textView_display.setMovementMethod(new ScrollingMovementMethod());
        bt_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Empty the TextView
                textView_display.setText(" ");

                // Instantiate the cache
                Cache cache = new DiskBasedCache(getCacheDir(),1024*1024*5);

                // Setup the network to use HttpURLConnection as the HTTP client
                Network network = new BasicNetwork(new HurlStack());

                // Instantiate the RequestQueue with cache and network
                mRequestQueue = new RequestQueue(cache,network);

                // Start the RequestQueue
                mRequestQueue.start();

                // Initialize a new StringRequest

                StringRequest stringRequest = new StringRequest(Request.Method.GET, BASE_URL, new
                        Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                // Do something with response string
                                 textView_display.setText(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when get error
                        Snackbar.make(linearLayout,"error in request",Snackbar.LENGTH_SHORT).show();

                    }
                });
                // Add StringRequest to the RequestQueue
                mRequestQueue.add(stringRequest);

            }
        });



    }
}
