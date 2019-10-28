package com.example.spit_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    FirebaseDatabase profile;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;
    TextView name;
    TextView uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=findViewById(R.id.user);
        uid=findViewById(R.id.uidname);
        FirebaseUser users = FirebaseAuth.getInstance().getCurrentUser();
        assert users != null;
        String uid1=users.getUid();
        profile=FirebaseDatabase.getInstance();
        reference=profile.getReference().child("Users").child(uid1);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nam=dataSnapshot.child("Username").getValue(String.class);
                String UID=dataSnapshot.child("UID").getValue(String.class);
                name.setText(nam);
                uid.setText(UID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
