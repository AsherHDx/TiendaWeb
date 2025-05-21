package com.ProyectoWeb.implementations;

import com.ProyectoWeb.entities.*;
import com.ProyectoWeb.repositories.DetallePedidoRepository;
import com.ProyectoWeb.repositories.PedidoRepository;
import com.ProyectoWeb.repositories.ProductoRepository;
import com.ProyectoWeb.services.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {
    @Autowired
    DetallePedidoRepository detallePedidoRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ProductoRepository productoRepository;


    @Override
    public DetallePedidoResponseDTO getById(Long id) {
        Optional<DetallePedido> detPedOpt = detallePedidoRepository.findById(id);
        if(detPedOpt.isPresent()){
            DetallePedido detPed = detPedOpt.get();
            DetallePedidoResponseDTO detPedRespDTO = new DetallePedidoResponseDTO();
            detPedRespDTO.setIdDetalle(detPed.getIdDetalle());
            detPedRespDTO.setIdPedido(detPed.getPedido().getIdPedido());
            detPedRespDTO.setNombreProducto(detPed.getProducto().getNombre());
            detPedRespDTO.setCantidad(detPed.getCantidad());
            detPedRespDTO.setSubtotal(detPed.getSubtotal());
            return detPedRespDTO;
        }
        return null;
    }

    //FALTA IMPLEMENTAR ESTA WEA
    @Override
    public DetallePedidoDTO update(Long id, DetallePedidoDTO detPedDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {
        detallePedidoRepository.deleteById(id);
    }
}
