package com.bcil.roomdatabase.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bcil.roomdatabase.R;
import com.bcil.roomdatabase.ui.UpdateTaskActivity;
import com.bcil.roomdatabase.entity.Task;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.MyViewHolder> {
private Context mCtx;
private List<Task> taskList;

    public TasksAdapter(Context mCtx, List<Task> taskList) {
        this.mCtx = mCtx;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_tasks,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int postion) {
      Task t = taskList.get(postion);
      holder.textViewTask.setText(t.getTask());
      holder.textViewDesc.setText(t.getDesc());
      holder.textViewFinishBy.setText(t.getFinishBy());

      if (t.isFinished())
          holder.textViewStatus.setText("Completed");
      else
          holder.textViewStatus.setText("Not Completed");
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewStatus, textViewTask, textViewDesc, textViewFinishBy;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewStatus  = (TextView)itemView.findViewById(R.id.textViewStatus);
            textViewTask = (TextView)itemView.findViewById(R.id.textViewTask);
            textViewDesc  = (TextView)itemView.findViewById(R.id.textViewDesc);
            textViewFinishBy  = (TextView)itemView.findViewById(R.id.textViewFinishBy);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Task task = taskList.get(getAdapterPosition());
            Intent intent = new Intent(mCtx, UpdateTaskActivity.class);
            intent.putExtra("task",task);
            mCtx.startActivity(intent);
        }
    }
}
