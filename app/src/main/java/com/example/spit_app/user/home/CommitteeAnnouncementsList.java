package com.example.spit_app.user.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.spit_app.R;
import com.example.spit_app.admin.CommitteeAnnouncements;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommitteeAnnouncementsList extends AppCompatActivity {

    private FirebaseDatabase DatabaseAnnouncement;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<CommitteeAnnouncements> list;
    private RecyclerAdapterCommitteeAnnouncementList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_announcements_list);


        DatabaseAnnouncement = FirebaseDatabase.getInstance();
        recyclerView =  findViewById(R.id.recyclerviewcommitteeannounce);
        layoutManager = new LinearLayoutManager(CommitteeAnnouncementsList.this);
        recyclerView.setLayoutManager(layoutManager);
        list= new ArrayList<CommitteeAnnouncements>();
        Query query= DatabaseAnnouncement.getReference().child("CommitteeAnnouncements").child(getIntent().getStringExtra("Committee")).orderByChild("date");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot announcementSnapshot: dataSnapshot.getChildren()){
                    CommitteeAnnouncements announce= announcementSnapshot.getValue(CommitteeAnnouncements.class);
                    list.add(announce);
                }

                adapter=new RecyclerAdapterCommitteeAnnouncementList(list,CommitteeAnnouncementsList.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
