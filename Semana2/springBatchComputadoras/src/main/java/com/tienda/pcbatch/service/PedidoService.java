package com.tienda.pcbatch.service;

import com.tienda.pcbatch.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class PedidoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void processCSV(MultipartFile file) throws Exception {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) { // saltar header
                    firstLine = false;
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length != 8) {
                    throw new IllegalArgumentException("El CSV no tiene 8 columnas");
                }

                Pedido pedido = new Pedido();
                pedido.setPedidoId(Long.parseLong(fields[0].trim()));
                pedido.setCliente(fields[1].trim());
                pedido.setModeloPC(fields[2].trim());
                pedido.setProcesador(fields[3].trim());
                pedido.setRam(fields[4].trim());
                pedido.setAlmacenamiento(fields[5].trim());
                pedido.setPrecio(Double.parseDouble(fields[6].trim()));
                pedido.setEstado(fields[7].trim());

                // Insertar en DB
                jdbcTemplate.update(
                        "INSERT INTO pedidos (pedido_id, cliente, modelo_pc, procesador, ram, almacenamiento, precio, estado) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                        pedido.getPedidoId(),
                        pedido.getCliente(),
                        pedido.getModeloPC(),
                        pedido.getProcesador(),
                        pedido.getRam(),
                        pedido.getAlmacenamiento(),
                        pedido.getPrecio(),
                        pedido.getEstado()
                );
            }
        }
    }
}
