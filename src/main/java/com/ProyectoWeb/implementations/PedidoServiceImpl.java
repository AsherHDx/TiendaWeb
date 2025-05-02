package com.ProyectoWeb.implementations;

import com.ProyectoWeb.entities.*;
import com.ProyectoWeb.repositories.ClienteRepository;
import com.ProyectoWeb.repositories.EmpleadoRepository;
import com.ProyectoWeb.repositories.PedidoRepository;
import com.ProyectoWeb.repositories.ProductoRepository;
import com.ProyectoWeb.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProductoRepository productoRepository;

    @Override
    public Pedido insert(PedidoDTO pedDTO) {
        Empleado emp = empleadoRepository.findById(pedDTO.getIdEmpleado())
                .orElseThrow(()->new RuntimeException("Empleado no encontrado"));
        Cliente cl = clienteRepository.findById(pedDTO.getIdCliente())
                .orElseThrow(()->new RuntimeException("Cliente no encontrado"));

        Pedido ped = new Pedido();
        ped.setFecha(LocalDate.now());
        ped.setEmpleado(emp);
        ped.setCliente(cl);

        for(DetallePedido det : pedDTO.getDetallePedidos()){
            Producto producto = productoRepository.findById(det.getProducto().getIdProducto())
                    .orElseThrow(()-> new RuntimeException("El producto no existe"));
            det.setProducto(producto);
            det.setSubtotal(det.calcularSubTotal());
            det.setPedido(ped);
        }

        ped.setDetalles(pedDTO.getDetallePedidos());
        ped.setTotal(ped.calcularTotal());
        ped.setPago(pedDTO.getPago());

        return pedidoRepository.save(ped);
    }

    @Override
    public PedidoResponseDTO getById(Long id) {
        Optional<Pedido> pedOpt = pedidoRepository.findById(id);
        if(pedOpt.isPresent()){
            Pedido ped = pedOpt.get();
            PedidoResponseDTO pedRespDTO = new PedidoResponseDTO();
            pedRespDTO.setIdPedido(ped.getIdPedido());
            pedRespDTO.setNombreCliente(ped.getCliente().getNombre());
            pedRespDTO.setNombreEmpleado(ped.getEmpleado().getNombre());
            pedRespDTO.setProductos(ped.getDetalles());
            pedRespDTO.setMetodoPago(ped.getPago().getMetodoPago());
            pedRespDTO.setFecha(ped.getFecha());
            pedRespDTO.setTotal(ped.getTotal());
            return pedRespDTO;
        }
        return null;
    }

    @Override
    public Pedido update(Long id, Pedido ped) {
        Optional<Pedido> existPedOpt = pedidoRepository.findById(id);
        if(existPedOpt.isPresent()){
            Pedido existPed = existPedOpt.get();
            existPed.setEmpleado(ped.getEmpleado());
            existPed.setCliente(ped.getCliente());
            existPed.setTotal(ped.getTotal());
            return pedidoRepository.save(existPed);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }
}
