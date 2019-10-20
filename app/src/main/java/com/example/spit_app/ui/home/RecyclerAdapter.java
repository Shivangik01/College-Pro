package com.example.spit_app.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spit_app.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<String> list;
    public RecyclerAdapter(List<String> list){
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView= (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.committe_layout,parent,false);
        ViewHolder mViewHolder = new ViewHolder(textView);
        return mViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.committeename.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView committeename;
        public ViewHolder(@NonNull TextView itemView) {
            super(itemView);
            committeename=itemView;
        }
    }
}
