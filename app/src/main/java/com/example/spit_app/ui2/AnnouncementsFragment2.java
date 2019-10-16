package com.example.spit_app.ui2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.spit_app.R;

public class AnnouncementsFragment2 extends Fragment {

    private AnnouncementsViewModel2 announcementsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container2, Bundle savedInstanceState) {
        announcementsViewModel =
                ViewModelProviders.of(this).get(AnnouncementsViewModel2.class);
        View root = inflater.inflate(R.layout.fragment_announcements2, container2, false);
        final TextView textView = root.findViewById(R.id.text_announcements2);
        announcementsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}