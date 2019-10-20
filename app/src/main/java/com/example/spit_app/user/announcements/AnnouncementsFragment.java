package com.example.spit_app.user.announcements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.spit_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementsFragment extends Fragment {

    DatabaseReference DatabaseAnnouncement;

    ListView listView;
    List<Announcing> announcelist;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        announcelist = new ArrayList<>();

        DatabaseAnnouncement= FirebaseDatabase.getInstance().getReference();


        View root = inflater.inflate(R.layout.fragment_announcements1, container, false);

        listView=(ListView) root.findViewById(R.id.listview);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        DatabaseAnnouncement.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                announcelist.clear();
                for(DataSnapshot announcementSnapshot: dataSnapshot.getChildren()){
                    Announcing announce =announcementSnapshot.getValue(Announcing.class);
                    announcelist.add(announce);
                }

                AnnouncementList adapter = new AnnouncementList(getActivity(), announcelist);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}