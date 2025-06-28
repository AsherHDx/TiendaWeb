package com.ProyectoWeb.implementations;

import com.ProyectoWeb.entities.DetallePedido;
import com.ProyectoWeb.entities.Pedido;
import com.ProyectoWeb.services.EmailService;
import com.ProyectoWeb.services.PdfService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceimpl implements EmailService {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    PdfService pdfService;

    @Override
    public void enviarEmail(Pedido pedido) throws MessagingException {
        byte [] pdfBytes = pdfService.generarPdfPedido(pedido);

        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail,true);

        helper.setTo(pedido.getCliente().getCorreo());
        helper.setSubject("Tu compra en Toy Store");
        helper.setText("Agradecemos tu compra! A continuación se adjunta tu resumen de compra");
        /*SimpleMailMessage mail = new SimpleMailMessage();
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
        String text = textBuilder.toString();*/
        ByteArrayDataSource dataSource = new ByteArrayDataSource(pdfBytes, "application/pdf");
        helper.addAttachment("pedido.pdf", dataSource);
        //mail.setText(text);

        mailSender.send(mail);
    }
}
