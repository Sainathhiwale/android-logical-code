package com.bcil.sharedpreferences.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bcil.sharedpreferences.R;
import com.bcil.sharedpreferences.adapter.UsersRecyclerAdapter;
import com.bcil.sharedpreferences.database.DatabaseHelper;
import com.bcil.sharedpreferences.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsersListActivity extends AppCompatActivity {
   private UsersRecyclerAdapter usersRecyclerAdapter;
   private AppCompatActivity  activity = UsersListActivity.this;
   private List<User> listUsers;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewUsers;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        initViews();
        initObjects();
    }

    public void initViews(){
        textViewName = (AppCompatTextView)findViewById(R.id.textViewName);
        recyclerViewUsers = (RecyclerView)findViewById(R.id.recyclerViewUsers);

    }
    private void initObjects() {
        listUsers = new ArrayList<>();
        usersRecyclerAdapter = new UsersRecyclerAdapter(listUsers);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(usersRecyclerAdapter);

        Intent intent = getIntent();
        String email = intent.getStringExtra("KEY_USER_EMAIL");
        textViewName.setText(email);


        //getDataFromSQLite();
        AsyncUser asyncUser = new AsyncUser();
        asyncUser.execute();

    }
    /**
     * This method is to fetch all user records from SQLite
     */
    @SuppressLint("StaticFieldLeak")
    public class AsyncUser extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            listUsers.clear();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (listUsers!=null) {
                        listUsers.addAll(databaseHelper.getAllUser());
                    }
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            usersRecyclerAdapter.notifyDataSetChanged();
        }
    }
    /*@SuppressLint("StaticFieldLeak")
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                listUsers.clear();
                listUsers.addAll(databaseHelper.getAllUser());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                usersRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }*/
}
