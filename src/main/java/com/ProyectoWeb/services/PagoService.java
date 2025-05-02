package com.ProyectoWeb.services;

import com.ProyectoWeb.entities.Pago;
import com.ProyectoWeb.entities.PagoDTO;

public interface PagoService {
    public Pago insert(PagoDTO pagoDTO);
    public Pago getById(Long id);
    public Pago update(Long id, PagoDTO pagoDTO);
    public void delete(Long id);
}
