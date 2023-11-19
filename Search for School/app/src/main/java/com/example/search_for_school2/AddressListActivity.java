package com.example.search_for_school2;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import adapter.AddressAdapter;
import db.DatabaseHelper;
import models.Address;

public class AddressListActivity extends AppCompatActivity {

    private ListView listView;
    private AddressAdapter adapter;
    private List<Address> addressList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        DatabaseHelper dbhelper = new DatabaseHelper(this);
        addressList = dbhelper.getAllAddresss();
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new AddressAdapter(this, R.layout.address_item, addressList);
        listView.setAdapter(adapter);
    }
}