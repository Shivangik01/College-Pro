package com.example.spit_app.user.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spit_app.R;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> list;
    private RecyclerAdapterCommitteName adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home_user, container, false);
        recyclerView=root.findViewById(R.id.recyclerview);
        layoutManager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        String sample[]=getResources().getStringArray(R.array.committees);
        list= Arrays.asList(sample);
        adapter = new RecyclerAdapterCommitteName(list,getContext());
        recyclerView.setAdapter(adapter);

        return root;
    }
}