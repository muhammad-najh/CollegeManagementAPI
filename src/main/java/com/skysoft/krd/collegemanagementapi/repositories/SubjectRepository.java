package com.skysoft.krd.collegemanagementapi.repositories;

import com.skysoft.krd.collegemanagementapi.entities.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
}
