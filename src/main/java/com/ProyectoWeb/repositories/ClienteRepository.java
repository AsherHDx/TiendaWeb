package com.ProyectoWeb.repositories;

import com.ProyectoWeb.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
