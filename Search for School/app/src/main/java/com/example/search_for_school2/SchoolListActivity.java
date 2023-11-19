package com.example.search_for_school2;

import static com.example.search_for_school2.R.id.list_view_;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import adapter.SchoolAdapter;
import db.DatabaseHelper;
import models.School;

public class SchoolListActivity  extends AppCompatActivity {

    private ListView listView_;
    private SchoolAdapter adapter;
    private List<School> schoolList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_list);

        DatabaseHelper dbhelper = new DatabaseHelper(this);

        schoolList = dbhelper.getAllSchools();

        listView_ = (ListView) findViewById(R.id.list_view_s);
        adapter = new SchoolAdapter(this, R.layout.school_item, schoolList);
        listView_.setAdapter(adapter);
    }
}