package com.example.spit_app.user.announcements;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.spit_app.R;

import java.util.List;

public class AnnouncementList extends ArrayAdapter {

    private Activity context;
    private List<Announcing> Announcementlist;

    public AnnouncementList(Activity context, List <Announcing> Announcementlist){
        super(context, R.layout.adapter_view,Announcementlist);
        this.context=context;
        this.Announcementlist=Announcementlist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflator =context.getLayoutInflater();

        View listviewitem =inflator.inflate(R.layout.adapter_view,null,true);

        TextView Date = (TextView) listviewitem.findViewById(R.id.textView6);
        TextView Eventname =(TextView) listviewitem.findViewById(R.id.events);

        Announcing announce = Announcementlist.get(position);

        Date.setText(announce.getDate());
        Eventname.setText(announce.getEvent());

        return listviewitem;
    }
}
