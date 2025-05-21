package com.ProyectoWeb.services;

import com.ProyectoWeb.entities.DetallePedido;
import com.ProyectoWeb.entities.DetallePedidoDTO;
import com.ProyectoWeb.entities.DetallePedidoResponseDTO;

public interface DetallePedidoService {
    //No hay un insert porque no tiene sentido insertar solamente un detalle por separado de su pedido
    public DetallePedidoResponseDTO getById(Long id);
    public DetallePedidoDTO update(Long id, DetallePedidoDTO detPedDTO);
    public void delete(Long id);
}
