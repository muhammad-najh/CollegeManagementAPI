package com.skysoft.krd.collegemanagementapi.repositories;

import com.skysoft.krd.collegemanagementapi.entities.AdmissionRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionRecordRepository extends JpaRepository<AdmissionRecordEntity, Long> {
}
