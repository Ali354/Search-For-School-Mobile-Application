package com.example.search_for_school2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import db.DatabaseHelper;
import models.School;

public class SchoolActivity extends AppCompatActivity {
    private ImageButton addButton;
    private ImageButton viewButton;
    private ImageButton editButton;
    private ImageButton deleteButton;
    public static List<School> schoolList;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        addButton =  findViewById(R.id.button_school_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SchoolActivity.this, AddSchoolActivity.class);
                startActivity(intent);
            }
        });

        viewButton =  findViewById(R.id.button_school_view);
        editButton =  findViewById(R.id.button_school_edit);
        deleteButton =  findViewById(R.id.button_school_delete);
        final DatabaseHelper dbhelper = new DatabaseHelper(this);

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schoolList = dbhelper.getAllSchools();
                if (schoolList.size() > 0) {

                    Intent intent = new Intent(SchoolActivity.this, SchoolListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SchoolActivity.this, "No schools found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    EditText id = (EditText) findViewById(R.id.plain_school_delete_id);
                    String id_string = id.getText().toString();
                    int school_id = Integer.parseInt(id_string);
                    School reg = dbhelper.getSchool((school_id));

                    dbhelper.deleteSchool(reg);
                    Toast.makeText(SchoolActivity.this, "School Delete Successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SchoolActivity.this, SchoolListActivity.class);
                    startActivity(intent);
                }catch(Exception e){
                    Toast.makeText(SchoolActivity.this, "No School Matched", Toast.LENGTH_SHORT).show();

                }
            }
        });


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText id = (EditText) findViewById(R.id.plain_school_edit_id);
                    String id_string = id.getText().toString();
                    int school_id = Integer.parseInt(id_string);
                    Intent intent = new Intent(SchoolActivity.this, EditSchoolActivity.class);
                    intent.putExtra("school_id", school_id);
                    startActivity(intent);
                }catch(Exception e){
                    Toast.makeText(SchoolActivity.this, "No School Matched", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    public List<School> getSchoolList() {
        return schoolList;
    }
}
