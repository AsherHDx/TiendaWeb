package com.ProyectoWeb.services;

import com.ProyectoWeb.entities.Pedido;
import jakarta.mail.MessagingException;

public interface EmailService {
    public void enviarEmail(Pedido pedido) throws MessagingException;
}
