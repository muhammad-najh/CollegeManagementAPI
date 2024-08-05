package com.skysoft.krd.collegemanagementapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "students")
@Audited
public class StudentEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "admission_id_mapping")
    @NotAudited //excluding in audit table
    private AdmissionRecordEntity studentAdmission;

    @ManyToMany

    @JoinTable(
            name = "student_subject_mapping",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")

    )

    @NotAudited //excluding in audit table
    private Set<SubjectEntity> subjects;


    @JoinTable(
            name = "student_professor_mapping",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    @ManyToMany
    @NotAudited //excluding in audit table
    private Set<ProfessorEntity> studentTeachByProfessors;


    //Life cycle hocks for database
    // you can remove behavior of auditable listiner you can remove  it and try to create your own audutable senario

    //before save
    @PrePersist
    void beforeSave(){
// createAt = LocalDateTime.now(); this will run by defult
// for example you want to count++ number of student in any other place in database
    }

    @PreUpdate
    void beforeUpdate(){

    }

    @PreRemove
    void beforeRemove(){

    }



}
