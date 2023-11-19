package com.example.search_for_school2;

//import static com.example.search_for_school2.R.id.list_view_;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import adapter.StudentAdapter;
import db.DatabaseHelper;
import models.Student;

public class StudentListActivity  extends AppCompatActivity {

    private ListView listView_;
    private StudentAdapter adapter;
    private List<Student> studentList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        DatabaseHelper dbhelper = new DatabaseHelper(this);

        studentList = dbhelper.getAllStudents();

        listView_ = (ListView) findViewById(R.id.list_view_l);
        adapter = new StudentAdapter(this, R.layout.student_item, studentList);
        listView_.setAdapter(adapter);
    }
}