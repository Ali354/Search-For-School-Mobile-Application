package com.example.search_for_school2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import db.DatabaseHelper;
import models.School;



public class UserActivity extends AppCompatActivity {
    private EditText searchInput;
    private Button searchButton;
    private RecyclerView searchResults;

    private DatabaseHelper dbHelper;
    private SearchResultAdapter searchResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_view);

        searchInput = findViewById(R.id.search_input);
        searchButton = findViewById(R.id.search_button);
        searchResults = findViewById(R.id.search_results);

        dbHelper = new DatabaseHelper(this);
//        // Add sample data to the database
//        dbHelper.insertSchool("University of California, Berkeley", "John Smith", "Berkeley, CA");
//        dbHelper.insertSchool("Stanford University", "Jane Doe", "Stanford, CA");
//        dbHelper.insertSchool("Massachusetts Institute of Technology", "Bob Johnson", "Cambridge, MA");
//
//        dbHelper.insertAddrees("Barza", "Damascus");
//        dbHelper.insertAddrees("Mazza", "Damascus");
//        dbHelper.insertAddrees("Masaken", "Damascus");

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchInput.getText().toString();

                List<School> schools = dbHelper.searchSchools(query);

                searchResultAdapter = new SearchResultAdapter(schools);
                searchResults.setAdapter(searchResultAdapter);
                searchResults.setLayoutManager(new LinearLayoutManager(UserActivity.this));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        dbHelper.close();
    }
}