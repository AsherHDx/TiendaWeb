package com.ProyectoWeb.implementations;

import com.ProyectoWeb.entities.ImagenProducto;
import com.ProyectoWeb.entities.Producto;
import com.ProyectoWeb.repositories.ProductoRepository;
import com.ProyectoWeb.services.ImageService;
import com.ProyectoWeb.services.ProductoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    ImageService imageService;

    @Override
    public Producto insert(String productoJSON, MultipartFile img) throws IOException {
        //Deserializo el JSON para obtener mi producto que envié en la petición
        ObjectMapper mapper = new ObjectMapper();
        Producto p = mapper.readValue(productoJSON,Producto.class);

        //Guardo la imagen en Cloudinary
        Map resultado = imageService.uploadImage(img);
        String url = (String) resultado.get("secure_url");
        String publicId = (String) resultado.get("public_id");

        /*//Guardo la imagen en la carpeta del proyecto
        String nombreImg = img.getOriginalFilename();
        Path ruta = Paths.get("assets/productos/" + nombreImg);
        //Path dirsPath = Paths.get("assets/productos/");
        //Files.createDirectories(dirsPath); //crea la ruta si no existe
        Files.copy(img.getInputStream(),ruta);*/

        //instancio un registro de la tabla ImagenProducto
        ImagenProducto imagenProducto = new ImagenProducto();
        imagenProducto.setFormato(img.getContentType());
        imagenProducto.setProducto(p);
        imagenProducto.setUrl(url);
        imagenProducto.setPublicId(publicId);
        imagenProducto.setNombreImg(img.getOriginalFilename());

        //le guardo/actualizo la info de la img para que, cuando se haga el save(p), se guarde mi ImagenProducto en la BD
        p.setImg(imagenProducto);

        return productoRepository.save(p);
    }

    @Override
    public Producto getById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override @Transactional
    public Producto update(Long id, String productoJSON) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Producto p = mapper.readValue(productoJSON,Producto.class);

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

    @Override @Transactional
    public void delete(Long id) throws IOException {
        Producto p = productoRepository.findById(id).orElseThrow(
                ()->new RuntimeException("El producto no existe"));
        if(p.getImg().getPublicId() != null)
            imageService.deleteImage(p.getImg().getPublicId());
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }
}
