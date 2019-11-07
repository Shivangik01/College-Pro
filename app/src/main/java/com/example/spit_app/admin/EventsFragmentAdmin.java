package com.example.spit_app.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spit_app.R;
import com.example.spit_app.user.announcements.Announcement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EventsFragmentAdmin extends Fragment {

    private FirebaseDatabase AdminAnnouncements;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Announcement> list;
    List<String> listco;
    private Recycler_Adapter_Admin adapter;
    private DatabaseReference ref;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container2, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_events_admin, container2, false);

        Button committee = root.findViewById(R.id.coannounce);
        AdminAnnouncements = FirebaseDatabase.getInstance();
        recyclerView = root.findViewById(R.id.recyclerviewannoucementsadmin);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        list= new ArrayList<Announcement>();


        Query query= AdminAnnouncements.getReference().child("GeneralAnnouncements").orderByChild("date");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot announcementSnapshot: dataSnapshot.getChildren()){
                    String date=announcementSnapshot.child("date").getValue(String.class);
                    String id=announcementSnapshot.child("announceid").getValue(String.class);
                    Calendar cal = Calendar.getInstance();
                    int cyear = cal.get(Calendar.YEAR);
                    int cmonth = cal.get(Calendar.MONTH);
                    int cday = cal.get(Calendar.DAY_OF_MONTH)-1;
                    if(cday==0)
                    {
                        cmonth=cmonth-1;
                        if(cmonth==1||cmonth==3||cmonth==5||cmonth==7||cmonth==8||cmonth==10||cmonth==12)
                            cday=31;
                        else
                            cday=30;
                    }

                    String cdate= cyear + "/" + cmonth + "/" + cday;
                    if(date.equals(cdate))
                    {
                        ref=AdminAnnouncements.getReference().child("GeneralAnnouncements").child(id);
                        ref.removeValue();
                    }
                    else {
                        Announcement announce = announcementSnapshot.getValue(Announcement.class);
                        list.add(announce);
                    }
                }
                adapter=new Recycler_Adapter_Admin(list,getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        committee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CommitteeAnnouncementsListAdmin.class));
            }
        });


        return root;
    }
}