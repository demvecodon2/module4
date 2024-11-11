package com.example.demo1.service;

import com.example.demo1.model.Student;
import com.example.demo1.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService{
    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public void addStudent(Student student) {
        studentRepository.addStudent(student);
    }


    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteStudent(id);

    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.getAllStudent();
    }

    @Override
    public Optional<Student> getStudentById(int id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.updateStudent(student);
    }

    @Override
    public Student getCustomerById(int customerId) {
        return studentRepository.getCustomerById(customerId);
    }
}
