package com.example.studentcrud;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert
    void insert(Student student);

    @Update
    void update(Student student);

    @Delete
    void delete(Student student);

    @Query("SELECT * FROM Student")
    List<Student> getAllStudents();

    @Query("SELECT * FROM Student WHERE id = :studentId LIMIT 1")
    Student getStudentById(int studentId);
}
