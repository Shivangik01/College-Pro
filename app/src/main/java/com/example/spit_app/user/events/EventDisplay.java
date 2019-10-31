package com.example.spit_app.user.events;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.spit_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventDisplay extends AppCompatActivity {
    TextView eventname;
    TextView date;
    TextView Description;
    Button unmark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_display);

        eventname=findViewById(R.id.event);
        date=findViewById(R.id.datee);
        Description=findViewById(R.id.des);
        unmark=findViewById(R.id.unmark);

        eventname.setText(getIntent().getStringExtra("Eventname"));
        date.setText(getIntent().getStringExtra("Date"));
        Description.setText(getIntent().getStringExtra("Description"));

        unmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id= getIntent().getStringExtra("AnnouncementId");
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                String uid=user.getUid();
                DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Announcements").child(id);
                ref.removeValue();
                Toast.makeText(getApplicationContext(), "Event Unmarked", Toast.LENGTH_SHORT).show();
               finish();
            }
        });

    }

}
