package com.ProyectoWeb.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class PedidoResponseDTO {
    private Long idPedido;
    private String nombreCliente;
    private String nombreEmpleado;
    private List<DetallePedidoResponseDTO> productos;
    private String metodoPago;
    private LocalDate fecha;
    private double total;
}
