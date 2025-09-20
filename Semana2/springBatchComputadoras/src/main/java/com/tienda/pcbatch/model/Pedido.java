package com.tienda.pcbatch.model;

public class Pedido {
    private Long pedidoId;
    private String cliente;
    private String modeloPC;
    private String procesador;
    private String ram;
    private String almacenamiento;
    private double precio;
    private String estado;

    public Long getPedidoId() { return pedidoId; }
    public void setPedidoId(Long pedidoId) { this.pedidoId = pedidoId; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getModeloPC() { return modeloPC; }
    public void setModeloPC(String modeloPC) { this.modeloPC = modeloPC; }

    public String getProcesador() { return procesador; }
    public void setProcesador(String procesador) { this.procesador = procesador; }

    public String getRam() { return ram; }
    public void setRam(String ram) { this.ram = ram; }

    public String getAlmacenamiento() { return almacenamiento; }
    public void setAlmacenamiento(String almacenamiento) { this.almacenamiento = almacenamiento; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Pedido{" +
                "pedidoId=" + pedidoId +
                ", cliente='" + cliente + '\'' +
                ", modeloPC='" + modeloPC + '\'' +
                ", procesador='" + procesador + '\'' +
                ", ram='" + ram + '\'' +
                ", almacenamiento='" + almacenamiento + '\'' +
                ", precio=" + precio +
                ", estado='" + estado + '\'' +
                '}';
    }
}
