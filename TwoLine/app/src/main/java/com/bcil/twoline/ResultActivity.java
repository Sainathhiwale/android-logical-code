package com.bcil.twoline;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bcil.twoline.adapter.ResultRecyclerAdapter;
import com.bcil.twoline.model.Gapscan;
import com.bcil.twoline.sqllite.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
     private RecyclerView rvList;
     private ResultRecyclerAdapter resultRecyclerAdapter;
     private List<Gapscan> gapscanList;
     private DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initView();
        initData();
    }
    private void initView(){
        rvList = (RecyclerView)findViewById(R.id.rvList);

    }
    private void initData() {
      gapscanList =  new ArrayList<>();
      resultRecyclerAdapter = new ResultRecyclerAdapter(gapscanList);
      RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
      rvList.setLayoutManager(mLayoutManager);
      rvList.setItemAnimator(new DefaultItemAnimator());
      rvList.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
      rvList.setHasFixedSize(true);
      rvList.setAdapter(resultRecyclerAdapter);
        databaseHandler = new DatabaseHandler(ResultActivity.this);
      AsyncResult asyncResult = new AsyncResult();
      asyncResult.execute();

    }
    @SuppressLint("StaticFieldLeak")
    public class AsyncResult extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                       gapscanList.clear();
                        gapscanList.addAll(databaseHandler.getAllResult());

                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            resultRecyclerAdapter.notifyDataSetChanged();
        }
    }
}
