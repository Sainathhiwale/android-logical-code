package com.bcil.twoline.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bcil.twoline.R;
import com.bcil.twoline.model.Gapscan;

import java.util.List;
//result the data
public class ResultRecyclerAdapter extends RecyclerView.Adapter<ResultRecyclerAdapter.MyViewHolder> {

    private List<Gapscan> gapscanList;

    public ResultRecyclerAdapter(List<Gapscan> gapscanList) {
        this.gapscanList = gapscanList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int postion) {
      holder.tvBarcode.setText(gapscanList.get(postion).getEanno());
      holder.tvlocation.setText(gapscanList.get(postion).getLocation());

    }

    @Override
    public int getItemCount() {
        return gapscanList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
         TextView tvBarcode, tvlocation;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBarcode = (TextView)itemView.findViewById(R.id.tvBarcode);
            tvlocation = (TextView)itemView.findViewById(R.id.tvlocation);
        }
    }
}
