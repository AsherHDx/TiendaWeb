package com.ProyectoWeb.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity @Table(name = "pago")
@Data @AllArgsConstructor @NoArgsConstructor
public class Pago {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPago")
    private Long idPago;

    @Column(name = "metodoPago", length = 20, nullable = false)
    private String metodoPago;

    @Column(name = "montoPagado", nullable = false)
    private double montoPagado;

    @Column(name = "fechaPago", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private LocalDate fechaPago;

    @OneToOne
    @JoinColumn(name = "idPedido", unique = true)
    @JsonBackReference("Pedido-Pago")
    private Pedido pedido;
}
