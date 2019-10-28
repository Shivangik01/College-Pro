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
import java.util.List;

public class AnnouncementsFragment extends Fragment {

    FirebaseDatabase DatabaseAnnouncement;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Announcing> list;
    private Recycler_Adapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        DatabaseAnnouncement = FirebaseDatabase.getInstance();

        View root = inflater.inflate(R.layout.fragment_announcements_user, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerviewannounce);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        list= new ArrayList<Announcing>();

        Query query=DatabaseAnnouncement.getReference().child("GeneralAnnouncements").orderByChild("date");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot announcementSnapshot: dataSnapshot.getChildren()){
                    Announcing announce= announcementSnapshot.getValue(Announcing.class);
                    list.add(announce);
                }

                adapter=new Recycler_Adapter(list,getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return root;


    }
}