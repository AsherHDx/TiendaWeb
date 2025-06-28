package com.ProyectoWeb.services;

import com.ProyectoWeb.entities.Pedido;

public interface PdfService {
    public byte[] generarPdfPedido(Pedido p);
}
