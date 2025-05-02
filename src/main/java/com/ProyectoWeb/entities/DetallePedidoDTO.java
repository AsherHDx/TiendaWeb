package com.ProyectoWeb.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class DetallePedidoDTO {
    private Long idPedido;
    private Long idProducto;
    private int cantidad;
}
