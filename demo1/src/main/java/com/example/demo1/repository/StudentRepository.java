package com.example.demo1.repository;

import com.example.demo1.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository implements IStudentRepository {

    private static List<Student> students = new ArrayList<>();

    static {
        students.add(new Student(1, "Phương", "phuong@gmail.com", "12311233"));
        students.add(new Student(2, "Minh", "minh@gmail.com", "12312312"));
        students.add(new Student(3, "Hiếu", "hieu@gmail.com", "1234567890"));
    }


    @Override
    public void addStudent(Student student) {
        students.add(student);
    }


    @Override
    public void deleteStudent(int id) {
        students.removeIf(student -> student.getId() == id);
    }


    @Override
    public List<Student> getAllStudent() {
        return new ArrayList<>(students);
    }


    @Override
    public Optional<Student> getStudentById(int id) {
        return students.stream().filter(student -> student.getId() == id).findFirst();
    }

    @Override
    public void updateStudent(Student updatedStudent) {
        for (Student student : students) {
            if (student.getId() == updatedStudent.getId()) {
                student.setName(updatedStudent.getName());
                student.setPhone(updatedStudent.getPhone());
                System.out.println("Updated successfully: " + student);
                return;
            }
        }
        System.out.println("Student with ID " + updatedStudent.getId() + " not found.");
    }

    @Override
    public Student getCustomerById(int customerId) {
        for (Student student : students) {
            if (student.getId() == customerId) {
                System.out.println("Student Details: " + student);
                return student;
            }
        }
        System.out.println("No student found with ID: " + customerId);
        return null;
    }

}
