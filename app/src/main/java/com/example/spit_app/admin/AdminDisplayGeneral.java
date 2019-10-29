package com.example.spit_app.admin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spit_app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AdminDisplayGeneral extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    EditText eventname;
    TextView mDisplayDate;
    EditText Description;
    Button update, delete;
    private String Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_display);

        eventname=findViewById(R.id.eventn);
        mDisplayDate=findViewById(R.id.daten);
        Description=findViewById(R.id.desn);
        delete=findViewById(R.id.delete);
        update=findViewById(R.id.update);

        eventname.setText(getIntent().getStringExtra("Eventname"));
        mDisplayDate.setText(getIntent().getStringExtra("Date"));
        Description.setText(getIntent().getStringExtra("Description"));

        mDisplayDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AdminDisplayGeneral.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar cal = Calendar.getInstance();
                int cyear = cal.get(Calendar.YEAR);
                int cmonth = cal.get(Calendar.MONTH);
                int cday = cal.get(Calendar.DAY_OF_MONTH);
                if(year<cyear){
                    Toast.makeText(AdminDisplayGeneral.this, "Enter valid date",Toast.LENGTH_SHORT).show();
                }
                else if(year==cyear && month<cmonth){
                    Toast.makeText(AdminDisplayGeneral.this, "Enter valid date",Toast.LENGTH_SHORT).show();
                }
                else if(year==cyear && month==cmonth && day<cday){
                    Toast.makeText(AdminDisplayGeneral.this, "Enter valid date",Toast.LENGTH_SHORT).show();
                }
                else{
                    month = month + 1;
                    String date = day + "/" + month + "/" + year;
                    mDisplayDate.setText(date);
                    Date = year + "/" + month + "/" + day;
                }
            }
        };

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

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getIntent().getStringExtra("AnnouncementId");
                String event= eventname.getText().toString();
                String data=Description.getText().toString();

                DatabaseReference dR = FirebaseDatabase.getInstance().getReference("GeneralAnnouncements").child(id);
                Announcements announceobj = new Announcements(id,data,event,Date);
                dR.setValue(announceobj);
                Toast.makeText(getApplicationContext(), "Announcement updated",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
