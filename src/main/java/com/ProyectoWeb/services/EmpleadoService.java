package com.ProyectoWeb.services;

import com.ProyectoWeb.entities.Empleado;

import java.util.List;

public interface EmpleadoService {
    public Empleado insert(Empleado emp);
    public Empleado getById(Long id);
    public Empleado update(Long id, Empleado emp);
    public void delete(Long id);
    public List<Empleado> getAll();
}
