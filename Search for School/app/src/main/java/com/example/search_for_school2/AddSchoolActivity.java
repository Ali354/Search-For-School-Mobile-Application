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
import models.School;

public class AddSchoolActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneNumberEditText;
    private EditText specialityEditText;
    private EditText addressIdEditText;
    private Button saveButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_school_activity);

        addressIdEditText = (EditText) findViewById(R.id.school_addressId_edit_text);
        nameEditText = (EditText) findViewById(R.id.school_name_edit_text);
        phoneNumberEditText = (EditText) findViewById(R.id.school_phoneNumber_edit_text);
        saveButton = (Button) findViewById(R.id.save_school_button);
        final DatabaseHelper dbHelper = new DatabaseHelper(this);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {
                String addressId_ = addressIdEditText.getText().toString().trim();
                int addressId = Integer.parseInt(addressId_);
                String name = nameEditText.getText().toString().trim();
                String phoneNumber = phoneNumberEditText.getText().toString().trim();
//                String speciality = specialityEditText.getText().toString().trim();

                if (!name.isEmpty() && !phoneNumber.isEmpty() && !addressId_.isEmpty()) {
                    School newSchool = new School(name,phoneNumber,addressId);
                    dbHelper.addSchool(newSchool);
                    Toast.makeText(AddSchoolActivity.this, "School added", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(AddSchoolActivity.this, "Please fill the empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}