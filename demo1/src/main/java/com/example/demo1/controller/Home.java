package com.example.demo1.controller;

import com.example.demo1.model.Student;
import com.example.demo1.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class Home {

    @Autowired
    private IStudentService studentService;

    @GetMapping("/student")
    public String getAllStudent(Model model) {
        List<Student> students = studentService.getAllStudent();
        model.addAttribute("students", students);
        return "/student/list";
    }

    @GetMapping("/student/add")
    public String addStudent(Model model) {
        Student student = new Student(0, "", "", "");
        model.addAttribute("student", student);
        return "/student/add";
    }


    @PostMapping("/student/add")
    public String saveStudent(Student student) {
        studentService.addStudent(student);
        return "redirect:/student";
    }



    @PostMapping("/student/{id}/update")
    public String updateStudent(@PathVariable("id") int id, Student student) {
        student.setId(id);
        studentService.updateStudent(student);
        return "redirect:/student";
    }


    @GetMapping("/student/{id}/edit")
    public String editStudent(Model model, @PathVariable("id") int id) {
        Student student = studentService.getStudentById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
        model.addAttribute("student", student);
        return "/student/edit";
    }

    @GetMapping("/student/{id}/delete")
    public String deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
        return "redirect:/student";
    }
    @GetMapping("/student/{id}/view")
    public String viewStudent(@PathVariable("id") int id, Model model) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + id));
        model.addAttribute("student", student);
        return "/student/view";
    }

}
