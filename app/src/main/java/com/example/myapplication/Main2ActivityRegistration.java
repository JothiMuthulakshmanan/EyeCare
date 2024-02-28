package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class Main2ActivityRegistration extends AppCompatActivity {


    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextcpassword;
    Button btn;
    TextView tv1;
    ProgressBar prgbar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.pswrd);
        editTextcpassword=findViewById(R.id.cpswrd);
        btn=findViewById(R.id.btn_register);
        tv1=findViewById(R.id.tv1);
        prgbar=findViewById(R.id.prgbar);
        tv1.setOnClickListener(view -> {
            Intent intent =new Intent(Main2ActivityRegistration.this,MainActivityLogin2.class);
            startActivity(intent);

        });
btn.setOnClickListener(view -> {
    String email = editTextEmail.getText().toString();
    String password = editTextPassword.getText().toString();
    String cpassword=editTextcpassword.getText().toString();

    boolean iscrct=validation(email,password,cpassword);
    if(!iscrct){
        return;
    }
    prgbar.setVisibility(View.VISIBLE);
    btn.setVisibility(View.GONE);
    mAuth = FirebaseAuth.getInstance();
    mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(Main2ActivityRegistration.this, task -> {
                btn.setVisibility(View.VISIBLE);
                prgbar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(Main2ActivityRegistration.this, "Authentication succeed.",
                            Toast.LENGTH_SHORT).show();
                    Objects.requireNonNull(mAuth.getCurrentUser()).sendEmailVerification();
                    mAuth.signOut();
                    finish();

                } else {
                    Toast.makeText(Main2ActivityRegistration.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            });

});



    }

    boolean validation(String email, String password, String cpassword){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("email is invalid");
            return false;
        }
        if (password.length()<6) {
            editTextPassword.setError("passwoed length less than 7");
            return false;
        }
        if(!password.equals(cpassword)){
            editTextcpassword.setError("Mismatched password");
            return false;
        }
        return true;
    }
}