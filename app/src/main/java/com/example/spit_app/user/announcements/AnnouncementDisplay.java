package com.example.spit_app.user.announcements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.spit_app.R;

public class AnnouncementDisplay extends AppCompatActivity {
    TextView eventname;
    TextView date;
    TextView Description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_display);

        eventname=findViewById(R.id.eventn);
        date=findViewById(R.id.daten);
        Description=findViewById(R.id.desn);

        eventname.setText(getIntent().getStringExtra("Eventname"));
        date.setText(getIntent().getStringExtra("Date"));
        Description.setText(getIntent().getStringExtra("Description"));



    }
}
