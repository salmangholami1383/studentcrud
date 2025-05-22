package com.example.studentcrud;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etFirstName, etLastName, etNationalCode;
    Button btnSave, btnViewList;
    StudentDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etNationalCode = findViewById(R.id.etNationalCode);
        btnSave = findViewById(R.id.btnSave);
        btnViewList = findViewById(R.id.btnViewList);

        db = StudentDatabase.getInstance(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = etFirstName.getText().toString().trim();
                String lastName = etLastName.getText().toString().trim();
                String code = etNationalCode.getText().toString().trim();

                if (firstName.isEmpty() || lastName.isEmpty() || code.isEmpty()) {
                    Toast.makeText(MainActivity.this, "همه فیلدها را پر کنید", Toast.LENGTH_SHORT).show();
                    return;
                }

                Student student = new Student(firstName, lastName, code);
                db.studentDao().insert(student);


                Intent intent = new Intent(MainActivity.this, StudentAddedReceiver.class);
                sendBroadcast(intent);

                etFirstName.setText("");
                etLastName.setText("");
                etNationalCode.setText("");
            }
        });

        btnViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudentListActivity.class);
                startActivity(intent);
            }
        });
    }
}
