package com.ProyectoWeb.services;

import com.ProyectoWeb.entities.Producto;

public interface ProductoService {
    public Producto insert(Producto p);
    public Producto getById(Long id);
    public Producto update(Long id, Producto p);
    public void delete(Long id);
}
