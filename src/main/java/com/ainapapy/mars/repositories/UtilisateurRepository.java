package com.ainapapy.mars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ainapapy.mars.models.Utilisateur;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);
}

