package com.ainapapy.mars.controllers;

import com.ainapapy.mars.repositories.UtilisateurRepository;
import com.ainapapy.mars.utils.JwtUtil;
import com.ainapapy.mars.models.Utilisateur;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthRestController(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody Utilisateur utilisateur) {
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        utilisateurRepository.save(utilisateur);
        return "Utilisateur inscrit avec succès";
    }

    @PostMapping("/login")
    public String login(@RequestBody Utilisateur utilisateur) {
        Utilisateur user = utilisateurRepository.findByEmail(utilisateur.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        if (!passwordEncoder.matches(utilisateur.getMotDePasse(), user.getMotDePasse())) {
            throw new RuntimeException("Mot de passe incorrect");
        }
        return jwtUtil.generateToken(user.getEmail());
    }
}
