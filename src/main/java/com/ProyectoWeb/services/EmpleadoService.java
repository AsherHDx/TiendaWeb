package com.ProyectoWeb.services;

import com.ProyectoWeb.entities.Empleado;

public interface EmpleadoService {
    public Empleado insert(Empleado emp);
    public Empleado getById(Long id);
    public Empleado update(Long id, Empleado emp);
    public void delete(Long id);
}
