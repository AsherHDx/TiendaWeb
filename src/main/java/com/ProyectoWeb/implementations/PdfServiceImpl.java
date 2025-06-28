package com.ProyectoWeb.implementations;

import com.ProyectoWeb.entities.Pedido;
import com.ProyectoWeb.services.PdfService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

@Service
public class PdfServiceImpl implements PdfService {
    @Override
    public byte[] generarPdfPedido(Pedido p) {
        // step 1: creation of a document-object
        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // step 2:
            // PDF en memoria
            PdfWriter.getInstance(document, out);

            // step 3: we open the document
            document.open();

            // step 4: content
            Image logo = Image.getInstanceFromClasspath("logo.jpg");
            document.add(logo);
            
            Paragraph p1 = new Paragraph(new Chunk(
                    "Pedido: ",
                    FontFactory.getFont(FontFactory.HELVETICA, 20)));
            p1.add("The leading of this paragraph is calculated automagically. ");
            p1.add("The default leading is 1.5 times the fontsize. ");
            p1.add(new Chunk("You can add chunks "));
            document.add(p1);

            Paragraph p2 = new Paragraph(new Chunk(
                    "Pedido: ",
                    FontFactory.getFont(FontFactory.HELVETICA, 6)));
            p2.add("The leading of this paragraph is calculated automagically. ");
            p2.add("The default leading is 1.5 times the fontsize. ");
            p2.add(new Chunk("You can add chunks "));
            document.add(p2);

            // step 5: we close the document
            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
