package com.ProyectoWeb.controllers;

import com.ProyectoWeb.entities.Empleado;
import com.ProyectoWeb.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiWeb/empleado")
@CrossOrigin(origins = "*")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @PostMapping
    public Empleado insertarEmpleado(@RequestBody Empleado emp){
        return empleadoService.insert(emp);
    }

    @GetMapping("/{id}")
    public Empleado obtenerUnEmpleado(@PathVariable Long id){
        return empleadoService.getById(id);
    }

    @PutMapping("/{id}")
    public Empleado actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado emp){
        return empleadoService.update(id,emp);
    }

    @DeleteMapping("/{id}")
    public void eliminarEmpleado(@PathVariable Long id){
        empleadoService.delete(id);
    }
}
