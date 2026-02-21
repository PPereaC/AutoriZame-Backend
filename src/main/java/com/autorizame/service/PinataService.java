package com.autorizame.service;

import com.autorizame.client.PinataClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PinataService {

    private final PinataClient pinataClient;

    public PinataService(PinataClient pinataClient) {
        this.pinataClient = pinataClient;
    }

    /**
     * Sube metadata de pedido a IPFS y devuelve el hash
    */
    public Map<String, Object> subirMetadataPedido(Map<String, Object> datosPedido) {
        return pinataClient.subirMetadata(datosPedido);
    }

    /**
     * Recupera metadata de IPFS usando el CID
    */
    public Map<String, Object> recuperarMetadata(String cid) {
        return pinataClient.recuperarMetadata(cid);
    }
}
