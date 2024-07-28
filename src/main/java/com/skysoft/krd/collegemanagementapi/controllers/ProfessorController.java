package com.skysoft.krd.collegemanagementapi.controllers;

import com.skysoft.krd.collegemanagementapi.entities.ProfessorEntity;
import com.skysoft.krd.collegemanagementapi.services.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Professor")
public class ProfessorController {
    private final ProfessorService professorService;
    public ProfessorController(final ProfessorService professorService) {
        this.professorService = professorService;
    }
    @GetMapping
    public List<ProfessorEntity> getAllProfessor() {
        return professorService.getAllProfessor();
    }
    @GetMapping("/{professorId}")
    public ProfessorEntity getProfessorById(@PathVariable Long professorId) {
        return professorService.getProfessorById(professorId);
    }
    @PostMapping("/create")
    public ProfessorEntity createProfessor(@RequestBody ProfessorEntity professor) {
        return professorService.createProfessor(professor);
    }
    @PutMapping("/{professorId}/subjectToTech/{subjectId}")
    public ProfessorEntity assignSubjectToProffesor(@PathVariable Long professorId, @PathVariable Long subjectId) {
        return professorService.assignSubjectToProffesor(professorId,subjectId);
    }
}
