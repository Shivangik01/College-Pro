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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.spit_app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class HomeFragment2 extends Fragment {

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    EditText eventname;
    EditText description;
    Spinner committeename;
    Button Add;
    DatabaseReference DatabaseCommittee;
    String Date;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container2, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home_admin, container2, false);

        final TextView mDisplayDate = (TextView) root.findViewById(R.id.codate);

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
                month = month + 1;
                String date = year + "/" + month + "/" + day;
                Date = date;
                mDisplayDate.setText(date);
            }
        };

        eventname = (EditText) root.findViewById(R.id.coevent);
        description = (EditText) root.findViewById(R.id.codes);
        committeename = (Spinner) root.findViewById(R.id.coname);

        Add = (Button) root.findViewById(R.id.button3);

        DatabaseCommittee= FirebaseDatabase.getInstance().getReference("CommitteeAnnouncements");

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });
        return root;
    }

    private void addData() {
        String event= eventname.getText().toString();
        String data=description.getText().toString();
        String name=committeename.getSelectedItem().toString();

        if(!TextUtils.isEmpty(data)){

            String id = DatabaseCommittee.push().getKey();

            CommitteeAnnouncements announceobj = new CommitteeAnnouncements(id,data,event,name,Date);
            DatabaseCommittee.child(id).setValue(announceobj);

            Toast.makeText(getActivity(), "Announcement added",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(getActivity(), "Enter the announcement",Toast.LENGTH_LONG).show();

        }
    }
}