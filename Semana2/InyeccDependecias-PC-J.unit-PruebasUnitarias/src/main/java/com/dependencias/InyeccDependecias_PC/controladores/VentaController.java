package com.dependencias.InyeccDependecias_PC.controladores;

import com.dependencias.InyeccDependecias_PC.interfaces.Computadora;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    private ApplicationContext context;

    @GetMapping("/{marca}")
    public String venta(@PathVariable String marca) {
        Computadora computadora = (Computadora) context.getBean(marca);
        return computadora.ensamblar() + " | Precio: $" + computadora.precio();
    }
}

