package com.skysoft.krd.collegemanagementapi.dto;

import com.skysoft.krd.collegemanagementapi.entities.AdmissionRecordEntity;
import com.skysoft.krd.collegemanagementapi.entities.ProfessorEntity;
import com.skysoft.krd.collegemanagementapi.entities.SubjectEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long id;
    private String name;
    private AdmissionRecordEntity studentAdmission;
    private Set<SubjectEntity> subjects;
    private Set<ProfessorEntity> studentTeachByProfessors;
}
