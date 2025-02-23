package com.example.Projet_JEE.controller;

import com.example.Projet_JEE.service.Programme_therapeutiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/programmes-therapeutiques")
public class Programme_therapeutiqueController {

    @Autowired
    private Programme_therapeutiqueService programmeTherapeutiqueService;

    @PostMapping("/{idProgramme}/activites")
    public ResponseEntity<Void> ajouterActiviteAuProgramme(@PathVariable int idProgramme, @RequestBody int idActivite) {
        programmeTherapeutiqueService.ajouterActiviteAuProgramme(idProgramme, idActivite);
        return ResponseEntity.ok().build();
    }
}
