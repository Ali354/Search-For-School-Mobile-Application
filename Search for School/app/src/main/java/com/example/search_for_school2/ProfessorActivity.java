package com.example.search_for_school2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import db.DatabaseHelper;
import models.Professor;

public class ProfessorActivity extends AppCompatActivity {
    private ImageButton addButton;
    private ImageButton viewButton;
    private ImageButton editButton;
    private ImageButton deleteButton;
    public static List<Professor> professorList;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        addButton =  findViewById(R.id.button_professor_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfessorActivity.this, AddProfessorActivity.class);
                startActivity(intent);
            }
        });

        viewButton =  findViewById(R.id.button_professor_view);
        editButton =  findViewById(R.id.button_professor_edit);
        deleteButton =  findViewById(R.id.button_professor_delete);
        final DatabaseHelper dbhelper = new DatabaseHelper(this);

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                professorList = dbhelper.getAllProfessors();
                if (professorList.size() > 0) {

                    Intent intent = new Intent(ProfessorActivity.this, ProfessorListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ProfessorActivity.this, "No professors found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText id = (EditText) findViewById(R.id.plain_professor_delete_id);
                    String id_string = id.getText().toString();
                    int professor_id = Integer.parseInt(id_string);
                    Professor reg = dbhelper.getProfessorById((professor_id));

                    dbhelper.deleteProfessor(reg);
                    Toast.makeText(ProfessorActivity.this, "Professor Deleted Successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ProfessorActivity.this, ProfessorListActivity.class);
                    startActivity(intent);
                }catch(Exception e){
                    Toast.makeText(ProfessorActivity.this, "No Professor Matched", Toast.LENGTH_SHORT).show();

                }
            }
        });


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText id = (EditText) findViewById(R.id.plain_professor_edit_id);
                    String id_string = id.getText().toString();
                    int professor_id = Integer.parseInt(id_string);

                    Intent intent = new Intent(ProfessorActivity.this, EditProfessorActivity.class);
                    intent.putExtra("professor_id", professor_id);
                    startActivity(intent);
                }catch(Exception e){
                    Toast.makeText(ProfessorActivity.this, "No Professor Matched", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    public List<Professor> getProfessorList() {
        return professorList;
    }
}