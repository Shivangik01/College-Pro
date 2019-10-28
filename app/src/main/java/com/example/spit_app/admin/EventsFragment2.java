package com.example.spit_app.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spit_app.R;
import com.example.spit_app.user.announcements.Announcement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventsFragment2 extends Fragment {

    private FirebaseDatabase AdminAnnouncements;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Announcement> list;
    private Recycler_Adapter_Admin adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container2, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_events_admin, container2, false);

        AdminAnnouncements = FirebaseDatabase.getInstance();
        recyclerView = root.findViewById(R.id.recyclerviewannoucementsadmin);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        list= new ArrayList<Announcement>();
        Query query= AdminAnnouncements.getReference().child("GeneralAnnouncements").orderByChild("date");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot announcementSnapshot: dataSnapshot.getChildren()){
                    Announcement announce= announcementSnapshot.getValue(Announcement.class);
                    list.add(announce);
                }

                adapter=new Recycler_Adapter_Admin(list,getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return root;
    }
}