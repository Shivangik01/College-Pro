package com.example.spit_app.user.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spit_app.R;
import com.example.spit_app.admin.CommitteeAnnouncements;

import java.util.List;

public class RecyclerAdapterCommitteeAnnouncementList extends RecyclerView.Adapter<RecyclerAdapterCommitteeAnnouncementList.ViewHolder>{
    private List<CommitteeAnnouncements> list;
    private Context context;
    public RecyclerAdapterCommitteeAnnouncementList(List<CommitteeAnnouncements> list, Context context){
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.committee_announcements_view, parent, false);
        RecyclerAdapterCommitteeAnnouncementList.ViewHolder viewHolder= new RecyclerAdapterCommitteeAnnouncementList.ViewHolder(view,context,list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterCommitteeAnnouncementList.ViewHolder holder, int position) {
        holder.eventname.setText(list.get(position).getEvent());
        holder.date.setText(list.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {

        TextView eventname;
        TextView date;
        Context context;
        List<CommitteeAnnouncements> list;

        public ViewHolder(@NonNull View itemView, Context context, List<CommitteeAnnouncements> list) {
            super(itemView);
            eventname=itemView.findViewById(R.id.events);
            date=itemView.findViewById(R.id.date);
            this.context=context;
            this.list=list;
        }


    }
}


