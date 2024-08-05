package com.skysoft.krd.collegemanagementapi.services.impl;

import com.skysoft.krd.collegemanagementapi.entities.AdmissionRecordEntity;
import com.skysoft.krd.collegemanagementapi.repositories.AdmissionRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdmissionRecordService {
    private final AdmissionRecordRepository admissionRecordRepository;

    public AdmissionRecordService(AdmissionRecordRepository admissionRecordRepository) {
        this.admissionRecordRepository = admissionRecordRepository;
    }

    public AdmissionRecordEntity createAdmissionRecord(AdmissionRecordEntity admissionRecordEntity) {
        return admissionRecordRepository.save(admissionRecordEntity);
    }

    public AdmissionRecordEntity getAdmissionRecordById(Long admissionRecordId) {
        return admissionRecordRepository.findById(admissionRecordId).orElse(null);
    }

    public List<AdmissionRecordEntity> getAllAdmissionRecords() {
        return admissionRecordRepository.findAll();
    }
}
