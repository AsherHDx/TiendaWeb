package com.ProyectoWeb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity @Table(name = "empleado")
@Data @NoArgsConstructor @AllArgsConstructor
public class Empleado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmpleado")
    private Long idEmpleado;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "correo", length = 100, nullable = false)
    private String correo;

    @Column(name = "telefono", length = 15, nullable = false)
    private String telefono;

    @Column(name = "cargo", length = 30, nullable = false)
    private String cargo;

    @Column(name = "salario", nullable = false)
    private double salario;

    @OneToMany(mappedBy = "empleado")
    //@JsonManagedReference("Empleado-Pedido")
    @JsonIgnore
    private List<Pedido> listaPedidos;
}
