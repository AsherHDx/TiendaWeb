package com.ProyectoWeb.services;

import com.ProyectoWeb.entities.DetallePedido;
import com.ProyectoWeb.entities.DetallePedidoDTO;
import com.ProyectoWeb.entities.DetallePedidoResponseDTO;

public interface DetallePedidoService {
    public DetallePedido insert(DetallePedidoDTO detPedDTO);
    public DetallePedidoResponseDTO getById(Long id);
    public DetallePedidoDTO update(Long id, DetallePedidoDTO detPedDTO);
    public void delete(Long id);
}
