package com.example.studentcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StudentAdapter adapter;
    StudentDatabase db;
    List<Student> studentList;

    @Override
    protected void onResume() {
        super.onResume();
        loadStudents();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        recyclerView = findViewById(R.id.recyclerViewStudents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = StudentDatabase.getInstance(this);
        loadStudents();
    }

    private void loadStudents() {
        studentList = db.studentDao().getAllStudents();
        adapter = new StudentAdapter(this, studentList, db);
        recyclerView.setAdapter(adapter);
    }
}
