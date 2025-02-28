package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/activites")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping("/{activiteId}/rate")
    public ResponseEntity<?> rateActivity(
            @PathVariable Long activiteId,
            @RequestBody Map<String, Integer> request,
            Authentication authentication) {

        evaluationService.saveEvaluation(
                authentication.getName(),
                activiteId,
                request.get("note")
        );
        return ResponseEntity.ok().build();
    }
}