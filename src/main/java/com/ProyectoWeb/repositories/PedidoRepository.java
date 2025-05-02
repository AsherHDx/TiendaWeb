package com.ProyectoWeb.repositories;

import com.ProyectoWeb.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
}
