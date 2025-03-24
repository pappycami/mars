package com.ainapapy.mars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ainapapy.mars.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);
}

