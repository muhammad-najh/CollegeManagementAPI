package com.skysoft.krd.collegemanagementapi.services;

import com.skysoft.krd.collegemanagementapi.entities.StudentEntity;
import com.skysoft.krd.collegemanagementapi.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentEntity getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public StudentEntity createStudent(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }
}
