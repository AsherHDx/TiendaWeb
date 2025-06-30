package com.ProyectoWeb.services;

import com.ProyectoWeb.entities.Producto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductoService {
    public Producto insert(String productoJSON, MultipartFile img) throws IOException;
    public Producto getById(Long id);
    public Producto update(Long id, String productoJSON) throws IOException;
    public void delete(Long id) throws IOException;
    public List<Producto> getAll();
}
