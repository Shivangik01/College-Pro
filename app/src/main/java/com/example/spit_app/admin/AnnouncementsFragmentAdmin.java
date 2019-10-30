package com.example.spit_app.admin;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.spit_app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AnnouncementsFragmentAdmin extends Fragment {

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private EditText eventname;
    private EditText announce;
    private Button buttonAdd;
    private DatabaseReference DatabaseAnnouncement;
    private String Date;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container2, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_announcements_admin, container2, false);

        final TextView mDisplayDate = root.findViewById(R.id.textView4);

            mDisplayDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
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
                    Toast.makeText(getActivity(), "Enter valid date",Toast.LENGTH_SHORT).show();
                }
                else if(year==cyear && month<cmonth){
                    Toast.makeText(getActivity(), "Enter valid date",Toast.LENGTH_SHORT).show();
                }
                else if(year==cyear && month==cmonth && day<cday){
                    Toast.makeText(getActivity(), "Enter valid date",Toast.LENGTH_SHORT).show();
                }
                else{
                    month = month + 1;
                    String date = day + "/" + month + "/" + year;
                    mDisplayDate.setText(date);
                    Date = year + "/" + month + "/" + day;
                }
            }
        };



        eventname =  root.findViewById(R.id.events);
        announce =  root.findViewById(R.id.textinfo);
        buttonAdd = root.findViewById(R.id.buttonadd);

        DatabaseAnnouncement= FirebaseDatabase.getInstance().getReference("GeneralAnnouncements");

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });



        return root;
    }

    private void addData() {
        String event= eventname.getText().toString();
        String data=announce.getText().toString();

        if(TextUtils.isEmpty(event)&&TextUtils.isEmpty(data)&&TextUtils.isEmpty(Date)){
            Toast.makeText(getActivity(), "Enter all the fields",Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(Date)&&TextUtils.isEmpty(event)){
            Toast.makeText(getActivity(), "Enter the Date and Event name",Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(Date)&&TextUtils.isEmpty(data)){
            Toast.makeText(getActivity(), "Enter the Date and Announcement",Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(data)&&TextUtils.isEmpty(event)){
            Toast.makeText(getActivity(), "Enter the Event name and Announcement",Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(Date)){
            Toast.makeText(getActivity(), "Enter the Date",Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(event)){
            Toast.makeText(getActivity(), "Enter the Event name",Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(data)){
            Toast.makeText(getActivity(), "Enter the announcement",Toast.LENGTH_SHORT).show();
        }

        else if(!TextUtils.isEmpty(data)&&!TextUtils.isEmpty(event)&&!TextUtils.isEmpty(Date)){

            String id = DatabaseAnnouncement.push().getKey();

            Announcements announceobj = new Announcements(id,data,event,Date);
            DatabaseAnnouncement.child(id).setValue(announceobj);

            Toast.makeText(getActivity(), "Announcement added",Toast.LENGTH_LONG).show();

        }
    }
}