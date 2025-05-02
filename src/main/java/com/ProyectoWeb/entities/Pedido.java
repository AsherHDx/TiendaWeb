package com.ProyectoWeb.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity @Table(name = "pedido")
@Data @AllArgsConstructor @NoArgsConstructor
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedido")
    private Long idPedido;

    @Column(name = "fecha", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private LocalDate fecha;

    @Column(name = "total", nullable = false)
    private double total;

    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "idEmpleado")
    @JsonBackReference("Empleado-Pedido")
    private Empleado empleado;

    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "idCliente")
    @JsonBackReference("Cliente-Pedido")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    @JsonManagedReference("Pedido-Detalle")
    private List<DetallePedido> detalles;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido") //el optional=true es por default
    @JsonManagedReference("Pedido-Pago")
    private Pago pago;



    public double calcularTotal(){
        double total=0;
        for(DetallePedido det: this.detalles){
            total+=det.getSubtotal();
        }
        return total;
    }
}
