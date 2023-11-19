package com.example.search_for_school2;


import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import db.DatabaseHelper;
import models.Address;

public class AddressActivity extends AppCompatActivity {
    private ImageButton addButton;
    private ImageButton viewButton;
    private ImageButton editButton;
    private ImageButton deleteButton;
    public static List<Address> addressList;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        addButton =  findViewById(R.id.button_address_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });

        viewButton =  findViewById(R.id.button_address_view);
        editButton =  findViewById(R.id.button_address_edit);
        deleteButton =  findViewById(R.id.button_address_delete);
        final DatabaseHelper dbhelper = new DatabaseHelper(this);

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressList = dbhelper.getAllAddresss();
                if (addressList.size() > 0) {

                    Intent intent = new Intent(AddressActivity.this, AddressListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddressActivity.this, "No addresss found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText id = (EditText) findViewById(R.id.plain_address_delete_id);
                    String id_string = id.getText().toString();
                    int address_id = Integer.parseInt(id_string);
                    Address reg = dbhelper.getAddress((address_id));

                    dbhelper.deleteAddress(reg);
                    Toast.makeText(AddressActivity.this, "Address Deleted Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddressActivity.this, AddressListActivity.class);
                    startActivity(intent);
                }catch (Exception e) {
                    Toast.makeText(AddressActivity.this, "No Address Matched!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddressActivity.this, AddressListActivity.class);
                    startActivity(intent);
                }
            }
        });


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText id = (EditText) findViewById(R.id.plain_address_edit_id);
                    String id_string = id.getText().toString();
                    int address_id = Integer.parseInt(id_string);
                    Intent intent = new Intent(AddressActivity.this, EditAddressActivity.class);
                    intent.putExtra("address_id", address_id);
                    startActivity(intent);
                }catch(Exception e){
                    Toast.makeText(AddressActivity.this, "No Address Matched", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public List<Address> getAddressList() {
        return addressList;
    }
}