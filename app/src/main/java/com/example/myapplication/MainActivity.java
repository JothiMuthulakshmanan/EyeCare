package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent;
                if (item.getItemId() == R.id.Home) {
                    // Start Activity1
                    intent = new Intent(MainActivity.this, Home.class);
                } else if (item.getItemId() == R.id.Blog) {
                    // Start Activity2
                    intent = new Intent(MainActivity.this, blog.class);
                }
                else  {
                    // Start Activity2
                    intent = new Intent(MainActivity.this, BlogCreation.class);
                }

                if (intent != null) {
                    startActivity(intent);
                }
                return true;
            }
        });
    }
}