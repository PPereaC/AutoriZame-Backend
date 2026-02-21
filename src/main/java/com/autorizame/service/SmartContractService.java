package com.autorizame.service;

import com.autorizame.client.SmartContractClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SmartContractService {

    private final SmartContractClient smartContractClient;

    public SmartContractService(SmartContractClient smartContractClient) {
        this.smartContractClient = smartContractClient;
    }

    /**
     * Mintea un NFT de autorización en la blockchain
    */
    public Map<String, Object> mintarAutorizacion(String destinatario, String personaAutorizada,
            Long referenciaExterna, Integer pin, String rutaDatos) {
        return smartContractClient.mintarAutorizacion(destinatario, personaAutorizada,
                referenciaExterna, pin, rutaDatos);
    }

    /**
     * Transfiere un token de autorización a otra dirección
    */
    public Map<String, Object> transferirAutorizacion(Long idToken, String nuevoDestinatario) {
        return smartContractClient.transferirAutorizacion(idToken, nuevoDestinatario);
    }
}
