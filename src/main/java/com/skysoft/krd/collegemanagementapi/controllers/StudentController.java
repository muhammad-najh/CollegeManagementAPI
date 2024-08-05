package com.skysoft.krd.collegemanagementapi.controllers;

import com.skysoft.krd.collegemanagementapi.dto.StudentDto;
import com.skysoft.krd.collegemanagementapi.exceptions.ResourceNotFoundException;
import com.skysoft.krd.collegemanagementapi.services.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentController {
   private final StudentServiceImpl studentServiceImpl;
   @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
       return ResponseEntity.ok(studentServiceImpl.getAllStudents());
   }
   @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long studentId) {
       Optional<StudentDto> studentDto= studentServiceImpl.getStudentById(studentId);
       return studentDto.map(ResponseEntity::ok).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
   }
   @PostMapping("/create")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
       StudentDto savedStudentDto = studentServiceImpl.createStudent(studentDto);
       return new ResponseEntity<>(savedStudentDto, HttpStatus.CREATED);
   }
    @PutMapping(path = "/{studentId}/admission/{admissionId}")
    private ResponseEntity<StudentDto> assignAdmissionToStudent(@PathVariable Long studentId, @PathVariable("admissionId") Long admissionId) {
        StudentDto updatedStudentDto= studentServiceImpl.assignAdmissionToStudent(studentId,admissionId);
        return new ResponseEntity<>(updatedStudentDto,HttpStatus.OK);
    }

    @PutMapping("/{studentId}/subject/{subjectId}")
    private ResponseEntity<StudentDto> assignSubjectToStudent(@PathVariable Long studentId, @PathVariable Long subjectId) {
       StudentDto updatedStudentDto= studentServiceImpl.assignSubjectToStudent(studentId,subjectId);
       return new ResponseEntity<>(updatedStudentDto,HttpStatus.OK);
    }

    @PutMapping("/{studentId}/professor/{professorId}")
    private ResponseEntity<StudentDto> assignProfessorToStudent(@PathVariable Long studentId, @PathVariable Long professorId) {
        StudentDto updatedStudentDto= studentServiceImpl.assignProfessorToStudent(studentId,professorId);
        return new ResponseEntity<>(updatedStudentDto,HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto,@PathVariable Long id){
        StudentDto savedStudentDto = studentServiceImpl.updateStudent(studentDto,id);
        return new ResponseEntity<>(savedStudentDto, HttpStatus.CREATED);
    }
}
