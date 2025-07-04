package com.ProyectoWeb.controllers;

import com.ProyectoWeb.entities.*;
import com.ProyectoWeb.repositories.ClienteRepository;
import com.ProyectoWeb.repositories.EmpleadoRepository;
import com.ProyectoWeb.services.EmailService;
import com.ProyectoWeb.services.PedidoService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/apiWeb/pedido")
@CrossOrigin(origins = "*")
public class PedidoController {
    @Autowired
    PedidoService pedidoService;
    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping
    public Pedido insertarPedido(@RequestBody PedidoDTO pedDTO) throws MessagingException {
        return pedidoService.insert(pedDTO);
    }

    @GetMapping("/{id}")
    public PedidoResponseDTO obtenerUnPedido(@PathVariable Long id){
        return pedidoService.getById(id);
    }

    @GetMapping
    public List<PedidoResponseDTO> obtenerPedidos(){
        return pedidoService.getAll();
    }

    //Se tiene que actualizar este mét0do para usar el PedidoDTO o lo que se necesite
    @PutMapping("/{id}")
    public Pedido ActualizarPedido(@PathVariable Long id, @RequestBody Pedido ped){
        return pedidoService.update(id,ped);
    }

    @DeleteMapping("/{id}")
    public void eliminarPedido(@PathVariable Long id){
        pedidoService.delete(id);
    }
}
