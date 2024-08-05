package com.skysoft.krd.collegemanagementapi.services.impl;

import com.skysoft.krd.collegemanagementapi.dto.StudentDto;
import com.skysoft.krd.collegemanagementapi.entities.AdmissionRecordEntity;
import com.skysoft.krd.collegemanagementapi.entities.ProfessorEntity;
import com.skysoft.krd.collegemanagementapi.entities.StudentEntity;
import com.skysoft.krd.collegemanagementapi.entities.SubjectEntity;
import com.skysoft.krd.collegemanagementapi.exceptions.ResourceNotFoundException;
import com.skysoft.krd.collegemanagementapi.repositories.AdmissionRecordRepository;
import com.skysoft.krd.collegemanagementapi.repositories.ProfessorRepository;
import com.skysoft.krd.collegemanagementapi.repositories.StudentRepository;
import com.skysoft.krd.collegemanagementapi.repositories.SubjectRepository;
import com.skysoft.krd.collegemanagementapi.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final AdmissionRecordRepository admissionRecordRepository;
    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;
    private final ModelMapper modelMapper;

    public List<StudentDto> getAllStudents() {
        List<StudentEntity> studentsEntities = studentRepository.findAll();



        return studentsEntities.stream().map(
                studentEntity -> modelMapper.map(studentEntity, StudentDto.class)
        ).collect(Collectors.toList());

    }

    public Optional<StudentDto> getStudentById(Long studentId) {
        return studentRepository.findById(studentId).map(student -> modelMapper.map(student, StudentDto.class));

    }

    public StudentDto createStudent(StudentDto studentDto) {
        StudentEntity studentEntity= modelMapper.map(studentDto,StudentEntity.class);
        studentRepository.save(studentEntity);

        return  modelMapper.map(studentEntity, StudentDto.class);
    }

    @Override
    public StudentDto updateStudent(StudentDto inputStudentDto, Long id) {
        StudentEntity oldStudentEntity=studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        inputStudentDto.setId(id);
        // it will map all A to B
        modelMapper.map(inputStudentDto, oldStudentEntity);
        StudentEntity newStudentEntity=studentRepository.save(oldStudentEntity);

        return  modelMapper.map(newStudentEntity, StudentDto.class);

    }


    public StudentDto assignAdmissionToStudent(Long studentId, Long admissionId) {
        Optional<StudentEntity>studentEntity = studentRepository.findById(studentId);
        Optional<AdmissionRecordEntity>admissionRecordEntity= admissionRecordRepository.findById(admissionId);
        if (studentEntity.isEmpty()) {
          throw new ResourceNotFoundException("Student not found");
        }
        if (admissionRecordEntity.isEmpty()) {
            throw new ResourceNotFoundException("AdmissionRecord not found");
        }
       StudentEntity updatedStudentEntity= studentEntity.flatMap(studentEntity1 ->
                admissionRecordEntity.map(admissionRecordEntity1 -> {
                    studentEntity1.setStudentAdmission(admissionRecordEntity1);
                    return studentRepository.save(studentEntity1);
                })).orElse(null);

       return modelMapper.map(updatedStudentEntity, StudentDto.class);
    }

    public StudentDto assignSubjectToStudent(Long studentId, Long subjectId) {
        Optional<StudentEntity>studentEntity = studentRepository.findById(studentId);
        Optional<SubjectEntity>subjectEntity= subjectRepository.findById(subjectId);
        if (studentEntity.isEmpty()) {
                throw new ResourceNotFoundException("Student not found");
        }
        if (subjectEntity.isEmpty()) {
            throw new ResourceNotFoundException("Subject not found");
        }
       StudentEntity assignedSubjectToStudentEntity= studentEntity.flatMap(student ->
                subjectEntity.map(subject -> {
                    student.getSubjects().add(subject);
                    studentRepository.save(student);
//                    subject.getSubjectsBelongToStudent().add(student);
//                    subjectRepository.save(subject);
                    return student;
                })).orElse(null);
        return modelMapper.map(assignedSubjectToStudentEntity, StudentDto.class);
    }

    public StudentDto assignProfessorToStudent(Long studentId, Long professorId) {
        Optional<StudentEntity>studentEntity = studentRepository.findById(studentId);
        Optional<ProfessorEntity> professorEntity=professorRepository.findById(professorId);

        if (studentEntity.isEmpty()) {
            throw new ResourceNotFoundException("Student not found");
        }
        if (professorEntity.isEmpty()) {
            throw new ResourceNotFoundException("Professor not found");
        }
        StudentEntity assignProfessorToStudent=  studentEntity.flatMap(student ->
                professorEntity.map(professor -> {
                    student.getStudentTeachByProfessors().add(professor);
                    studentRepository.save(student);
                    return student;
                })).orElse(null);
        return modelMapper.map(assignProfessorToStudent, StudentDto.class);
    }
}
