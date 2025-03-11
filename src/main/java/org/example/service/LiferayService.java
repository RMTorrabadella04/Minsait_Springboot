package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.model.Ejercicio;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LiferayService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public LiferayService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }


    public ResponseEntity<String> createEjercicio(Ejercicio ejercicio) throws JsonProcessingException {
        String url = "http://localhost:8080/o/c/ejercicios/";

        // Configuración de los headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        headers.set("Authorization", "Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0Mg==");
        
        // Crear la entidad con el cuerpo y los headers
        HttpEntity<Ejercicio> entity = new HttpEntity<>(ejercicio, headers);

        // Hacer la solicitud POST
        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }
}
