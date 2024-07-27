package com.skysoft.krd.collegemanagementapi.controllers;

import com.skysoft.krd.collegemanagementapi.entities.StudentEntity;
import com.skysoft.krd.collegemanagementapi.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
   private final StudentService studentService;
   public StudentController(StudentService studentService) {
       this.studentService = studentService;
   }
   @GetMapping
    public List<StudentEntity> getAllStudents() {
       return studentService.getAllStudents();
   }
   @GetMapping("/{studentId}")
    public StudentEntity getStudentById(@PathVariable Long studentId) {
       return studentService.getStudentById(studentId);
   }
   @PostMapping("/create")
    public StudentEntity createStudent(@RequestBody StudentEntity studentEntity){
       return studentService.createStudent(studentEntity);
   }
}
