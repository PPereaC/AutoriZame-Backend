package com.autorizame.controllers;

import com.autorizame.service.PinataService;
import com.autorizame.service.SmartContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/blockchain")
public class BlockchainController {

    private final PinataService pinataService;
    private final SmartContractService smartContractService;

    public BlockchainController(PinataService pinataService, SmartContractService smartContractService) {
        this.pinataService = pinataService;
        this.smartContractService = smartContractService;
    }

    @PostMapping("/ipfs/subir")
    public ResponseEntity<Map<String, Object>> subirMetadata(@RequestBody Map<String, Object> datosPedido) {
        Map<String, Object> respuesta = pinataService.subirMetadataPedido(datosPedido);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/ipfs/recuperar/{cid}")
    public ResponseEntity<Map<String, Object>> recuperarMetadata(@PathVariable String cid) {
        Map<String, Object> respuesta = pinataService.recuperarMetadata(cid);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/nft/mintar")
    public ResponseEntity<Map<String, Object>> mintarAutorizacion(@RequestBody Map<String, Object> request) {
        String destinatario = (String) request.get("destinatario");
        String personaAutorizada = (String) request.get("personaAutorizada");
        Long referenciaExterna = ((Number) request.get("referenciaExterna")).longValue();
        Integer pin = ((Number) request.get("pin")).intValue();
        String rutaDatos = (String) request.get("rutaDatos");

        Map<String, Object> respuesta = smartContractService.mintarAutorizacion(
                destinatario, personaAutorizada, referenciaExterna, pin, rutaDatos);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/nft/transferir")
    public ResponseEntity<Map<String, Object>> transferirAutorizacion(@RequestBody Map<String, Object> request) {
        Long idToken = ((Number) request.get("idToken")).longValue();
        String nuevoDestinatario = (String) request.get("nuevoDestinatario");

        Map<String, Object> respuesta = smartContractService.transferirAutorizacion(idToken, nuevoDestinatario);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
