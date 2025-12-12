package com.example.clientservice.repositories;

import com.example.clientservice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // Marque cette interface comme un composant Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // JpaRepository fournit déjà les méthodes CRUD de base:
    // save(), findById(), findAll(), deleteById(), etc.

    // Vous pouvez ajouter des méthodes personnalisées ici, par exemple:
    // List<Client> findByNomContaining(String nom);
}