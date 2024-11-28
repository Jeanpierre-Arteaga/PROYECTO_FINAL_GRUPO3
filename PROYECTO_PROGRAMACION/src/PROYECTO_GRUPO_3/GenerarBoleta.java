/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROYECTO_GRUPO_3;
  
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerarBoleta {
    public void crearBoleta(Contenedores y, Usuario usuario, GestorDeUsuarios gestor, int llenados, Producto_Reciclable[] detallesEnvases) {
        try {
            Rectangle tamaño = new Rectangle(215, 480);
            Document documento = new Document(tamaño, 10, 10, 10, 10);
            
            PdfWriter.getInstance(documento, new FileOutputStream("boleta_ticket_" + llenados + ".pdf"));
            documento.open();

            Font fuente_1 = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Font fuente_2 = new Font(Font.FontFamily.HELVETICA, 7, Font.BOLD); 
            Font fuente_3 = new Font(Font.FontFamily.HELVETICA, 7); 
            Font fuente_4 = new Font(Font.FontFamily.HELVETICA, 5); 

            Paragraph titulo = new Paragraph("RECICLA RIQUEZA", fuente_1);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            Paragraph subtitulo = new Paragraph("BANCO DE RECICLAJE CON RECOMPENSA MONETARIA", fuente_2);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(subtitulo);

            Image logo = Image.getInstance("C:\\Users\\jeana\\Downloads\\RECICLA-removebg-preview (1) (1).png");
            logo.scaleToFit(150, 100);
            logo.setAlignment(Element.ALIGN_CENTER);
            documento.add(logo);

            LineSeparator linea = new LineSeparator();
            linea.setLineWidth(0.5f); 
            linea.setLineColor(BaseColor.BLACK); 
            documento.add(linea);

            Paragraph boletaNum = new Paragraph("BOLETA ELECTRÓNICA N° " + llenados, fuente_2);
            boletaNum.setAlignment(Element.ALIGN_CENTER);
            documento.add(boletaNum);

            Paragraph distrito = new Paragraph("DISTRITO DE " + y.getUbicacion().toUpperCase(), fuente_2);
            distrito.setAlignment(Element.ALIGN_CENTER);
            distrito.setSpacingAfter(5);
            documento.add(distrito);
            documento.add(linea);
            
            documento.add(new Paragraph("\n")); 

            Paragraph nombreUsuario = new Paragraph(usuario.getNombres().toUpperCase() + " " + usuario.getApellidos().toUpperCase(), fuente_3);
            nombreUsuario.setAlignment(Element.ALIGN_CENTER);
            documento.add(nombreUsuario);

            Paragraph correoUsuario = new Paragraph(usuario.getEmail(), fuente_4);
            correoUsuario.setAlignment(Element.ALIGN_CENTER);
            documento.add(correoUsuario);

            String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            String hora = new SimpleDateFormat("HH:mm:ss").format(new Date());

            Paragraph fechaHora = new Paragraph();
            fechaHora.add(new Phrase("Fecha: ", fuente_2)); 
            fechaHora.add(new Phrase(" " + fecha, fuente_3)); 
            fechaHora.add(new Phrase("                     "));
            fechaHora.add(new Phrase("Hora: ", fuente_2));
            fechaHora.add(new Phrase(" " + hora, fuente_3));

            fechaHora.setAlignment(Element.ALIGN_CENTER);

            documento.add(fechaHora);
            documento.add(new Paragraph("\n")); 

            PdfPTable tabla_1 = new PdfPTable(4);
            tabla_1.setWidthPercentage(100);
            tabla_1.setWidths(new int[]{24, 45, 22, 30});

            Font fuente_5 = new Font(Font.FontFamily.HELVETICA, 5, Font.BOLD); 
            agregarTabla(tabla_1, "CANTIDAD", fuente_5, Element.ALIGN_CENTER);
            agregarTabla(tabla_1, "DESCRIPCIÓN", fuente_5, Element.ALIGN_CENTER);
            agregarTabla(tabla_1, "P. UNIT", fuente_5, Element.ALIGN_CENTER);
            agregarTabla(tabla_1, "MONTO TOTAL", fuente_5, Element.ALIGN_CENTER);

            Font fuente_6 = new Font(Font.FontFamily.HELVETICA, 5);
            double totalMonto = 0;
            String[] tipos = {"Vidrio", "Plástico", "Lata"};
            for (int i = 0; i < detallesEnvases.length; i++) {
                Producto_Reciclable producto = detallesEnvases[i];
                if (producto != null) {
                    agregarTabla(tabla_1, String.valueOf(producto.getCantidad()), fuente_6, Element.ALIGN_CENTER); 
                    agregarTabla(tabla_1, "Envases de " + tipos[i], fuente_6, Element.ALIGN_CENTER); 
                    agregarTabla(tabla_1,"S/. "+String.format("%.2f", producto.establecerValor()), fuente_6, Element.ALIGN_CENTER); 
                    double monto = producto.calcularValor();
                    totalMonto += monto; 
                    agregarTabla(tabla_1,"S/. "+String.format("%.2f", monto), fuente_6, Element.ALIGN_CENTER);
                }
            }

            documento.add(tabla_1);

            documento.add(new Paragraph("\n"));

            PdfPTable tabla_2 = new PdfPTable(2); 
            tabla_2.setWidthPercentage(100);
            tabla_2.setWidths(new int[]{50, 50}); 

            PdfPCell celda_1 = new PdfPCell(new Phrase("TOTAL", fuente_5));
            celda_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            celda_1.setFixedHeight(10);
            celda_1.setBorderWidth(0.5f);
            tabla_2.addCell(celda_1);
            PdfPCell celda_2 = new PdfPCell(new Phrase("S/. "+String.format("%.2f", totalMonto), fuente_4));
            celda_2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            celda_2.setFixedHeight(10); 
            celda_2.setBorderWidth(0.5f);
            tabla_2.addCell(celda_2);

            PdfPCell celda_3 = new PdfPCell(new Phrase("SALDO", fuente_5));
            celda_3.setHorizontalAlignment(Element.ALIGN_LEFT);
            celda_3.setFixedHeight(10); 
            celda_3.setBorderWidth(0.5f);
            tabla_2.addCell(celda_3);
            PdfPCell celda_4 = new PdfPCell(new Phrase("S/. "+String.format("%.2f", usuario.getSaldo()), fuente_4));
            celda_4.setHorizontalAlignment(Element.ALIGN_RIGHT);
            celda_4.setFixedHeight(10); 
            celda_4.setBorderWidth(0.5f);
            tabla_2.addCell(celda_4);

            documento.add(tabla_2);

            documento.add(new Paragraph("\n")); 

            Paragraph mensajeGestor = new Paragraph(
                "Usted ha sido atendido por el siguiente gestor, comuníquese ante cualquier consulta:",fuente_2);
            mensajeGestor.setAlignment(Element.ALIGN_CENTER);
            mensajeGestor.setSpacingAfter(5);
            documento.add(mensajeGestor);

            Paragraph datosGestor = new Paragraph(gestor.getNombres().toUpperCase() + " " + gestor.getApellidos().toUpperCase(),fuente_3);
            datosGestor.setAlignment(Element.ALIGN_CENTER); // Alineación a la izquierda
            datosGestor.setSpacingAfter(5);
            documento.add(datosGestor);
            
            Paragraph TelefonoCorreo = new Paragraph(gestor.getTelefono() + "  -  " + gestor.getEmail(),fuente_4);
            TelefonoCorreo.setAlignment(Element.ALIGN_CENTER);
            documento.add(TelefonoCorreo);
            documento.close();
            System.out.println("Boleta generada exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void agregarTabla(PdfPTable tabla, String texto, Font fuente, int alineacion) {
        PdfPCell celda = new PdfPCell(new Phrase(texto, fuente));
        celda.setHorizontalAlignment(alineacion);
        celda.setBorderWidth(0.5f);
        celda.setPadding(5);
        tabla.addCell(celda);
    }
}