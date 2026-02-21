package com.autorizame.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class PinataClient {

    private final RestClient restClient;

    public PinataClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:3001")
                .build();
    }

    /**
     * Sube metadata JSON a IPFS mediante Pinata
     * @param datosPedido datos del pedido (idPedido, addressCliente, addressAutorizado, etc.)
     * @return respuesta con pinHash y gatewayUrl
    */
    @SuppressWarnings("unchecked")
    public Map<String, Object> subirMetadata(Map<String, Object> datosPedido) {
        return restClient.post()
                .uri("/subirMetadata")
                .body(datosPedido)
                .retrieve()
                .body(Map.class);
    }

    /**
     * Recupera metadata de IPFS a partir de un CID
     * @param cid hash IPFS (CID)
     * @return datos almacenados en IPFS
    */
    @SuppressWarnings("unchecked")
    public Map<String, Object> recuperarMetadata(String cid) {
        return restClient.get()
                .uri("/recuperarMetadata/{cid}", cid)
                .retrieve()
                .body(Map.class);
    }
}
