package com.ainapapy.mars.services;

import com.ainapapy.mars.models.dto.UtilisateurDto;
import com.ainapapy.mars.models.Utilisateur;
import com.ainapapy.mars.repositories.UtilisateurRepository;
import com.ainapapy.mars.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(UtilisateurDto dto) {
        
        Utilisateur user = new Utilisateur();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.getRoles().add("ROLE_USER");
        utilisateurRepository.save(user);
        return jwtUtil.generateToken(user.getEmail());
    }

    public String login(UtilisateurDto dto) throws Exception {
        Utilisateur user = utilisateurRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new Exception("Email invalide"));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new Exception("Mot de passe invalide");
        }
        return jwtUtil.generateToken(user.getEmail());
    }
    
}
