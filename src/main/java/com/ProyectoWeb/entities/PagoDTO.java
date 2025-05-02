package com.ProyectoWeb.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class PagoDTO {
    private Long idPedido;
    private String metodoPago;
    private double montoPagado;
    private LocalDate fechaPago;
}
