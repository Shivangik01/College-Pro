package com.example.spit_app.user.announcements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spit_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.FutureTask;

public class AnnouncementsFragmentUser extends Fragment {

    private FirebaseDatabase DatabaseAnnouncement;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Announcement> list;
    private Recycler_Adapter adapter;
    DatabaseReference ref;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        DatabaseAnnouncement = FirebaseDatabase.getInstance();

        View root = inflater.inflate(R.layout.fragment_announcements_user, container, false);
        recyclerView =  root.findViewById(R.id.recyclerviewannounce);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        list= new ArrayList<Announcement>();
        adapter=new Recycler_Adapter(list,getContext());
        recyclerView.setAdapter(adapter);
        Query query= DatabaseAnnouncement.getReference().child("GeneralAnnouncements").orderByChild("date");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot announcementSnapshot: dataSnapshot.getChildren()){
                    String date=announcementSnapshot.child("date").getKey();
                    String id=announcementSnapshot.child("announceid").getKey();
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
                        ref=DatabaseAnnouncement.getReference().child("GeneralAnnouncements").child(id);
                        ref.removeValue();
                    }
                    else {
                        Announcement announce = announcementSnapshot.getValue(Announcement.class);
                        list.add(announce);
                    }
                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return root;

    }
}