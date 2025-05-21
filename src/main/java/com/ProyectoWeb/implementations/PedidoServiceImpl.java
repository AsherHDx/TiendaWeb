package com.ProyectoWeb.implementations;

import com.ProyectoWeb.entities.*;
import com.ProyectoWeb.repositories.ClienteRepository;
import com.ProyectoWeb.repositories.EmpleadoRepository;
import com.ProyectoWeb.repositories.PedidoRepository;
import com.ProyectoWeb.repositories.ProductoRepository;
import com.ProyectoWeb.services.EmailService;
import com.ProyectoWeb.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    EmailService emailService;

    @Override
    public Pedido insert(PedidoDTO pedDTO) {
        //busco si existe el empleado y el cliente relacionados con el pedido en la BD
        Empleado emp = empleadoRepository.findById(pedDTO.getIdEmpleado())
                .orElseThrow(()->new RuntimeException("Empleado no encontrado"));
        Cliente cl = clienteRepository.findById(pedDTO.getIdCliente())
                .orElseThrow(()->new RuntimeException("Cliente no encontrado"));

        //creo un Pedido a partir del DTO y calculo/proceso/coloco/busco los datos que faltan
        Pedido ped = new Pedido();
        ped.setEmpleado(emp);
        ped.setCliente(cl);
        ped.setFecha(LocalDate.now());

        //Cada pedido con N productos me va agenerar N cantidad de detalles, aquí hago cada detalle
        List<DetallePedido> detalles = new ArrayList<>();
        for(DetallePedidoDTO detalleDTO : pedDTO.getDetallePedidosDTO()){
            DetallePedido detalle = new DetallePedido();
            Producto producto = productoRepository.findById(detalleDTO.getIdProducto())
                    .orElseThrow(()-> new RuntimeException("El producto no existe"));
            detalle.setProducto(producto);
            detalle.setPedido(ped);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setSubtotal(detalle.calcularSubTotal());
            detalles.add(detalle);
        }

        ped.setDetalles(detalles);
        ped.setTotal(ped.calcularTotal());

        //ahora construyo el pago partiendo del DTO (que solo me da el String "mét0do de pago")
        Pago pago = new Pago();
        pago.setPedido(ped);
        pago.setMetodoPago(pedDTO.getPagoDTO().getMetodoPago());
        pago.setMontoPagado(ped.getTotal());
        pago.setFechaPago(LocalDate.now());

        ped.setPago(pago);

        //se utiliza el EmailService para enviar un email al cliente con los detalles de su pedido
        emailService.enviarEmail(ped);

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
