package com.ProyectoWeb.controllers;

import com.ProyectoWeb.entities.Producto;
import com.ProyectoWeb.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiWeb/producto")
@CrossOrigin(origins = "*")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @PostMapping
    public Producto insertarProducto(@RequestBody Producto p){
        return productoService.insert(p);
    }

    @GetMapping("/{id}")
    public Producto obtenerUnProducto(@PathVariable Long id){
        return productoService.getById(id);
    }

    @GetMapping
    public List<Producto> obtenerProductos(){
        return productoService.getAll();
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto p){
        return productoService.update(id,p);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id){
        productoService.delete(id);
    }
}
