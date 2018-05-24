package com.dorvis.recyclerview_volleyrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dorvis.recyclerview_volleyrequest.adapter.StoreAdapter;
import com.dorvis.recyclerview_volleyrequest.model.StoresListBO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.dorvis.recyclerview_volleyrequest.network.UrlUtility.GET_STORES_LIST;

public class MainActivity extends AppCompatActivity {
     RecyclerView recyclerView;
     StoreAdapter mAdapter;
     List<StoresListBO>storesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        loadData();

        storesList = new ArrayList<>();
        mAdapter = new StoreAdapter(storesList,MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);


    }

    private void loadData() {
         StringRequest request = new StringRequest(Request.Method.GET, GET_STORES_LIST, new Response.Listener<String>() {
             @Override
             public void onResponse(String response) {

                 try {
                     JSONObject jsonObject = new JSONObject(response);
                     jsonObject.getString("status_type");
                     JSONArray array = jsonObject.getJSONArray("result");
                     for (int i=0;i<array.length();i++){
                         JSONObject singleJsonObj = array.getJSONObject(i);
                        StoresListBO singleObj= new StoresListBO();
                        singleObj.setStoreName(singleJsonObj.getString("store_name"));
                        singleObj.setBranchName(singleJsonObj.getString("branch_name"));
                        singleObj.setBranchAddress(singleJsonObj.getString("address"));
                        singleObj.setPointsPercent(singleJsonObj.getString("points_percentage"));
                        singleObj.setPointsValue(singleJsonObj.getString("points_value"));
                        singleObj.setDescription(singleJsonObj.getString("description"));
                        storesList.add(singleObj);
                        mAdapter.notifyDataSetChanged();

                     }

                 } catch (JSONException e) {
                     e.printStackTrace();
                 }

             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Toast.makeText(getApplicationContext(), "Something went wrong, please try again or check internet connection", Toast.LENGTH_LONG).show();

             }
         });
         RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
         request.setRetryPolicy(new DefaultRetryPolicy(300000,
                 DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                 DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
         requestQueue.add(request);

    }
}