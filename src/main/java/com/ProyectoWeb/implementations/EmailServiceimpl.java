package com.ProyectoWeb.implementations;

import com.ProyectoWeb.entities.DetallePedido;
import com.ProyectoWeb.entities.Pedido;
import com.ProyectoWeb.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceimpl implements EmailService {
    @Autowired
    JavaMailSender mailSender;

    @Override
    public void enviarEmail(Pedido pedido) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(pedido.getCliente().getCorreo());
        mail.setSubject("Resumen de Pedido");

        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append("Resumen de su pedido:\n");
        for(DetallePedido detPed : pedido.getDetalles()){
            textBuilder.append(detPed.getProducto().getNombre()).
                    append(", Cant: ").append(detPed.getCantidad()).
                    append(", SubTotal: ").append(detPed.getSubtotal()).
                    append("\n");
        }
        textBuilder.append("Total: ").append(pedido.getTotal());
        String text = textBuilder.toString();
        mail.setText(text);

        mailSender.send(mail);
    }
}
