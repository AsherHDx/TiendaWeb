package com.ProyectoWeb.controllers;

import com.ProyectoWeb.entities.Pago;
import com.ProyectoWeb.entities.PagoDTO;
import com.ProyectoWeb.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiWeb/pago")
@CrossOrigin(origins = "*")
public class PagoController {
    @Autowired
    PagoService pagoService;

    @GetMapping("/{id}")
    public Pago obtenerUnPago(@PathVariable Long id){
        return pagoService.getById(id);
    }

}
