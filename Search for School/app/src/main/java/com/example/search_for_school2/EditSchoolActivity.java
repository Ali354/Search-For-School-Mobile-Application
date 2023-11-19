package com.example.search_for_school2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import db.DatabaseHelper;
import models.School;

public class EditSchoolActivity extends AppCompatActivity {

    private EditText NameEditText;
    private EditText phoneNumberEditText;
    private EditText addressIdEditText;

    private int mSchoolId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_school_layout);

        NameEditText = (EditText) findViewById(R.id.school_name_edit_text);
        phoneNumberEditText = (EditText) findViewById(R.id.school_phoneNumber_edit_text);
        addressIdEditText = (EditText) findViewById(R.id.school_addressId_edit_text);

        // Get the school ID from the intent
        mSchoolId = getIntent().getIntExtra("school_id", -1);
        try {
            // Use the school ID to populate the UI elements with the school's information
            final DatabaseHelper dbhelper = new DatabaseHelper(this);

            final School school = dbhelper.getSchool(mSchoolId);
            NameEditText.setText(school.getName());
            phoneNumberEditText.setText(school.getPhoneNumber());
            addressIdEditText.setText("1");

        Button saveButton = (Button) findViewById(R.id.save_school_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Save the edited school information
                    String Name = NameEditText.getText().toString();
                    String phoneNumber = phoneNumberEditText.getText().toString();
                    String addressId_ = addressIdEditText.getText().toString();
                    long addressId = Long.parseLong(addressId_);
//                saveSchoolInformation(mSchoolId, name, code);
                    School newSchool = new School(Name, phoneNumber, addressId);
                    newSchool.setId(mSchoolId);
                    dbhelper.updateSchool(newSchool);
                    Toast.makeText(EditSchoolActivity.this, "School Updated Successfully", Toast.LENGTH_SHORT).show();

                    // Return to SchoolListActivity
                    Intent intent = new Intent(EditSchoolActivity.this, SchoolListActivity.class);
                    startActivity(intent);
                }catch(Exception e){
                    Toast.makeText(EditSchoolActivity.this, "No Schools Matched", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }catch (Exception e){
        Toast.makeText(EditSchoolActivity.this, "No Schools Matched", Toast.LENGTH_SHORT).show();

    }
    }
}