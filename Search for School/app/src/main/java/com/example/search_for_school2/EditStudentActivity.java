package com.example.search_for_school2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import db.DatabaseHelper;
import models.Student;

public class EditStudentActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText schoolIdEditText;

    private int mStudentId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_student_layout);

        firstNameEditText = (EditText) findViewById(R.id.student_first_name_edit_text);
        lastNameEditText = (EditText) findViewById(R.id.student_last_name_edit_text);
        schoolIdEditText = (EditText) findViewById(R.id.student_school_id_edit_text);

        // Get the student ID from the intent
        mStudentId = getIntent().getIntExtra("student_id", -1);
        try {

        // Use the student ID to populate the UI elements with the student's information
        final DatabaseHelper dbhelper = new DatabaseHelper(this);

        final Student student = dbhelper.getStudentById(mStudentId);
        firstNameEditText.setText(student.getFirstName());
        lastNameEditText.setText(student.getLastName());
        schoolIdEditText.setText("1");

        Button saveButton = (Button) findViewById(R.id.save_student_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                // Save the edited student information
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String schoolId_ = schoolIdEditText.getText().toString();
                long schoolId = Long.parseLong(schoolId_);
//                saveStudentInformation(mStudentId, name, code);
                Student newStudent = new Student(firstName,lastName, schoolId);
                newStudent.setId(mStudentId);
                dbhelper.updateStudent(newStudent);
                Toast.makeText(EditStudentActivity.this, "Student Updated Successfully", Toast.LENGTH_SHORT).show();

                // Return to StudentListActivity
                Intent intent = new Intent(EditStudentActivity.this, StudentListActivity.class);
                startActivity(intent);
                }catch(Exception e){
                    Toast.makeText(EditStudentActivity.this, "No Student Matched", Toast.LENGTH_SHORT).show();

                }
            }
        });
        }catch (Exception e){
            Toast.makeText(EditStudentActivity.this, "No Student Matched", Toast.LENGTH_SHORT).show();

        }
    }
}