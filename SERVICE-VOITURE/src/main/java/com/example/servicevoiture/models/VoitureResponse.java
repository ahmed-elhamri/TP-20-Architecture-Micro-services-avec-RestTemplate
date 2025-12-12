package com.example.servicevoiture.models;

import com.example.servicevoiture.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder  // Permet d'utiliser le pattern Builder pour créer des instances
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoitureResponse {
    private Long id;
    private String marque;
    private String matricule;
    private String model;
    private Client client;
}
