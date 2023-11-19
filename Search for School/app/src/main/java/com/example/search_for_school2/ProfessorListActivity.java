package com.example.search_for_school2;

import static com.example.search_for_school2.R.id.list_view_;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import adapter.ProfessorAdapter;
import db.DatabaseHelper;
import models.Professor;

public class ProfessorListActivity extends AppCompatActivity {

    private ListView listView_;
    private ProfessorAdapter adapter;
    private List<Professor> professorList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_list);

        DatabaseHelper dbhelper = new DatabaseHelper(this);

        professorList = dbhelper.getAllProfessors();

        listView_ = (ListView) findViewById(list_view_);
        adapter = new ProfessorAdapter(this, R.layout.professor_item, professorList);
        listView_.setAdapter(adapter);
    }
}