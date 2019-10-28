package com.example.spit_app.user.announcements;

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

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.ViewHolder> {
    private List<Announcing> list;
    private Context context;
    public Recycler_Adapter(List<Announcing> list,Context context){
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.announcements_view, parent, false);
        ViewHolder viewHolder= new ViewHolder(view,context,list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.eventname.setText(list.get(position).getEvent());
        holder.date.setText(list.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView eventname;
        TextView date;
        Context context;
        List<Announcing> list;

        public ViewHolder(@NonNull View itemView, Context context, List<Announcing> list) {
            super(itemView);
            eventname=itemView.findViewById(R.id.events);
            date=itemView.findViewById(R.id.date);
            itemView.setOnClickListener(this);
            this.context=context;
            this.list=list;
        }

        @Override
        public void onClick(View v) {

            Intent intent= new Intent(context,AnnouncementDisplay.class);
            intent.putExtra("Date", list.get(getAdapterPosition()).getDate());
            intent.putExtra("Description", list.get(getAdapterPosition()).getDt());
            intent.putExtra("Eventname", list.get(getAdapterPosition()).getEvent());
            intent.putExtra("AnnouncementId",list.get(getAdapterPosition()).getAnnounceid());
            context.startActivity(intent);
        }
    }
}
