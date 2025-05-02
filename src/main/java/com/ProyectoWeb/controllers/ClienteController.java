package com.ProyectoWeb.controllers;

import com.ProyectoWeb.entities.Cliente;
import com.ProyectoWeb.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiWeb/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @PostMapping
    public Cliente insertarCliente(@RequestBody Cliente cl){
        return clienteService.insert(cl);
    }

    @GetMapping("/{id}")
    public Cliente obtenerUnCliente(@PathVariable Long id){
        return clienteService.getById(id);
    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente cl){
        return clienteService.update(id,cl);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Long id){
        clienteService.delete(id);
    }
}
