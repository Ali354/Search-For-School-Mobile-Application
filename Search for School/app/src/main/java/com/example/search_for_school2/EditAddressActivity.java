package com.example.search_for_school2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.search_for_school2.R;

import db.DatabaseHelper;
import models.Address;

/**
 */

// In EditAddressActivity.java

public class EditAddressActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mCodeEditText;
    private TextView mIdTextView;

    private int mAddressId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_address_layout);

        mNameEditText = (EditText) findViewById(R.id.name_edit_text);
        mCodeEditText = (EditText) findViewById(R.id.code_edit_text);
        mIdTextView = (TextView) findViewById(R.id.id_text_view);

        // Get the address ID from the intent
        mAddressId = getIntent().getIntExtra("address_id", -1);
        try {

            // Use the address ID to populate the UI elements with the address's information
            final DatabaseHelper dbhelper = new DatabaseHelper(this);
            try {
                final Address address = dbhelper.getAddress(mAddressId);

                mNameEditText.setText(address.getStreet());
                mCodeEditText.setText(address.getCity());
                mIdTextView.setText(String.valueOf(address.getId()));
            } catch (Exception e) {
                Toast.makeText(EditAddressActivity.this, "No Address Found!", Toast.LENGTH_SHORT).show();
            }


            Button saveButton = (Button) findViewById(R.id.save_button);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Save the edited address information
                    String name = mNameEditText.getText().toString();
                    String code = mCodeEditText.getText().toString();
//                saveAddressInformation(mAddressId, name, code);
                    Address newAddress = new Address(name, code);
                    newAddress.setId(mAddressId);
                    dbhelper.updateAddress(newAddress);
                    Toast.makeText(EditAddressActivity.this, "Address Updated Successfully", Toast.LENGTH_SHORT).show();

                    // Return to AddressListActivity
                    Intent intent = new Intent(EditAddressActivity.this, AddressListActivity.class);
                    startActivity(intent);
                }
            });
        }catch (Exception e){
            Toast.makeText(EditAddressActivity.this, "No Address Matched", Toast.LENGTH_SHORT).show();

        }
    }
}
