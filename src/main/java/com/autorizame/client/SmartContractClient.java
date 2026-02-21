package com.autorizame.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class SmartContractClient {

    private final RestClient restClient;

    public SmartContractClient(@Value("${microservicios.smartcontract.url}") String smartContractUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(smartContractUrl)
                .build();
    }

    /**
     * Mintea un NFT de autorización en la blockchain
     * @param destinatario address del cliente
     * @param personaAutorizada address del autorizado
     * @param referenciaExterna ID numérico del pedido
     * @param pin pin de seguridad numérico
     * @param rutaDatos URL o CID de IPFS
     * @return respuesta con success, transactionHash, bloque
    */
    @SuppressWarnings("unchecked")
    public Map<String, Object> mintarAutorizacion(String destinatario, String personaAutorizada,
                                                  Long referenciaExterna, Integer pin, String rutaDatos) {
        Map<String, Object> requestBody = Map.of(
                "destinatario", destinatario,
                "personaAutorizada", personaAutorizada != null ? personaAutorizada : "0x0000000000000000000000000000000000000000",
                "referenciaExterna", referenciaExterna,
                "pin", pin != null ? pin : 0,
                "rutaDatos", rutaDatos
        );

        return restClient.post()
                .uri("/mintarAutorizacion")
                .body(requestBody)
                .retrieve()
                .body(Map.class);
    }

    /**
     * Transfiere un token de una persona a otra
     * @param idToken ID del token
     * @param nuevoDestinatario nueva dirección del destinatario
     * @return respuesta con success, mensaje, transactionHash
    */
    @SuppressWarnings("unchecked")
    public Map<String, Object> transferirAutorizacion(Long idToken, String nuevoDestinatario) {
        Map<String, Object> requestBody = Map.of(
                "idToken", idToken,
                "nuevoDestinatario", nuevoDestinatario
        );

        return restClient.post()
                .uri("/transferirAutorizacion")
                .body(requestBody)
                .retrieve()
                .body(Map.class);
    }
}
