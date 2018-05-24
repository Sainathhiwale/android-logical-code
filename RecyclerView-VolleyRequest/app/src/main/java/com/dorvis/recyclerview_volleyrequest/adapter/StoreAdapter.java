package com.dorvis.recyclerview_volleyrequest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dorvis.recyclerview_volleyrequest.R;
import com.dorvis.recyclerview_volleyrequest.model.StoresListBO;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {
private List<StoresListBO>mList;
private Context mContext;

public StoreAdapter(List<StoresListBO> storesList, Context _context){
    mList= storesList;
    mContext=_context;
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.store_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    StoresListBO singleStore = mList.get(position);
    holder.tvStoreName.setText(singleStore.getStoreName());
    holder.tvDescription.setText(singleStore.getDescription());
    holder.tvAddress.setText(singleStore.getBranchAddress());
    String titleStr = singleStore.getStoreName().substring(0,1);
    if (titleStr!=null)
        holder.title.setText(titleStr);
    else
        holder.title.setText("A");

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvStoreName;
        TextView tvDescription;
        TextView tvAddress;
        TextView title;
        public ViewHolder(View itemView) {
            super(itemView);
            tvStoreName=(TextView)itemView.findViewById(R.id.tvStoreName);
            tvDescription=(TextView)itemView.findViewById(R.id.tvDescription);
            tvAddress=(TextView)itemView.findViewById(R.id.tvAddress);
            title=(TextView)itemView.findViewById(R.id.title);

        }
    }
}
