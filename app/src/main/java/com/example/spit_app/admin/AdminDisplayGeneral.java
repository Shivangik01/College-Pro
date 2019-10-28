package com.example.spit_app.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spit_app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminDisplayGeneral extends AppCompatActivity {

    EditText eventname;
    EditText date;
    EditText Description;
    Button update, delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_display);

        eventname=findViewById(R.id.eventn);
        date=findViewById(R.id.daten);
        Description=findViewById(R.id.desn);
        delete=findViewById(R.id.delete);
        update=findViewById(R.id.update);

        eventname.setText(getIntent().getStringExtra("Eventname"));
        date.setText(getIntent().getStringExtra("Date"));
        Description.setText(getIntent().getStringExtra("Description"));

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getIntent().getStringExtra("AnnouncementId");
                DatabaseReference ref=FirebaseDatabase.getInstance().getReference("GeneralAnnouncements").child(id);
                ref.removeValue();
                Toast.makeText(getApplicationContext(), "Announcement Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AdminDisplayGeneral.this, Recycler_Adapter_Admin.class));
            }
        });
    }
}
