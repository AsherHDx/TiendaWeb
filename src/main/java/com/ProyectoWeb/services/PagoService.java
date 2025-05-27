package com.ProyectoWeb.services;

import com.ProyectoWeb.entities.Pago;
import com.ProyectoWeb.entities.PagoDTO;

public interface PagoService {
    //No hay insert porque solo se puede epagar con el pedido, no después (a menos que se permita)
    //algo similar con los demás
    public Pago getById(Long id);
}
