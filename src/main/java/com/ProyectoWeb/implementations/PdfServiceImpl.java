package com.ProyectoWeb.implementations;

import com.ProyectoWeb.entities.DetallePedido;
import com.ProyectoWeb.entities.Pedido;
import com.ProyectoWeb.services.PdfService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
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
        float width = document.getPageSize().getWidth();
        float height = document.getPageSize().getHeight();
        Font font16 = FontFactory.getFont(FontFactory.HELVETICA, 16);
        Font font18_bold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);

        try {
            // step 2:
            // PDF en memoria
            PdfWriter.getInstance(document, out);

            // step 3: we open the document
            document.open();

            // step 4: content
            Image logo = Image.getInstanceFromClasspath("logo.png");
            document.add(logo);

            document.add(Chunk.NEWLINE);
            Paragraph p1 = new Paragraph(new Chunk(
                    p.getCliente().getNombre() + ", usted ha realizado una compra en en Toy Store con fecha: " +
                    p.getFecha() + ". A continuación le facilitamos una tabla con los detalles de su compra.", font16));
            document.add(p1);
            document.add(Chunk.NEWLINE);

            float[] columnDefinitionSize = {33.33F, 33.33F, 33.33F};
            float pos = height / 2;
            PdfPTable table = null;
            PdfPCell cell = null;

            table = new PdfPTable(columnDefinitionSize);
            table.getDefaultCell().setBorder(1);
            table.setHorizontalAlignment(0);
            table.setTotalWidth(width - 72);
            table.setLockedWidth(true);

            cell = new PdfPCell(new Phrase("Resumen de Compra",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(columnDefinitionSize.length);
            table.addCell(cell);
            table.addCell(new Phrase("Producto", font18_bold));
            table.addCell(new Phrase("Cantidad", font18_bold));
            table.addCell(new Phrase("SubTotal", font18_bold));
            for(DetallePedido detalle : p.getDetalles()){
                table.addCell(new Phrase(detalle.getProducto().getNombre(), font16));
                table.addCell(new Phrase(Float.toString(detalle.getCantidad()), font16));
                table.addCell(new Phrase(Double.toString(detalle.getSubtotal()), font16));
            }
            cell = new PdfPCell(new Phrase("TOTAL:", font18_bold));
            cell.setColspan(columnDefinitionSize.length-1);
            cell.setBorder(Rectangle.TOP);
            table.addCell(cell);
            table.addCell(new Phrase(Double.toString(p.getTotal()), font18_bold));
            document.add(table);

            document.add(Chunk.NEWLINE);
            Paragraph p2 = new Paragraph(new Chunk(
                    "Le agradecemos su preferencia, ¡Vuelva pronto!",
                    FontFactory.getFont(FontFactory.HELVETICA, 20)));
            document.add(p2);

            // step 5: we close the document
            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
