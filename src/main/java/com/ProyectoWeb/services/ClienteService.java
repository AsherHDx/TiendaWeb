package com.ProyectoWeb.services;


import com.ProyectoWeb.entities.Cliente;

public interface ClienteService {
    public Cliente insert(Cliente p);
    public Cliente getById(Long id);
    public Cliente update(Long id, Cliente p);
    public void delete(Long id);
}
