package com.ProyectoWeb.services;

import com.ProyectoWeb.entities.DetallePedido;
import com.ProyectoWeb.entities.DetallePedidoDTO;
import com.ProyectoWeb.entities.DetallePedidoResponseDTO;

public interface DetallePedidoService {
    //No hay un insert, delete y update porque no tiene sentido insertar solamente
    // un detalle por separado de su pedido, lo mismo aplica con los demás
    public DetallePedidoResponseDTO getById(Long id);
}
