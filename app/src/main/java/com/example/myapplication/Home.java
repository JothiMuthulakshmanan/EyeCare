package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    private Button pickImageBtn;
    private ImageView imageIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize variables
        pickImageBtn = findViewById(R.id.pckbtn);
        imageIV = findViewById(R.id.img);

        // Set a click listener for the button
        pickImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to pick images
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                // Start the activity for result
                startActivityForResult(intent, 1);
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navi1);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Intent intent;


                 if(item.getItemId()==R.id.Home){
                     intent = new Intent(Home.this, Home.class);
                 }
               else if (item.getItemId() == R.id.Blog) {
                    // Start Activity2
                    intent = new Intent(Home.this, blog.class);
                }
                else  {
                    // Start Activity2
                    intent = new Intent(Home.this, BlogCreation.class);
                }

                if (intent != null) {
                    startActivity(intent);
                }
                return true;
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            // Compare the requestCode with the constant
            if (requestCode == 1) {
                // Get the URI of the selected image from the intent data
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    // Update the image view in the layout
                    imageIV.setImageURI(selectedImageUri);
                }
            }
        }
    }
}