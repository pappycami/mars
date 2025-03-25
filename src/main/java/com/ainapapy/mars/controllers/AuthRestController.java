package com.ainapapy.mars.controllers;

import com.ainapapy.mars.repositories.UtilisateurRepository;
import com.ainapapy.mars.models.dto.UtilisateurDto;
import com.ainapapy.mars.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UtilisateurDto utilisateurDto) {
        if (utilisateurRepository.findByEmail(utilisateurDto.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Email déjà utilisé !");
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDto.getPassword()));
        utilisateur.getRoles().add("ROLE_USER"); // Ajout d'un rôle par défaut

        utilisateurRepository.save(utilisateur);
        return ResponseEntity.ok("Utilisateur enregistré avec succès !");
    }
}
