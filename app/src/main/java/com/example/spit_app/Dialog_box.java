package com.example.spit_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dialog_box extends AppCompatDialogFragment {
    DatabaseReference myRef;
    private EditText new_username;
    private DialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        final View view=inflater.inflate(R.layout.dialog_layout,null);

        builder.setView(view)
                .setTitle("Username")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new_username=view.findViewById(R.id.new_username);
                        String new_user=new_username.getText().toString();
                        listener.applyTexts(new_user);
                        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                        String userId=user.getUid();
                        myRef = FirebaseDatabase.getInstance().getReference("Users").child(userId).child("Username");

                        String newUser = new_username.getText().toString();

                        myRef.setValue(newUser);
                    }
                });


        return builder.create();


    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener=(DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"Implement Dialog Listener");
        }
    }

    public interface DialogListener{
        void applyTexts(String username);
    }


}
