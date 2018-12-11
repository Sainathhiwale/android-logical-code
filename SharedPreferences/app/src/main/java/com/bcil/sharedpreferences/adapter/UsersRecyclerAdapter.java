package com.bcil.sharedpreferences.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bcil.sharedpreferences.R;
import com.bcil.sharedpreferences.model.User;

import java.util.List;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {
    private List<User> listUsers;

    public UsersRecyclerAdapter(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_recycler,parent,false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int postion) {
       holder.textViewName.setText(listUsers.get(postion).getName());
       holder.textViewEmail.setText(listUsers.get(postion).getEmail());
       holder.textViewPhone.setText(listUsers.get(postion).getPhone());
    }

    @Override
    public int getItemCount() {
        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }

    /**
     * ViewHolder class
     */
    public class UserViewHolder  extends RecyclerView.ViewHolder{
        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPhone;

        public UserViewHolder(View view){
            super(view);
            textViewName =(AppCompatTextView)view.findViewById(R.id.textViewName);
            textViewEmail =(AppCompatTextView)view.findViewById(R.id.textViewEmail);
            textViewPhone =(AppCompatTextView)view.findViewById(R.id.textViewPhone);


        }
    }
}
