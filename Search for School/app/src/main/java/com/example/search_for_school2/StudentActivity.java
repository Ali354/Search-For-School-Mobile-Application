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
import models.Student;

public class StudentActivity extends AppCompatActivity {
    private ImageButton addButton;
    private ImageButton viewButton;
    private ImageButton editButton;
    private ImageButton deleteButton;
    public static List<Student> studentList;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        addButton =  findViewById(R.id.button_student_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentActivity.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });

        viewButton =  findViewById(R.id.button_student_view);
        editButton =  findViewById(R.id.button_student_edit);
        deleteButton =  findViewById(R.id.button_student_delete);
        final DatabaseHelper dbhelper = new DatabaseHelper(this);

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentList = dbhelper.getAllStudents();
                if (studentList.size() > 0) {

                    Intent intent = new Intent(StudentActivity.this, StudentListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(StudentActivity.this, "No students found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    EditText id = (EditText) findViewById(R.id.plain_student_delete_id);
                    String id_string = id.getText().toString();
                    int student_id = Integer.parseInt(id_string);
                    Student reg = dbhelper.getStudentById((student_id));

                    dbhelper.deleteStudent(reg);
                    Toast.makeText(StudentActivity.this, "Student Deleted Successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(StudentActivity.this, StudentListActivity.class);
                    startActivity(intent);
                }catch(Exception e){
                    Toast.makeText(StudentActivity.this, "No Student Matched", Toast.LENGTH_SHORT).show();

                }
            }
        });


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText id = (EditText) findViewById(R.id.plain_student_edit_id);
                    String id_string = id.getText().toString();
                    int student_id = Integer.parseInt(id_string);

                    Intent intent = new Intent(StudentActivity.this, EditStudentActivity.class);
                    intent.putExtra("student_id", student_id);
                    startActivity(intent);
                }catch(Exception e){
                    Toast.makeText(StudentActivity.this, "No Student Matched", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    public List<Student> getStudentList() {
        return studentList;
    }
}