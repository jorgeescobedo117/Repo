package com.dependencias.InyeccDependecias_PC.implementaciones;

import com.dependencias.InyeccDependecias_PC.interfaces.Computadora;
import org.springframework.stereotype.Component;

@Component("Dell")
public class Dell implements Computadora {

    @Override
    public String ensamblar() {
        return "AMD Ryzen 5, 8GB RAM";
    }

    @Override
    public double precio() {
        return 1600.00;
    }
}
