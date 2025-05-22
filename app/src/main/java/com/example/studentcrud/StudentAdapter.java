package com.example.studentcrud;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    Context context;
    List<Student> studentList;
    StudentDatabase db;

    public StudentAdapter(Context context, List<Student> studentList, StudentDatabase db) {
        this.context = context;
        this.studentList = studentList;
        this.db = db;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);

        holder.txtName.setText(student.firstName + " " + student.lastName);
        holder.txtCode.setText("کد ملی: " + student.nationalCode);

        // دکمه حذف
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // نمایش دیالوگ تأیید
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("حذف دانش‌آموز");
                builder.setMessage("آیا از حذف این دانش‌آموز مطمئن هستید؟");

                builder.setPositiveButton("بله", (dialog, which) -> {
                    db.studentDao().delete(student);
                    studentList.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                });

                builder.setNegativeButton("خیر", (dialog, which) -> {
                    dialog.dismiss();
                });

                builder.show();
            }
        });

        // دکمه ویرایش
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(context, UpdateStudentActivity.class);
                editIntent.putExtra("id", student.id);
                editIntent.putExtra("firstName", student.firstName);
                editIntent.putExtra("lastName", student.lastName);
                editIntent.putExtra("code", student.id);
                context.startActivity(editIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtCode;
        Button btnDelete, btnEdit;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tvName);
            txtCode = itemView.findViewById(R.id.tvNationalCode);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }
}
