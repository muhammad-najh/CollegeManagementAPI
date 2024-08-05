package com.skysoft.krd.collegemanagementapi.controllers;

import com.skysoft.krd.collegemanagementapi.entities.SubjectEntity;
import com.skysoft.krd.collegemanagementapi.services.impl.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/create")
    public SubjectEntity createSubject(@RequestBody SubjectEntity subjectEntity) {
        return subjectService.createSubject(subjectEntity);
    }
    @GetMapping
    public List<SubjectEntity> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{subjectId}")
    public SubjectEntity getSubject(@PathVariable("subjectId") Long subjectId) {
        return subjectService.getSubject(subjectId);
    }
}
