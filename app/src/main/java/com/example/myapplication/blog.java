package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class blog extends AppCompatActivity {

    RecyclerView rv;
    DatabaseReference database;
    Adapter adapter;
    ImageView addbtn;
    ArrayList<user> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        rv=findViewById(R.id.rv);
        database= FirebaseDatabase.getInstance().getReference("users");
        list =new ArrayList<>();


        rv.setLayoutManager(
                new LinearLayoutManager(this));
        FirebaseRecyclerOptions<user> options
                = new FirebaseRecyclerOptions.Builder<user>()
                .setQuery(database, user.class)
                .build();
        adapter = new Adapter(this,options);

        rv.setAdapter(adapter);

    }
    @Override
    protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

}