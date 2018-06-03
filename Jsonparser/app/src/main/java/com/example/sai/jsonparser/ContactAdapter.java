package com.example.sai.jsonparser;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
   private Context context;
    private Model [] data;


    public ContactAdapter(Context context,Model[] data){
       this.context=context;
       this.data=data;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Model model = data[position];
       holder.tv_id.setText(model.getId());
       holder.tv_name.setText(model.getName());
       holder.tv_gmail.setText(model.getEmail());
       holder.tv_address.setText(model.getAddress());
       holder.tv_gender.setText(model.getGender());
       holder.tv_mobile.setText(model.getMobile());
       holder.tv_home.setText(model.getHome());
       holder.tv_office.setText(model.getOffice());
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
          TextView tv_id,tv_name,tv_gmail,tv_address,tv_gender,tv_mobile,tv_home,tv_office;
        public ViewHolder(View itemView) {
            super(itemView);

            tv_id = (TextView)itemView.findViewById(R.id.tv_id);
            tv_name = (TextView)itemView.findViewById(R.id.tv_name);
            tv_gmail = (TextView)itemView.findViewById(R.id.tv_email);
            tv_address  =(TextView)itemView.findViewById(R.id.tv_tv_address);
            tv_gender = (TextView)itemView.findViewById(R.id.tv_tv_gender);
            tv_mobile = (TextView)itemView.findViewById(R.id.tv_tv_mobile);
            tv_home = (TextView)itemView.findViewById(R.id.tv_tv_home);
            tv_office = (TextView)itemView.findViewById(R.id.tv_tv_office);
        }
    }
}
