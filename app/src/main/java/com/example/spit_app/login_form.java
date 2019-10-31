package com.example.spit_app;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_form extends AppCompatActivity {


    EditText txtEmail,txtPassword;
    FirebaseAuth mfirebaseAuth;
    TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        ScrollView constraintLayout=findViewById(R.id.login_layout);
        AnimationDrawable animationDrawable=(AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        final String admin = "admin@gmail.com";

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            if (user.getEmail().equals(admin)) {
                startActivity(new Intent(login_form.this, AdminPage.class));
                finish();
            }
            else {
                startActivity(new Intent(login_form.this, UserPages.class));
                finish();
            }
        } else {
            txtEmail = (TextInputEditText) findViewById(R.id.e1);
            txtPassword = (TextInputEditText) findViewById(R.id.p1);
            Button btn_login = findViewById(R.id.buttonLogin);
            Button btnsignup = findViewById(R.id.btn_signupform);
            mfirebaseAuth = FirebaseAuth.getInstance();
            forgotPassword=(TextView)findViewById(R.id.tvForgotPassword);

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
                    if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                        Toast.makeText(login_form.this, "Please enter all fields", Toast.LENGTH_LONG).show();
                        return;
                    } else if (TextUtils.isEmpty(email)) {
                        Toast.makeText(login_form.this, "Please enter email", Toast.LENGTH_LONG).show();
                        return;
                    } else if (TextUtils.isEmpty(password)) {
                        Toast.makeText(login_form.this, "Please enter password", Toast.LENGTH_LONG).show();
                        return;
                    } else if (password.length() < 6) {
                        Toast.makeText(login_form.this, " Password Incorrect!", Toast.LENGTH_LONG).show();
                    } else if (!(email.isEmpty() && password.isEmpty())) {
                        mfirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(login_form.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                   // Toast.makeText(login_form.this, "Login successful", Toast.LENGTH_LONG).show();
                                    FirebaseUser firebaseUser = mfirebaseAuth.getInstance().getCurrentUser();
                                    Boolean emailflag = firebaseUser.isEmailVerified();
                                    if (!emailflag) {
                                        Toast.makeText(login_form.this, "Please Verify Your Email Id!", Toast.LENGTH_SHORT).show();
                                        mfirebaseAuth.signOut();
                                    } else {

                                        if (admin.equals(email))
                                        {startActivity(new Intent(login_form.this, AdminPage.class));
                                        finish();}
                                        else {
                                            startActivity(new Intent(login_form.this, UserPages.class));
                                            finish();
                                        }
                                    }
                                } else {
                                    Toast.makeText(login_form.this, "Invalid Password or Email Id", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(login_form.this, "Error Occurred", Toast.LENGTH_LONG).show();
                    }
                }
            });

            forgotPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(login_form.this,ChangePassword.class));
                }
            });
        }

    }
}
