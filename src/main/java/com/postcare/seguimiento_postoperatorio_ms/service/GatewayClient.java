package com.postcare.seguimiento_postoperatorio_ms.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

public class GatewayClient {
    private final RestTemplate restTemplate;
    private final String gatewayUrl;

    public GatewayClient(RestTemplate restTemplate, @Value("${gateway.url}") String gatewayUrl) {
        this.restTemplate = restTemplate;
        this.gatewayUrl = gatewayUrl;
    }

    public String enviarSintomas(List<String> sintomas, String operacion) {
        try {
            Map<String, Object> requestBody = Map.of(
                    "sintomas", sintomas,
                    "operacion", operacion
            );

            ResponseEntity<Map> response = restTemplate.postForEntity(
                    gatewayUrl + "/diagnostico",
                    requestBody,
                    Map.class
            );

            return response.getBody().toString();
        } catch (Exception e) {
            return "No se pudo obtener diagnóstico";
        }
    }

    public String actualizarTriage(String prioridad, String idPaciente, String cookies) {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.add("Cookie", cookies);

            Map<String, Object> requestBody = Map.of(
                    "triage", prioridad
            );

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    gatewayUrl + "/auth/users/triage/" + idPaciente + "/",
                    HttpMethod.POST,
                    request,
                    Map.class
            );
            return response.getBody().toString();
        } catch (Exception e) {
            return "No se pudo actualizar la prioridad";
        }
    }
}
