package com.ProyectoWeb.repositories;

import com.ProyectoWeb.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
}
