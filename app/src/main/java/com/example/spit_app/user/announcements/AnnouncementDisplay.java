package com.example.spit_app.user.announcements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.spit_app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AnnouncementDisplay extends AppCompatActivity {
    TextView eventname;
    TextView date;
    TextView Description;
    Button mark;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_display);

        eventname=findViewById(R.id.eventn);
        date=findViewById(R.id.daten);
        Description=findViewById(R.id.desn);
        mark=findViewById(R.id.mark);

        eventname.setText(getIntent().getStringExtra("Eventname"));
        date.setText(getIntent().getStringExtra("Date"));
        Description.setText(getIntent().getStringExtra("Description"));


    }
}
