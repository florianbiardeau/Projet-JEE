package com.example.Projet_JEE.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Pathologie_activite_Id {
    private int idPathologie;
    private int idActivite;
}
