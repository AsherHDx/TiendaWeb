package com.ProyectoWeb.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class PedidoDTO {
    private Long idEmpleado;
    private Long idCliente;
    private List<DetallePedidoDTO> detallePedidosDTO;
    private PagoDTO pagoDTO;
}
