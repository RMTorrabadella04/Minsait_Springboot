package org.example.controller;

import org.example.model.Ejercicio;
import org.example.service.LiferayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LiferayController {

    private final LiferayService liferayService;

    public LiferayController(LiferayService liferayService) {
        this.liferayService = liferayService;
    }

    @PostMapping("/createEjercicio")
    public ResponseEntity<?> createEjercicio(@RequestBody Ejercicio ejercicio) {
        try {
            // Llamar al servicio para crear el ejercicio en Liferay
            ResponseEntity<String> response = liferayService.createEjercicio(ejercicio);

            // Si la creación es exitosa, respondemos con éxito
            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok("Ejercicio creado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el ejercicio");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
        }
    }
}

