package com.skysoft.krd.collegemanagementapi.services;

import com.skysoft.krd.collegemanagementapi.entities.AdmissionRecordEntity;
import com.skysoft.krd.collegemanagementapi.entities.ProfessorEntity;
import com.skysoft.krd.collegemanagementapi.entities.StudentEntity;
import com.skysoft.krd.collegemanagementapi.entities.SubjectEntity;
import com.skysoft.krd.collegemanagementapi.repositories.AdmissionRecordRepository;
import com.skysoft.krd.collegemanagementapi.repositories.ProfessorRepository;
import com.skysoft.krd.collegemanagementapi.repositories.StudentRepository;
import com.skysoft.krd.collegemanagementapi.repositories.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final AdmissionRecordRepository admissionRecordRepository;
    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;
    public StudentService(StudentRepository studentRepository, AdmissionRecordRepository admissionRecordRepository, SubjectRepository subjectRepository, ProfessorRepository professorRepository) {
        this.studentRepository = studentRepository;
        this.admissionRecordRepository = admissionRecordRepository;
        this.subjectRepository = subjectRepository;
        this.professorRepository = professorRepository;
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

    public StudentEntity assignAdmissionToStudent(Long studentId, Long admissionId) {
        Optional<StudentEntity>studentEntity = studentRepository.findById(studentId);
        Optional<AdmissionRecordEntity>admissionRecordEntity= admissionRecordRepository.findById(admissionId);
       return studentEntity.flatMap(studentEntity1 ->
                admissionRecordEntity.map(admissionRecordEntity1 -> {
                    studentEntity1.setStudentAdmission(admissionRecordEntity1);
                    return studentRepository.save(studentEntity1);
                })).orElse(null);
    }

    public StudentEntity assignSubjectToStudent(Long studentId, Long subjectId) {
        Optional<StudentEntity>studentEntity = studentRepository.findById(studentId);
        Optional<SubjectEntity>subjectEntity= subjectRepository.findById(subjectId);
       return studentEntity.flatMap(student ->
                subjectEntity.map(subject -> {
                    student.getSubjects().add(subject);
                    studentRepository.save(student);
//                    subject.getSubjectsBelongToStudent().add(student);
//                    subjectRepository.save(subject);
                    return student;
                })).orElse(null);
    }

    public StudentEntity assignProfessorToStudent(Long studentId, Long professorId) {
        Optional<StudentEntity>studentEntity = studentRepository.findById(studentId);
        Optional<ProfessorEntity> professorEntity=professorRepository.findById(professorId);

        return studentEntity.flatMap(student ->
                professorEntity.map(professor -> {
                    student.getStudentTeachByProfessors().add(professor);
                    studentRepository.save(student);
                    return student;
                })).orElse(null);
    }
}
