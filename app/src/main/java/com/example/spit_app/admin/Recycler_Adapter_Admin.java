package com.example.spit_app.admin;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spit_app.R;
import com.example.spit_app.user.announcements.Announcement;

import java.util.List;

public class Recycler_Adapter_Admin extends RecyclerView.Adapter<Recycler_Adapter_Admin.ViewHolder> {
    private List<Announcement> list;
    private Context context;
    public Recycler_Adapter_Admin(List<Announcement> list, Context context){
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_view, parent, false);
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
        List<Announcement> list;

        public ViewHolder(@NonNull View itemView, Context context, List<Announcement> list) {
            super(itemView);
            eventname=itemView.findViewById(R.id.events);
            date=itemView.findViewById(R.id.date);
            itemView.setOnClickListener(this);
            this.context=context;
            this.list=list;
        }

        @Override
        public void onClick(View v) {

            Intent intent= new Intent(context, AdminDisplayGeneral.class);
            intent.putExtra("Date", list.get(getAdapterPosition()).getDate());
            intent.putExtra("Description", list.get(getAdapterPosition()).getDt());
            intent.putExtra("Eventname", list.get(getAdapterPosition()).getEvent());
            intent.putExtra("AnnouncementId",list.get(getAdapterPosition()).getAnnounceid());
            context.startActivity(intent);
        }
    }
}
