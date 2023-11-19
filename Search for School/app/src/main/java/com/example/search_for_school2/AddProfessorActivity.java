package com.example.search_for_school2;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import db.DatabaseHelper;
import models.Professor;

public class AddProfessorActivity extends AppCompatActivity {

    private EditText firstnameEditText;
    private EditText lastnameEditText;
    private EditText specialityEditText;
    private EditText schoolIdEditText;
    private Button saveButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_professor_activity);

        schoolIdEditText = (EditText) findViewById(R.id.professor_school_id_edit_text);
        firstnameEditText = (EditText) findViewById(R.id.professor_first_name_edit_text);
        lastnameEditText = (EditText) findViewById(R.id.professor_last_name_edit_text);
        specialityEditText = (EditText) findViewById(R.id.professor_speciality_edit_text);
        saveButton = (Button) findViewById(R.id.save_professor_button);
        final DatabaseHelper dbHelper = new DatabaseHelper(this);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {
                String schoolId_ = schoolIdEditText.getText().toString().trim();
                int schoolId = Integer.parseInt(schoolId_);
                String firstname = firstnameEditText.getText().toString().trim();
                String lastname = lastnameEditText.getText().toString().trim();
                String speciality = specialityEditText.getText().toString().trim();

                if (!firstname.isEmpty() && !lastname.isEmpty() && !speciality.isEmpty() && !schoolId_.isEmpty()) {
                    Professor newProfessor = new Professor(firstname,lastname, speciality,schoolId);
                    dbHelper.addProfessor(newProfessor);
                    Toast.makeText(AddProfessorActivity.this, "Professor added", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(AddProfessorActivity.this, "Please fill the empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}