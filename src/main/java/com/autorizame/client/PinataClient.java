package com.autorizame.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class PinataClient {

    private final RestClient restClient;

    public PinataClient(@Value("${microservicios.pinata.url}") String pinataUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(pinataUrl)
                .build();
    }

    /**
     * Sube metadata JSON a IPFS mediante Pinata
     * @param datosPedido datos del pedido (idPedido, addressCliente, addressAutorizado, etc.)
     * @return respuesta con pinHash y gatewayUrl
    */
    @SuppressWarnings("unchecked")
    public Map<String, Object> subirMetadata(Map<String, Object> datosPedido) {
        System.out.println("[PINATA-CLIENT] Enviando metadata a IPFS: " + datosPedido);
        Map<String, Object> respuesta = restClient.post()
                .uri("/subirMetadata")
                .body(datosPedido)
                .retrieve()
                .body(Map.class);
        System.out.println("[PINATA-CLIENT] Respuesta recibida: " + respuesta);
        return respuesta;
    }

    /**
     * Recupera metadata de IPFS a partir de un CID
     * @param cid hash IPFS (CID)
     * @return datos almacenados en IPFS
    */
    @SuppressWarnings("unchecked")
    public Map<String, Object> recuperarMetadata(String cid) {
        System.out.println("[PINATA-CLIENT] Recuperando metadata con CID: " + cid);
        Map<String, Object> respuesta = restClient.get()
                .uri("/recuperarMetadata/{cid}", cid)
                .retrieve()
                .body(Map.class);
        System.out.println("[PINATA-CLIENT] Metadata recuperada: " + respuesta);
        return respuesta;
    }
}
