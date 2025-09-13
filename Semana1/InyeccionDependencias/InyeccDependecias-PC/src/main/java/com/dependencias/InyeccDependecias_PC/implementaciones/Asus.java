package com.dependencias.InyeccDependecias_PC.implementaciones;

import com.dependencias.InyeccDependecias_PC.interfaces.Computadora;
import org.springframework.stereotype.Component;

@Component("Asus")
public class Asus  implements Computadora {
    @Override
    public String ensamblar() {
        return "Intel i7, 16GB RAM";
    }

    @Override
    public double precio() {
        return 1200.00;
    }
}
