package com.example.search_for_school2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import models.School;

public class AdminActivity extends AppCompatActivity {
    private ImageButton addressButton;
    private ImageButton schoolButton;
    private ImageButton studentButton;
    private ImageButton professorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_view);

        addressButton = findViewById(R.id.Address);
        schoolButton = findViewById(R.id.School);
        studentButton = findViewById(R.id.Student);
        professorButton = findViewById(R.id.Professor);


        addressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressActivity_();
            }
        });
        schoolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SchoolActivity_();
            }
        });
        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentActivity_();
            }
        });
        professorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfessorActivity_();
            }
        });
    }

    protected void AddressActivity_(){
        Intent intent = new Intent(this,AddressActivity.class);
        startActivity(intent);
    }
    protected void SchoolActivity_(){
        Intent intent = new Intent(this, SchoolActivity.class);
        startActivity(intent);
    }
    protected void StudentActivity_(){
        Intent intent = new Intent(this,StudentActivity.class);
        startActivity(intent);
    }
    protected void ProfessorActivity_(){
        Intent intent = new Intent(this,ProfessorActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        dbHelper.close();
    }

}
