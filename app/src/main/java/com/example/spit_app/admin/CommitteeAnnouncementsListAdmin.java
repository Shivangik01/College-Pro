package com.example.spit_app.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spit_app.R;
import com.example.spit_app.user.home.CommitteeAnnouncementsList;
import com.example.spit_app.user.home.RecyclerAdapterCommitteeAnnouncementList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommitteeAnnouncementsListAdmin extends AppCompatActivity {

    private FirebaseDatabase DatabaseAnnouncement;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<CommitteeAnnouncements> list;
    private RecyclerAdapterCommitteeAnnouncementListAdmin adapter;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_announcements_list);


        DatabaseAnnouncement = FirebaseDatabase.getInstance();
        recyclerView =  findViewById(R.id.recyclerviewcommitteeannounce);
        layoutManager = new LinearLayoutManager(CommitteeAnnouncementsListAdmin.this);
        recyclerView.setLayoutManager(layoutManager);
        list= new ArrayList<CommitteeAnnouncements>();
        adapter=new RecyclerAdapterCommitteeAnnouncementListAdmin(list, CommitteeAnnouncementsListAdmin.this);
        recyclerView.setAdapter(adapter);
        ref= DatabaseAnnouncement.getReference().child("CommitteeAnnouncements");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot announcementSnapshot: dataSnapshot.getChildren()){
                   String committee=announcementSnapshot.getKey();
                   DatabaseReference rf=ref.child(committee);
                   rf.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           for(DataSnapshot announceSnapshot : dataSnapshot.getChildren()){
                               CommitteeAnnouncements announce= announceSnapshot.getValue(CommitteeAnnouncements.class);
                               list.add(announce);
                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
                }


                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
