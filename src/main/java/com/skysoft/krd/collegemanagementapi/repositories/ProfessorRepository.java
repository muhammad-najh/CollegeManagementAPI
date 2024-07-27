package com.skysoft.krd.collegemanagementapi.repositories;

import com.skysoft.krd.collegemanagementapi.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
}
