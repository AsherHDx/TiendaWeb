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

    @PostMapping
    public DetallePedido insertarDetalle(@RequestBody DetallePedidoDTO detPedDTO){
        return detallePedidoService.insert(detPedDTO);
    }

    @GetMapping("/{id}")
    public DetallePedidoResponseDTO obtenerUnDetalle(@PathVariable Long id){
        return detallePedidoService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarDetalle(@PathVariable Long id){
        detallePedidoService.delete(id);
    }
}
