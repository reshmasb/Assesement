package com.example.Student.Management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Student.Management.entity.Student;
import com.example.Student.Management.repository.StudentRepository;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        student.calculateAge();
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsBetween18And25() {
        return studentRepository.findStudentsBetween18And25();
    }
}