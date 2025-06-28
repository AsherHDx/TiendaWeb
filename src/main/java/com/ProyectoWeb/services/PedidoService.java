package com.ProyectoWeb.services;

import com.ProyectoWeb.entities.Pedido;
import com.ProyectoWeb.entities.PedidoDTO;
import com.ProyectoWeb.entities.PedidoResponseDTO;
import jakarta.mail.MessagingException;

import java.util.List;

public interface PedidoService {
    public Pedido insert(PedidoDTO pedDTO) throws MessagingException;
    public PedidoResponseDTO getById(Long id);
    public Pedido update(Long id, Pedido ped);
    public void delete(Long id);
    public List<PedidoResponseDTO> getAll();
}
