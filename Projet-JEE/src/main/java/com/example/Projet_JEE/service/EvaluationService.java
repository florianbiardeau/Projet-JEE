package com.example.Projet_JEE.service;

import com.example.Projet_JEE.entity.Evaluation_activite;
import com.example.Projet_JEE.repository.Evaluation_activiteRepository;
import com.example.Projet_JEE.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final Evaluation_activiteRepository evaluationRepository;
    private final UtilisateurRepository utilisateurRepository;


    public void saveEvaluation(String username, Long activiteId, int note) {
        Long userId = utilisateurRepository.findByNomUtilisateur(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"))
                .getIdUtilisateur();

        Evaluation_activite evaluation = evaluationRepository
                .findByIdUtilisateurAndIdActivite(userId, activiteId)
                .orElseGet(() -> {
                    Evaluation_activite newEval = new Evaluation_activite();
                    newEval.setIdUtilisateur(userId);
                    newEval.setIdActivite(activiteId);
                    return newEval;
                });

        evaluation.setNote(note);
        evaluationRepository.save(evaluation);
    }
}