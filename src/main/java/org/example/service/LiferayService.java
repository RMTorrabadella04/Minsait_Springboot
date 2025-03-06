package org.example.service;

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

    public String obtenerToken() {
        String url = "http://localhost:8080/o/headless-admin-user/v1.0/authentication";

        // Configura los headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Crea el cuerpo de la solicitud con tus credenciales
        String body = "{ \"username\": \"test@liferay.com\", \"password\": \"test2\" }";
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            // Realiza la solicitud POST
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Verificar si la respuesta es exitosa
            if (response.getStatusCode() == HttpStatus.OK) {
                // Extraer el token del cuerpo de la respuesta
                JsonNode jsonResponse = objectMapper.readTree(response.getBody());
                return jsonResponse.path("token").asText();  // Extraer el token
            } else {
                throw new RuntimeException("Error al obtener el token: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            // Manejar errores de cliente y servidor
            throw new RuntimeException("Error en la solicitud de token: " + ex.getMessage());
        } catch (Exception e) {
            // Manejo genérico de excepciones
            throw new RuntimeException("Error inesperado: " + e.getMessage());
        }
    }

    public ResponseEntity<String> createEjercicio(Ejercicio ejercicio) {
        String url = "http://localhost:8080/o/c/ejercicios/post/postEjercicio/";

        // Configuración de los headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        try {
            // Obtener el token de autenticación
            String token = obtenerToken();
            headers.set("Authorization", "Bearer " + token);

            // Crear la entidad con el cuerpo y los headers
            HttpEntity<Ejercicio> entity = new HttpEntity<>(ejercicio, headers);

            // Hacer la solicitud POST
            return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        } catch (Exception e) {
            // Manejar errores de la solicitud
            throw new RuntimeException("Error al crear el ejercicio: " + e.getMessage());
        }
    }
}
