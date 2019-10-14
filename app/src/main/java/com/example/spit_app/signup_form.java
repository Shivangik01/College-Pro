package com.example.spit_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup_form extends AppCompatActivity {

   TextInputEditText  txtEmail,txtPassword,txtConfirmPassword,txtuid;
    Button btn_register;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        getSupportActionBar().setTitle("SIGN UP FORM");


        txtEmail= (TextInputEditText) findViewById(R.id.txt_email);
        txtPassword= (TextInputEditText) findViewById(R.id.txt_password);
        txtConfirmPassword= (TextInputEditText) findViewById(R.id.txt_confirmpassword);
        txtuid =(TextInputEditText) findViewById(R.id.uid);

        btn_register=(Button)findViewById(R.id.buttonregister);
        firebaseAuth=FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=txtEmail.getText().toString().trim();
                String password=txtPassword.getText().toString().trim();
                String confirmpassword=txtConfirmPassword.getText().toString().trim();
                String uid=txtuid.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(signup_form.this, "Please enter your email Id", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(uid)){
                    Toast.makeText(signup_form.this, "Please enter your UID number",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                   Toast.makeText(signup_form.this, "Please enter the password", Toast.LENGTH_SHORT).show();
                   return;
               }

                if(TextUtils.isEmpty(confirmpassword))
                {
                    Toast.makeText(signup_form.this, "Please enter confirm the password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(uid.length()!=10)
                {
                    Toast.makeText( signup_form.this,  " Invalid UID" ,Toast.LENGTH_SHORT).show();
                }

                if(password.length()<6)
                {
                    Toast.makeText(signup_form.this, "Password is too Short ", Toast.LENGTH_SHORT).show();
                }

                if(password.equals(confirmpassword))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(signup_form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(),login_form.class));
                                        Toast.makeText(signup_form.this, "Successful Signup", Toast.LENGTH_SHORT).show();
                                        finish();

                                    } else {

                                        Toast.makeText(signup_form.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                                    }

                                    //.
                                }
                            });
                }


            }
        });

    }
}