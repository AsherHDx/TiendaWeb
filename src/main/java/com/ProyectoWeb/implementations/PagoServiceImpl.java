package com.ProyectoWeb.implementations;

import com.ProyectoWeb.entities.Pago;
import com.ProyectoWeb.entities.PagoDTO;
import com.ProyectoWeb.entities.Pedido;
import com.ProyectoWeb.repositories.PagoRepository;
import com.ProyectoWeb.repositories.PedidoRepository;
import com.ProyectoWeb.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PagoServiceImpl implements PagoService {
    @Autowired
    PagoRepository pagoRepository;
    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public Pago getById(Long id) {
        return pagoRepository.findById(id).orElse(null);
    }

}
