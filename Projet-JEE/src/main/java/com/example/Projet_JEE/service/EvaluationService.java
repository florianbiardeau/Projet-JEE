package com.example.Projet_JEE.service;

import com.example.Projet_JEE.entity.Evaluation_activite;
import com.example.Projet_JEE.repository.ActiviteRepository;
import com.example.Projet_JEE.repository.Evaluation_activiteRepository;
import com.example.Projet_JEE.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final Evaluation_activiteRepository evaluationRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ActiviteRepository activiteRepository;


    public void saveEvaluation(String username, Long activiteId, int note) {
        Long userId = utilisateurRepository.findByNomUtilisateur(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"))
                .getIdUtilisateur();

        Evaluation_activite evaluation = evaluationRepository
                .findByUtilisateurIdUtilisateurAndActiviteIdActivite(userId, activiteId)
                .orElseGet(() -> {
                    Evaluation_activite newEval = new Evaluation_activite();
                    newEval.setUtilisateur(utilisateurRepository.findByIdUtilisateur(userId));
                    newEval.setActivite(activiteRepository.findByIdActivite(activiteId));
                    return newEval;
                }
                );

        evaluation.setNote(note);
        evaluationRepository.save(evaluation);
    }

    public Double findAverageNoteByActiviteId(Long activiteId) {
        return evaluationRepository.findAverageNoteByActiviteId(activiteId);
    }

    public Integer countByIdActivite(Long activiteId) {
        return evaluationRepository.countByIdActivite(activiteId);
    }

    public Integer findNoteByActiviteIdAndUtilisateurId(Long activiteId, Long utilisateurId) {
        return evaluationRepository.findNoteByActiviteIdAndUtilisateurId(activiteId, utilisateurId);
    }
}