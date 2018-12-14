package com.bcil.sqlitedatabase.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bcil.sqlitedatabase.R;
import com.bcil.sqlitedatabase.model.User;

import java.util.List;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {
 private List<User> listUsers;
    public UsersRecyclerAdapter(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_recycler,parent,false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
    holder.textViewName.setText(listUsers.get(position).getName());
    holder.textViewEmail.setText(listUsers.get(position).getEmail());
    holder.textViewPassword.setText(listUsers.get(position).getPassword());

    }

    @Override
    public int getItemCount() {
        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPassword;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = (AppCompatTextView)itemView.findViewById(R.id.textViewName);
            textViewEmail =(AppCompatTextView)itemView.findViewById(R.id.textViewEmail);
            textViewPassword = (AppCompatTextView)itemView.findViewById(R.id.textViewPassword);

        }
    }
}
