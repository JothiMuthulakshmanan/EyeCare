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

public class MainActivityLogin2 extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPassword;
    Button btn_login;
    TextView tv1;
    ProgressBar prgbar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login2);

        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.pswrd);
        btn_login=findViewById(R.id.btn_login);
        prgbar=findViewById(R.id.prgbar);
        tv1=findViewById(R.id.tv1);

        tv1.setOnClickListener(view -> {
            Intent intent =new Intent(MainActivityLogin2.this, Main2ActivityRegistration.class);
            startActivity(intent);

        });
        btn_login.setOnClickListener(view -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();


            boolean iscrct=validation(email,password);
            if(!iscrct){
                return;
            }
            prgbar.setVisibility(View.VISIBLE);
            btn_login.setVisibility(View.GONE);
            mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(MainActivityLogin2.this, task -> {
                        btn_login.setVisibility(View.VISIBLE);
                        prgbar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            if(Objects.requireNonNull(mAuth.getCurrentUser()).isEmailVerified()){
                                startActivity(new Intent(MainActivityLogin2.this,MainActivity.class));
                            }
                            else{
                            Toast.makeText(MainActivityLogin2.this, "Email is not verified please verify",
                                    Toast.LENGTH_SHORT).show();}


                        } else {

                            Toast.makeText(MainActivityLogin2.this, "Sign-in failed: " + Objects.requireNonNull(task.getException()).getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

        });
    }

    boolean validation(String email, String password){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("email is invalid");
            return false;
        }
        if (password.length()<6) {
            editTextPassword.setError("passwoed length less than 7");
            return false;
        }

        return true;
    }
}