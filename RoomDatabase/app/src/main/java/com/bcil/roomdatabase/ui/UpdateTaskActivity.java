package com.bcil.roomdatabase.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bcil.roomdatabase.R;
import com.bcil.roomdatabase.database_holder.DatabaseClient;
import com.bcil.roomdatabase.entity.Task;

public class UpdateTaskActivity extends AppCompatActivity {
    private EditText editTextTask, editTextDesc, editTextFinishBy;
    private CheckBox checkBoxFinished;
    private Button button_update;
    private Button button_delete;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);
        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinishBy = findViewById(R.id.editTextFinishBy);

        checkBoxFinished = findViewById(R.id.checkBoxFinished);


        final Task task = (Task) getIntent().getSerializableExtra("task");
        loadTask(task);
        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTask(task);
            }
        });
       findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AlertDialog.Builder alertBuilder = new AlertDialog.Builder(UpdateTaskActivity.this);
               alertBuilder.setTitle("Are you Sure?");
               alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       deleteTask(task);
                       dialog.dismiss();
                   }
               }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                      dialog.dismiss();
                   }
               });
               AlertDialog d = alertBuilder.create();
               d.show();
           }
       });
    }

    private void deleteTask(final Task task) {
        @SuppressLint("StaticFieldLeak")
        class DeleteTask extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .delete(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(UpdateTaskActivity.this,MainActivity.class));
            }
        }
        DeleteTask deleteTask = new DeleteTask();
        deleteTask.execute();
    }

    private void updateTask(final Task task) {
        final String sTask = editTextTask.getText().toString().trim();
        final String sDesc = editTextDesc.getText().toString().trim();
        final String sFinishBy = editTextFinishBy.getText().toString().trim();

        if (sTask.isEmpty()){
            editTextTask.setError("Task Required");
            editTextTask.requestFocus();
            return;
        }
        if (sDesc.isEmpty()){
            editTextDesc.setError("Desc Required");
            editTextDesc.requestFocus();
            return;
        }
        if (sFinishBy.isEmpty()){
            editTextFinishBy.setError("Finished by Required");
            editTextFinishBy.requestFocus();
            return;
        }
        @SuppressLint("StaticFieldLeak")
        class UpdateTask extends AsyncTask<Void,Void,Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                task.setTask(sTask);
                task.setDesc(sDesc);
                task.setFinishBy(sFinishBy);
                task.setFinished(checkBoxFinished.isChecked());

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .update(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(UpdateTaskActivity.this,MainActivity.class));
            }
        }
        UpdateTask updateTask = new UpdateTask();
        updateTask.execute();
    }

    private void loadTask(Task task) {
        editTextTask.setText(task.getTask());
        editTextDesc.setText(task.getDesc());
        editTextFinishBy.setText(task.getFinishBy());
        checkBoxFinished.setChecked(task.isFinished());
    }
}
