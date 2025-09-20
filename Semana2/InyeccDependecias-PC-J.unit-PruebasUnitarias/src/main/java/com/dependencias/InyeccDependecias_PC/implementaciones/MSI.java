package com.dependencias.InyeccDependecias_PC.implementaciones;

import com.dependencias.InyeccDependecias_PC.interfaces.Computadora;
import org.springframework.stereotype.Component;

@Component("MSI")
public class MSI implements Computadora {
    @Override
    public String ensamblar() {
        return "Chip M1, 8GB RAM";
    }

    @Override
    public double precio() {
        return 2990.00;
    }
}
