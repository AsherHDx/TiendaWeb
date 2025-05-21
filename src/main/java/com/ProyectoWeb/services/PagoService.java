package com.ProyectoWeb.services;

import com.ProyectoWeb.entities.Pago;
import com.ProyectoWeb.entities.PagoDTO;

public interface PagoService {
    //No hay insert porque solo se puede epagar con el pedido, no después (a menos que se permita)
    public Pago getById(Long id);
    public Pago update(Long id, PagoDTO pagoDTO);
    public void delete(Long id);
}
