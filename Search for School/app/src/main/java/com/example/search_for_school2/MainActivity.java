package com.example.search_for_school2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

import db.DatabaseHelper;
import models.Address;
import models.School;



public class MainActivity extends AppCompatActivity {
    private ImageButton userButton;
    private ImageButton adminButton;

    private static final int SPLASH_SCREEN_TIMEOUT = 2000; // 2 seconds

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // hide the main layout initially
        ConstraintLayout mainLayout = findViewById(R.id.main_layout);
        mainLayout.setVisibility(View.INVISIBLE);

        // show the splash image for 2 seconds
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // show the main layout after 2 seconds
                mainLayout.setVisibility(View.VISIBLE);
            }
        }, SPLASH_SCREEN_TIMEOUT);
        userButton = findViewById(R.id.User);
        adminButton = findViewById(R.id.Admin);
        DatabaseHelper dbHelper = new DatabaseHelper(this);


//        Address address1 = new Address("Masaken1", "Damascus");
//        Address address2 = new Address("Masaken2", "Damascus");

//        dbHelper.addaddress(address1);
//        dbHelper.addaddress(address2);

//        dbHelper.deleteAll();
//        // Add sample data to the database
//        School school1 = new School("School1 of California, Berkeley", 1);
//        School school2 = new School("School2 of California, Berkeley", 2);
//        School school3 = new School("School3 of California, Berkeley", 3);
//
//        dbHelper.addSchool(school1);
//        dbHelper.addSchool(school2);
//        dbHelper.addSchool(school3);


        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserActivity_();
            }
        });
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminActivity_();
            }
        });
    }

    protected void UserActivity_(){
        Intent intent = new Intent(this,UserActivity.class);
        startActivity(intent);
    }
    protected void AdminActivity_(){
        Intent intent = new Intent(this,AdminActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        dbHelper.close();
    }
}