package com.ProyectoWeb.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "producto")
@Data @AllArgsConstructor @NoArgsConstructor
public class Producto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private Long idProducto;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "stock", nullable = false)
    private int stock;

    //NOTA, este podría no ir ya que no me interesa en qué detalles está este producto, pero meh, ya se lo puse
    @OneToMany(mappedBy = "producto")
    @JsonManagedReference("Producto-Detalle")
    private List<DetallePedido> detalles;
}
