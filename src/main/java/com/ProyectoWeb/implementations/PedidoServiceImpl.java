package com.ProyectoWeb.implementations;

import com.ProyectoWeb.entities.*;
import com.ProyectoWeb.repositories.ClienteRepository;
import com.ProyectoWeb.repositories.EmpleadoRepository;
import com.ProyectoWeb.repositories.PedidoRepository;
import com.ProyectoWeb.repositories.ProductoRepository;
import com.ProyectoWeb.services.EmailService;
import com.ProyectoWeb.services.PedidoService;
import jakarta.mail.MessagingException;
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
    public Pedido insert(PedidoDTO pedDTO) throws MessagingException {
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
            //verifico que haya stock suficiente, si lo hay, modifico el stock de producto
            if(detalleDTO.getCantidad() < producto.getStock()){
                producto.setStock(producto.getStock()-detalleDTO.getCantidad());
                productoRepository.save(producto);
            }else{
                throw new RuntimeException("No hay sufuciente stock del producto"
                        + producto.getNombre() + "para surtir el pedido");
            }
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setSubtotal(detalle.calcularSubTotal());
            //se añade el detalle hecho a la lista
            detalles.add(detalle);
        }

        ped.setDetalles(detalles);//Se añade la lista de detalles al pedido
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
        //busco el pedido por ID en la BD
        Optional<Pedido> pedOpt = pedidoRepository.findById(id);
        if(pedOpt.isPresent()){
            Pedido ped = pedOpt.get();
            //hago el DTO a partir del obtenido
            PedidoResponseDTO pedRespDTO = new PedidoResponseDTO();
            pedRespDTO.setIdPedido(ped.getIdPedido());
            pedRespDTO.setNombreCliente(ped.getCliente().getNombre());
            pedRespDTO.setNombreEmpleado(ped.getEmpleado().getNombre());

            //Se convierte la lista de DetallePedido en una lista de DTO's
            List<DetallePedidoResponseDTO> lstDetDTO = mapFromPedidoToResponseDTO(ped);
            pedRespDTO.setProductos(lstDetDTO);

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

    @Override
    public List<PedidoResponseDTO> getAll() {
        //busco todos mis pedidos
        List<Pedido> lstPedido = pedidoRepository.findAll();
        //convierto cada pedido en un responseDTO
        List<PedidoResponseDTO> lstPedidoDTO = new ArrayList<>();
        for(Pedido ped: lstPedido){
            PedidoResponseDTO pedRespDTO = new PedidoResponseDTO();
            pedRespDTO.setIdPedido(ped.getIdPedido());
            pedRespDTO.setNombreCliente(ped.getCliente().getNombre());
            pedRespDTO.setNombreEmpleado(ped.getEmpleado().getNombre());

            //Se convierte la lista de DetallePedido en una lista de DTO's
            List<DetallePedidoResponseDTO> lstDetDTO = mapFromPedidoToResponseDTO(ped);
            pedRespDTO.setProductos(lstDetDTO);

            pedRespDTO.setMetodoPago(ped.getPago().getMetodoPago());
            pedRespDTO.setFecha(ped.getFecha());
            pedRespDTO.setTotal(ped.getTotal());

            lstPedidoDTO.add(pedRespDTO);
        }
        return lstPedidoDTO;
    }



    private static List<DetallePedidoResponseDTO> mapFromPedidoToResponseDTO(Pedido ped) {
        List<DetallePedidoResponseDTO> lstDetDTO = new ArrayList<>();
        for(DetallePedido detPed : ped.getDetalles()){
            DetallePedidoResponseDTO detRespDTO = new DetallePedidoResponseDTO();
            detRespDTO.setIdDetalle(detPed.getIdDetalle());
            detRespDTO.setIdPedido(detPed.getPedido().getIdPedido());
            detRespDTO.setNombreProducto(detPed.getProducto().getNombre());
            detRespDTO.setCantidad(detPed.getCantidad());
            detRespDTO.setSubtotal(detPed.getSubtotal());
            lstDetDTO.add(detRespDTO);
        }
        return lstDetDTO;
    }
}
