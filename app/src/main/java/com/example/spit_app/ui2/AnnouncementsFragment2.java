package com.example.spit_app.ui2;

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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.spit_app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AnnouncementsFragment2 extends Fragment {

    private AnnouncementsViewModel2 announcementsViewModel;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    EditText eventname;
    EditText announce;
    Button buttonAdd;
    DatabaseReference DatabaseAnnouncement;
    String Date;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container2, Bundle savedInstanceState) {
        announcementsViewModel =
                ViewModelProviders.of(this).get(AnnouncementsViewModel2.class);
        View root = inflater.inflate(R.layout.fragment_announcements2, container2, false);
        final TextView textView = root.findViewById(R.id.text_announcements2);
        final TextView mDisplayDate = (TextView) root.findViewById(R.id.textView4);

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
                int flag1;
                Calendar cal = Calendar.getInstance();
                if (year < cal.get(Calendar.YEAR) || month + 1 < cal.get(Calendar.MONTH) || day <= cal.get(Calendar.DAY_OF_MONTH)) {
                    Toast.makeText(getActivity(), "Enter a valid date", Toast.LENGTH_LONG).show();
                    flag1=0;
                }
                else if(year > cal.get(Calendar.YEAR) || month + 1 > cal.get(Calendar.MONTH) || day > cal.get(Calendar.DAY_OF_MONTH))
                {
                    flag1=1;
                }
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                Date = date;
                mDisplayDate.setText(date);
            }
        };



        eventname = (EditText) root.findViewById(R.id.events);
        announce = (EditText) root.findViewById(R.id.textinfo);
        buttonAdd = (Button) root.findViewById(R.id.buttonadd);

        DatabaseAnnouncement= FirebaseDatabase.getInstance().getReference();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });

        announcementsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

    private void addData() {
        String event= eventname.getText().toString();
        String data=announce.getText().toString();

        if(!TextUtils.isEmpty(data)){

            String id = DatabaseAnnouncement.push().getKey();

            Announcing announceobj = new Announcing(id,data,event,Date);
            DatabaseAnnouncement.child(id).setValue(announceobj);

            Toast.makeText(getActivity(), "Announcement added",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(getActivity(), "Enter the announcement",Toast.LENGTH_LONG).show();

        }
    }
}