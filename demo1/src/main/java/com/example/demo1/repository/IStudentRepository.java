package com.example.demo1.repository;

import com.example.demo1.model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository {

    void addStudent(Student student);

    void deleteStudent(int id);

    List<Student> getAllStudent();

    Optional<Student> getStudentById(int id);

    void updateStudent(Student student);

    Student getCustomerById(int customerId);


}
