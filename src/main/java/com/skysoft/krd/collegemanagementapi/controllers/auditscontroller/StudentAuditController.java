package com.skysoft.krd.collegemanagementapi.controllers.auditscontroller;

import com.skysoft.krd.collegemanagementapi.entities.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/audit")
public class StudentAuditController {

    @Autowired
    private  EntityManagerFactory entityManagerFactory;
    @GetMapping(path = "students/{studentId}")
    public List<StudentEntity> getStudentRevisions(@PathVariable("studentId") String studentId) {

        AuditReader reader= AuditReaderFactory.get(entityManagerFactory.createEntityManager());
            List<Number> revisions=reader.getRevisions(StudentEntity.class,studentId);
           return revisions
                   .stream()
                   .map(revisionNumber -> {
                return reader.find(StudentEntity.class,studentId,revisionNumber);
            }).collect(Collectors.toList());
    }
}
