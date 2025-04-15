package com.ainapapy.mars.controllers;

import com.ainapapy.mars.models.dto.UtilisateurDto;
import com.ainapapy.mars.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UtilisateurDto dto) {
        String token = authService.register(dto);
        return ResponseEntity.ok().body("Token : " + token);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UtilisateurDto dto) {
        try {
            String token = authService.login(dto);
            return ResponseEntity.ok().body("Token : " + token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Erreur : " + e.getMessage());
        }
    }
}
