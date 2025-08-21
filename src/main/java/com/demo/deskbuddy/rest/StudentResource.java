package com.demo.deskbuddy.rest;

import com.demo.deskbuddy.domain.Student;
import com.demo.deskbuddy.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class StudentResource {
    private final StudentRepository studentRepository;

    public StudentResource(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/api/students/{id}")
    public Optional<Student> getStudent(@PathVariable Long id) {
        return studentRepository.findById(id);
    }

    @GetMapping("/api/students/search")
    public List<Student> searchStudent(@RequestParam("query") String query) {
        if(query == null || query.isEmpty()) {
            return studentRepository.findAll();
        } else {
            return studentRepository.findAllByFirstNameOrLastNameContainingIgnoreCase(query, query);
        }
    }

    @PostMapping("/api/students")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/api/student/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> optStudent = studentRepository.findById(id);
        if (optStudent.isPresent()) {
            return studentRepository.save(student);
        } else {
            return null;
        }
    }





}
