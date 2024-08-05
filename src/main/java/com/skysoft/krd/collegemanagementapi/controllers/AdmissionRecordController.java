package com.skysoft.krd.collegemanagementapi.controllers;

import com.skysoft.krd.collegemanagementapi.entities.AdmissionRecordEntity;
import com.skysoft.krd.collegemanagementapi.services.impl.AdmissionRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("AdmissionRecord")
public class AdmissionRecordController {
    private final AdmissionRecordService admissionRecordService;
    public AdmissionRecordController(final AdmissionRecordService admissionRecordService) {
        this.admissionRecordService = admissionRecordService;
    }
    @GetMapping
    private List<AdmissionRecordEntity> getAllAdmissionRecords() {
        return admissionRecordService.getAllAdmissionRecords();
    }
    @GetMapping("/{admissionRecordId}")
    private AdmissionRecordEntity getAdmissionRecordById( @PathVariable Long admissionRecordId) {
        return admissionRecordService.getAdmissionRecordById(admissionRecordId);
    }
    @PostMapping("/create")
    private AdmissionRecordEntity createAdmissionRecord( @RequestBody AdmissionRecordEntity admissionRecordEntity) {
        System.out.println(admissionRecordEntity.getFees());
        return admissionRecordService.createAdmissionRecord(admissionRecordEntity);
    }
}
