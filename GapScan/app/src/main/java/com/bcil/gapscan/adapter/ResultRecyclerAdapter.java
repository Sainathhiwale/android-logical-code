package com.bcil.gapscan.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bcil.gapscan.R;
import com.bcil.gapscan.model.Gapscan;

import java.util.List;

public class ResultRecyclerAdapter  extends RecyclerView.Adapter<ResultRecyclerAdapter.MyViewHolder>{
    private List<Gapscan> gapscanList;

    public ResultRecyclerAdapter(List<Gapscan> gapscanList) {
        this.gapscanList = gapscanList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.tvBarcode.setText(gapscanList.get(position).getEanno());
    }

    @Override
    public int getItemCount() {
        return gapscanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvBarcode,tvId;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvBarcode = (TextView)itemView.findViewById(R.id.tvBarcode);
            tvId = (TextView)itemView.findViewById(R.id.tvId);
        }
    }
}
