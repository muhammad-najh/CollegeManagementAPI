package com.skysoft.krd.collegemanagementapi.services;

import com.skysoft.krd.collegemanagementapi.entities.ProfessorEntity;
import com.skysoft.krd.collegemanagementapi.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
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
}
