package com.example.spit_app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.spit_app.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home1, container, false);

        ListView list = (ListView) root.findViewById(R.id.thelist);


        ArrayList<String> names = new ArrayList<>();
        names.add("Mitch");
        names.add("Blake");
        names.add("Shelly");
        names.add("Jess");
        names.add("Steve");
        names.add("Mohammed");

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.fragment_home1, names);
        list.setAdapter(adapter);


        return root;
    }
}