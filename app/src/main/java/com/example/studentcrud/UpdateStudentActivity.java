package com.example.studentcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class UpdateStudentActivity extends AppCompatActivity {

    EditText etUpdateFirstName, etUpdateLastName, etUpdateNationalCode;
    Button btnUpdateStudent;
    StudentDatabase db;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        etUpdateFirstName = findViewById(R.id.etUpdateFirstName);
        etUpdateLastName = findViewById(R.id.etUpdateLastName);
        etUpdateNationalCode = findViewById(R.id.etUpdateNationalCode);
        btnUpdateStudent = findViewById(R.id.btnUpdateStudent);

        db = StudentDatabase.getInstance(this);

        int id = getIntent().getIntExtra("id", -1);
        if (id != -1) {
            student = db.studentDao().getStudentById(id);

            if (student != null) {
                etUpdateFirstName.setText(student.firstName);
                etUpdateLastName.setText(student.lastName);
                etUpdateNationalCode.setText(student.nationalCode);
            }
        }

        btnUpdateStudent.setOnClickListener(v -> {
            student.firstName = etUpdateFirstName.getText().toString().trim();
            student.lastName = etUpdateLastName.getText().toString().trim();
            student.nationalCode = etUpdateNationalCode.getText().toString().trim();

            db.studentDao().update(student);
            finish(); // بازگشت به لیست دانش‌آموزان
        });
    }
}
