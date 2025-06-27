package com.ProyectoWeb.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "ImagenProducto")
@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class ImagenProducto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idImagenProducto")
    private Long idImagenProducto;

    @Column(name = "nombreImg", length = 35, nullable = false)
    private String nombreImg;

    @Column(name = "uri", length = 100, nullable = false)
    private String uri;

    @Column(name = "formato", length = 10, nullable = false)
    private String formato;

    @OneToOne
    @JoinColumn(name = "idProducto", unique = true)
    @JsonBackReference("ImagenProducto")
    private Producto producto;
}
