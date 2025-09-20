package com.tienda.pcbatch.controller;

import com.tienda.pcbatch.model.Pedido;
import com.tienda.pcbatch.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("File") MultipartFile File) {
        try {
            pedidoService.processCSV(File);
            return ResponseEntity.ok("Archivo procesado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar CSV: " + e.getMessage());
        }
    }
}
