package com.example.servicevoiture.controllers;

import com.example.servicevoiture.models.VoitureResponse;
import com.example.servicevoiture.services.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/voiture")
public class VoitureController {
    @Autowired
    private VoitureService voitureService;

    /**
     * Récupère toutes les voitures avec les détails des clients
     * GET /api/voiture
     */
    @GetMapping
    public List<VoitureResponse> findAll() {
        return voitureService.findAll();
    }

    /**
     * Récupère une voiture par son ID avec les détails du client
     * GET /api/voiture/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            VoitureResponse voiture = voitureService.findById(id);
            return ResponseEntity.ok(voiture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erreur: " + e.getMessage());
        }
    }
}
