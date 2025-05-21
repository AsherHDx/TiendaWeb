package com.ProyectoWeb.implementations;

import com.ProyectoWeb.entities.Cliente;
import com.ProyectoWeb.entities.Producto;
import com.ProyectoWeb.repositories.ClienteRepository;
import com.ProyectoWeb.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public Cliente insert(Cliente p) {
        return clienteRepository.save(p);
    }

    @Override
    public Cliente getById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente update(Long id, Cliente cl) {
        Optional<Cliente> existClientOpt = clienteRepository.findById(id);
        if(existClientOpt.isPresent()){
            Cliente existClient = existClientOpt.get();
            existClient.setNombre(cl.getNombre());
            existClient.setCorreo(cl.getCorreo());
            existClient.setDireccion(cl.getDireccion());
            existClient.setTelefono(cl.getTelefono());
            return clienteRepository.save(existClient);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }
}
