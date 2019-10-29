package com.example.spit_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Change_username extends AppCompatActivity {
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_username);
        final EditText txt_new_username=findViewById(R.id.new_username);
        Button btn= findViewById(R.id.button_new_username);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                String userId=user.getUid();
                myRef =FirebaseDatabase.getInstance().getReference("Users").child(userId).child("Username");

                String newUser = txt_new_username.getText().toString();

                myRef.setValue(newUser);
                toastMessage("Username has been changed successfully!");
                finish();
            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
