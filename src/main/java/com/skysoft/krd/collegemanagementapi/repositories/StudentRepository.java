package com.skysoft.krd.collegemanagementapi.repositories;

import com.skysoft.krd.collegemanagementapi.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
