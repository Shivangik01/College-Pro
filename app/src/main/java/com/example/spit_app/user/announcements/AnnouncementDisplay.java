package com.example.spit_app.user.announcements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spit_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AnnouncementDisplay extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    DatabaseReference myRef;
    TextView eventname;
    TextView date;
    TextView Description;
    Button mark;
    FirebaseUser user;
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

        mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth=FirebaseAuth.getInstance();
                user=firebaseAuth.getCurrentUser();
                String userId=user.getUid();
                myRef =FirebaseDatabase.getInstance().getReference("Users").child(userId).child("GeneralAnnouncements");
                myRef.child("Announcementid").setValue(getIntent().getStringExtra("AnnouncementId"));
                toastMessage("Marking...");
            }
        });
    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
