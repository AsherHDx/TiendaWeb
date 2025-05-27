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

    //no uso cascade porque no voy a instanciar Empleados desde pedido (ni modificarlos)
    @ManyToOne @JoinColumn(name = "idEmpleado")
    //@JsonBackReference("Empleado-Pedido") por el JsonIgnore en Empleado
    private Empleado empleado;

    @ManyToOne @JoinColumn(name = "idCliente")
    //@JsonBackReference("Cliente-Pedido") porque se uso el JsonIgnore en Cliente
    private Cliente cliente;

    //aquí sí se usa cascade porque con un save(pedido) voy a crear el pedido y tod0s sus detalles
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
