package com.skysoft.krd.collegemanagementapi.services.impl;

import com.skysoft.krd.collegemanagementapi.entities.SubjectEntity;
import com.skysoft.krd.collegemanagementapi.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
   private final SubjectRepository subjectRepository;

   public SubjectService(SubjectRepository subjectRepository) {
       this.subjectRepository = subjectRepository;
   }
    public SubjectEntity createSubject(SubjectEntity subjectEntity) {
        return subjectRepository.save(subjectEntity);
    }

    public List<SubjectEntity> getAllSubjects() {
       return subjectRepository.findAll();
    }

    public SubjectEntity getSubject(Long subjectId) {
       return subjectRepository.findById(subjectId).orElse(null);
    }
}
