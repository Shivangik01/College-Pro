package com.example.spit_app.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.spit_app.R;

public class EventsFragment2 extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container2, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_events2, container2, false);

        return root;
    }
}