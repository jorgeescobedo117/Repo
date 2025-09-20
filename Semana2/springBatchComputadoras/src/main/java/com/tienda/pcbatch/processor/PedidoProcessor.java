package com.tienda.pcbatch.processor;

import com.tienda.pcbatch.model.Pedido;
import org.springframework.batch.item.ItemProcessor;

public class PedidoProcessor implements ItemProcessor<Pedido, Pedido> {
    @Override
    public Pedido process(Pedido pedido) throws Exception {
        if (pedido.getPrecio() <= 0) {
            System.out.println("Pedido rechazado (precio inválido): " + pedido);
            return null; // descartar
        }
        return pedido;
    }
}
