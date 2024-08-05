package com.skysoft.krd.collegemanagementapi.services;

import com.skysoft.krd.collegemanagementapi.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface  StudentService {
   List<StudentDto> getAllStudents();
   Optional<StudentDto> getStudentById(Long studentId) ;
   StudentDto createStudent(StudentDto studentDto);
   StudentDto updateStudent(StudentDto studentDto,Long id);
   StudentDto assignAdmissionToStudent(Long studentId, Long admissionId);
   StudentDto assignSubjectToStudent(Long studentId, Long subjectId);
   StudentDto assignProfessorToStudent(Long studentId, Long professorId);
}
