package com.example.servicevoiture.services;

import com.example.servicevoiture.entities.Client;
import com.example.servicevoiture.entities.Voiture;
import com.example.servicevoiture.models.VoitureResponse;
import com.example.servicevoiture.repositories.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoitureService {

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * URL du service client via la Gateway
     * Remarque: Dans un environnement de production, cette URL serait configurée
     * dans le fichier de propriétés ou récupérée via un service de découverte
     */
    private static final String CLIENT_SERVICE_URL =
            "http://localhost:8888/SERVICE-CLIENT/api/client/";

    /**
     * Récupère toutes les voitures avec les détails des clients
     */
    public List<VoitureResponse> findAll() {
        List<Voiture> voitures = voitureRepository.findAll();

        return voitures.stream()
                .map(this::mapToVoitureResponse)
                .collect(Collectors.toList());
    }

    /**
     * Récupère une voiture par son ID avec les détails du client
     */
    public VoitureResponse findById(Long id) throws Exception {
        Voiture voiture = voitureRepository.findById(id)
                .orElseThrow(() -> new Exception("Voiture non trouvée avec l'ID: " + id));

        return mapToVoitureResponse(voiture);
    }

    /**
     * Convertit une entité Voiture en VoitureResponse en récupérant les détails du client
     */
    private VoitureResponse mapToVoitureResponse(Voiture voiture) {
        // Récupération du client depuis le service client
        Client client = null;
        try {
            client = restTemplate.getForObject(
                    CLIENT_SERVICE_URL + voiture.getId_client(),
                    Client.class);
        } catch (Exception e) {
            // En cas d'erreur, on continue avec un client null
            System.err.println("Erreur lors de la récupération du client: " + e.getMessage());
        }

        // Construction de la réponse
        return VoitureResponse.builder()
                .id(voiture.getId())
                .marque(voiture.getMarque())
                .model(voiture.getModel())
                .matricule(voiture.getMatricule())
                .client(client)
                .build();
    }
}