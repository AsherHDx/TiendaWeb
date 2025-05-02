package com.ProyectoWeb.repositories;

import com.ProyectoWeb.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
