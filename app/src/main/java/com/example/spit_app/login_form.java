package com.example.spit_app;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_form extends AppCompatActivity {

    ActionBar actionBar;
    EditText txtEmail,txtPassword;
    FirebaseAuth mfirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListner;
    FirebaseUser User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("LOGIN FORM");
        txtEmail = (TextInputEditText) findViewById(R.id.e1);
        txtPassword = (TextInputEditText) findViewById(R.id.p1);
        Button btn_login = (Button) findViewById(R.id.buttonLogin);
        Button btnsignup = (Button) findViewById(R.id.btn_signupform);
        mfirebaseAuth = FirebaseAuth.getInstance();
        final String admin="admin@gmail.com";
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009999")));

        btnsignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(login_form.this, signup_form.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(login_form.this, "Please enter email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(login_form.this, "Please enter password", Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(login_form.this, " Password Incorrect!", Toast.LENGTH_LONG).show();
                }
                else if(!(email.isEmpty()&&password.isEmpty())) {
                    mfirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(login_form.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(login_form.this, "Login successful", Toast.LENGTH_LONG).show();
                                if(admin.equals(email))
                                    startActivity(new Intent(login_form.this, AdminPage.class));
                                else
                                    startActivity(new Intent(login_form.this, UserPages.class));
                                finish();
                            } else {
                                Toast.makeText(login_form.this, "Invalid Password or Email Id", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(login_form.this, "Error Occurred", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
