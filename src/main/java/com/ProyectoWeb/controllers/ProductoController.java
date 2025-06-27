package com.ProyectoWeb.controllers;

import com.ProyectoWeb.entities.Producto;
import com.ProyectoWeb.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/apiWeb/producto")
@CrossOrigin(origins = "*")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @PostMapping
    public Producto insertarProducto(@RequestParam("producto") String productoJSON,
                                     @RequestParam("archivo") MultipartFile img) throws IOException {
        return productoService.insert(productoJSON, img);
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
    public Producto actualizarProducto(@PathVariable Long id,
                                       @RequestParam("producto") String productoJSON,
                                       @RequestParam("archivo") MultipartFile img) throws IOException {
        return productoService.update(id,productoJSON,img);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) throws IOException {
        productoService.delete(id);
    }
}
