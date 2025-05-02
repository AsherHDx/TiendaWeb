package com.ProyectoWeb.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "detallepedido")
@Data @NoArgsConstructor @AllArgsConstructor
public class DetallePedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetalle")
    private Long idDetalle;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "subtotal", nullable = false)
    private double subtotal;

    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "idPedido")
    @JsonBackReference("Pedido-Detalle")
    private Pedido pedido;

    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "idProducto")
    @JsonBackReference("Producto-Detalle")
    private Producto producto;

    public double calcularSubTotal(){
        return this.producto.getPrecio()*this.cantidad;
    }
}
