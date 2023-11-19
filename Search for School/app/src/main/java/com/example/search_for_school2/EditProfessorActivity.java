package com.example.search_for_school2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import db.DatabaseHelper;
import models.Professor;

public class EditProfessorActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText specialityEditText;
    private EditText schoolIdEditText;

    private int mProfessorId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_professor_layout);

        firstNameEditText = (EditText) findViewById(R.id.professor_first_name_edit_text);
        lastNameEditText = (EditText) findViewById(R.id.professor_last_name_edit_text);
        specialityEditText = (EditText) findViewById(R.id.professor_speciality_edit_text);
        schoolIdEditText = (EditText) findViewById(R.id.professor_school_id_edit_text);

        // Get the professor ID from the intent
        mProfessorId = getIntent().getIntExtra("professor_id", -1);
        try {

        // Use the professor ID to populate the UI elements with the professor's information
        final DatabaseHelper dbhelper = new DatabaseHelper(this);

        final Professor professor = dbhelper.getProfessorById(mProfessorId);
        firstNameEditText.setText(professor.getFirstName());
        lastNameEditText.setText(professor.getLastName());
        specialityEditText.setText(professor.getSpecialty());
        schoolIdEditText.setText("1");

        Button saveButton = (Button) findViewById(R.id.save_professor_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Save the edited professor information
                    String firstName = firstNameEditText.getText().toString();
                    String lastName = lastNameEditText.getText().toString();
                    String speciality = specialityEditText.getText().toString();
                    String schoolId_ = schoolIdEditText.getText().toString();
                    long schoolId = Long.parseLong(schoolId_);
//                saveProfessorInformation(mProfessorId, name, code);
                    Professor newProfessor = new Professor(firstName, lastName, speciality, schoolId);
                    newProfessor.setId(mProfessorId);
                    dbhelper.updateProfessor(newProfessor);
                    Toast.makeText(EditProfessorActivity.this, "Professor Updated Successfully", Toast.LENGTH_SHORT).show();

                    // Return to ProfessorListActivity
                    Intent intent = new Intent(EditProfessorActivity.this, ProfessorListActivity.class);
                    startActivity(intent);
                }catch(Exception e){
                    Toast.makeText(EditProfessorActivity.this, "No Professor Matched", Toast.LENGTH_SHORT).show();

                }
            }
        });
        }catch (Exception e){
            Toast.makeText(EditProfessorActivity.this, "No Professor Matched", Toast.LENGTH_SHORT).show();

        }
    }
}
