package com.skysoft.krd.collegemanagementapi.services.impl;

import com.skysoft.krd.collegemanagementapi.entities.ProfessorEntity;
import com.skysoft.krd.collegemanagementapi.entities.SubjectEntity;
import com.skysoft.krd.collegemanagementapi.repositories.ProfessorRepository;
import com.skysoft.krd.collegemanagementapi.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;
    public ProfessorService(ProfessorRepository professorRepository, SubjectRepository subjectRepository) {
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
    }
    public ProfessorEntity createProfessor(ProfessorEntity professor) {
        return  professorRepository.save(professor);
    }

    public ProfessorEntity getProfessorById(Long professorId) {
        return professorRepository.findById(professorId).orElse(null);
    }

    public List<ProfessorEntity> getAllProfessor() {
        return professorRepository.findAll();
    }

    public ProfessorEntity assignSubjectToProffesor(Long professorId, Long subjectId) {
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(subjectId);
        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);
        return professorEntity.flatMap(professor ->
                subjectEntity.map(subject -> {
                    professor.getSubjectsBelongingToProfessor().add(subject);
                    professorRepository.save(professor);
                    subject.setProfessorLecturer(professor);
                    subjectRepository.save(subject);

                    return professor;

                })).orElse(null);


    }
}
