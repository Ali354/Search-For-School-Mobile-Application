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
import models.Address;


public class AddAddressActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText codeEditText;
    private Button saveButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_address_activity);

        nameEditText = (EditText) findViewById(R.id.plain_address_add_name);
        codeEditText = (EditText) findViewById(R.id.plain_address_add_code);
        saveButton = (Button) findViewById(R.id.button_save_address_add);
        final DatabaseHelper dbHelper = new DatabaseHelper(this);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String code = codeEditText.getText().toString().trim();

                if (!name.isEmpty() && !code.isEmpty()) {
                    Address newAddress = new Address(name, code);
                    dbHelper.addAddress(newAddress);
                    Toast.makeText(AddAddressActivity.this, "Address added", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(AddAddressActivity.this, "Please enter a name and code", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}