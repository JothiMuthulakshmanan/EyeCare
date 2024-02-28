




package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BlogCreation extends AppCompatActivity {

    EditText btv1,btv2,na;
    Button btnbl;
    FirebaseDatabase firebasedatabase;
    DatabaseReference databaseusers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_creation);
        na=findViewById(R.id.na);
        btv1=findViewById(R.id.btv1);
        btv2=findViewById(R.id.btv2);
        btnbl=findViewById(R.id.btnblog);
        firebasedatabase=FirebaseDatabase.getInstance();
       databaseusers=firebasedatabase.getReference();

        btnbl.setOnClickListener(view -> InsertData());
    }
    private void InsertData(){
        String textv1=btv1.getText().toString();
        String textv2=btv2.getText().toString();
        String nam=na.getText().toString();
        user obj=new user(textv1,textv2);
       databaseusers.child("users").child(nam).setValue(obj).addOnCompleteListener(task -> {
           if(task.isSuccessful()){
              Toast.makeText(BlogCreation.this,"blog is added successful",Toast.LENGTH_SHORT).show();
           }
        });
    }
}