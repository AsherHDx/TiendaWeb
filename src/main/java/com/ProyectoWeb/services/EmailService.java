package com.ProyectoWeb.services;

import com.ProyectoWeb.entities.Pedido;

public interface EmailService {
    public void enviarEmail(Pedido pedido);
}
