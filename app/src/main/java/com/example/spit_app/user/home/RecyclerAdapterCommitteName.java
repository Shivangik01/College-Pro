package com.example.spit_app.user.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spit_app.R;

import java.util.List;

public class RecyclerAdapterCommitteName extends RecyclerView.Adapter<RecyclerAdapterCommitteName.ViewHolder> {
    private List<String> list;
    private Context context;

    public RecyclerAdapterCommitteName(List<String> list, Context context){
        this.list=list;
        this.context=context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.committe_layout,parent,false);
        ViewHolder mViewHolder = new ViewHolder(view,context,list);
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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView committeename;
        Context context;
        List<String> list;
        public ViewHolder(@NonNull View itemView,Context context, List<String> list) {
            super(itemView);
            committeename= itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
            this.context=context;
            this.list=list;
        }

        @Override
        public void onClick(View v) {
            Intent intent= new Intent(context, CommitteeAnnouncementsList.class);
            intent.putExtra("Committee", list.get(getAdapterPosition()));
            context.startActivity(intent);

        }
    }
}
