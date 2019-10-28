package com.example.spit_app;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomCalendarView extends LinearLayout {

    ImageButton next,previous;
    TextView currentdate;
    GridView gridview;
    private static final int MAX_CALENDAR_DAYS=42;
    Calendar calendar =Calendar.getInstance(Locale.ENGLISH);
    Context context;

    List<Date> dates= new ArrayList<Date>();
    List<Events> events=new ArrayList<Events>();

    SimpleDateFormat dateformat = new SimpleDateFormat("MMM yyyy",Locale.ENGLISH );
    SimpleDateFormat monthformat = new SimpleDateFormat("MMM",Locale.ENGLISH );
    SimpleDateFormat yearformat = new SimpleDateFormat("yyyy",Locale.ENGLISH );



    public CustomCalendarView(Context context) {
        super(context);
    }

    public CustomCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }
}
