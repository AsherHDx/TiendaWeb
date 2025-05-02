package com.ProyectoWeb.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class DetallePedidoResponseDTO {
    private Long idDetalle;
    private Long idPedido;
    private String nombreProducto;
    private int cantidad;
    private double subtotal;
}
