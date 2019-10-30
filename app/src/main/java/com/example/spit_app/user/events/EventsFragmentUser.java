package com.example.spit_app.user.events;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventsFragmentUser extends Fragment {

    private FirebaseDatabase DatabaseEvent;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Announcement> list;
    private Recycler_Adapter_events adapter;
    private FirebaseUser user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_events_user, container, false);
        DatabaseEvent=FirebaseDatabase.getInstance();
        recyclerView = root.findViewById(R.id.recyclerviewevents);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        list= new ArrayList<Announcement>();
        user= FirebaseAuth.getInstance().getCurrentUser();
        String uid=user.getUid();


        DatabaseReference reference= DatabaseEvent.getReference().child("Users").child(uid).child("Announcements");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot announcementSnapshot: dataSnapshot.getChildren()){
                    Announcement announce= announcementSnapshot.getValue(Announcement.class);
                    list.add(announce);
                }

                adapter=new Recycler_Adapter_events(list,getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return root;
    }
}