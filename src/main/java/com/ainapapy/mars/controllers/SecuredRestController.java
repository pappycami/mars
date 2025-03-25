package com.ainapapy.mars.controllers;

import java.util.Collections;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ainap
 */
@RestController
@RequestMapping("/api/secured")
public class SecuredRestController {
    
    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok().body(Collections.singletonMap("message", "Hello utilisateur securisé"));
    }
    
}
