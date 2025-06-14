package com.ProyectoWeb.controllers;

import com.ProyectoWeb.entities.DetallePedido;
import com.ProyectoWeb.entities.DetallePedidoDTO;
import com.ProyectoWeb.entities.DetallePedidoResponseDTO;
import com.ProyectoWeb.services.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiWeb/detallePedido")
@CrossOrigin(origins = "*")
public class DetallePedidoController {
    @Autowired
    DetallePedidoService detallePedidoService;

    @GetMapping("/{id}")
    public DetallePedidoResponseDTO obtenerUnDetalle(@PathVariable Long id){
        return detallePedidoService.getById(id);
    }

}
