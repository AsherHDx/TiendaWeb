package com.ProyectoWeb.implementations;

import com.ProyectoWeb.entities.Producto;
import com.ProyectoWeb.repositories.ProductoRepository;
import com.ProyectoWeb.services.ProductoService;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    @Override
    public Producto insert(Producto p) {
        return productoRepository.save(p);
    }

    @Override
    public Producto getById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto update(Long id, Producto p) {
        Optional<Producto> existProdOpt = productoRepository.findById(id);
        if(existProdOpt.isPresent()){
            Producto existProd = existProdOpt.get();
            existProd.setNombre(p.getNombre());
            existProd.setDescripcion(p.getDescripcion());
            existProd.setPrecio(p.getPrecio());
            existProd.setStock(p.getStock());
            return productoRepository.save(existProd);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        productoRepository.deleteById(id);
    }
}
