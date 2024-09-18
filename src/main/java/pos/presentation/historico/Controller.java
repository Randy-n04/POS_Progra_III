package pos.presentation.historico;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import pos.logic.Factura;
import pos.logic.Linea;

import java.time.LocalDate;
import java.util.List;

public class Controller {
    private Model model;

    public Controller(View historicoView, Model model) {
        this.model = model;
    }

    // Método para buscar por fecha
    public List<Factura> searchByFecha(LocalDate fecha) {
        // Implementa la lógica para buscar facturas por fecha
        return model.getFacturasByFecha(fecha);
    }

    public Factura searchByNumFactura(String numero) {
        // Implementa la lógica para buscar una factura por su número
        return model.getFacturaByNumero(numero);
    }

    public void report() throws Exception {
        if (model.getListFac().isEmpty() || model.getListLin().isEmpty()) {
            throw new Exception("No hay datos disponibles para generar el reporte.");
        }

        String dest = "historico.pdf";
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        try (PdfWriter writer = new PdfWriter(dest); PdfDocument pdf = new PdfDocument(writer); Document document = new Document(pdf)) {
            document.setMargins(20, 20, 20, 20);

            // Header
            Table header = new Table(1);
            header.setWidth(500);
            header.setHorizontalAlignment(HorizontalAlignment.CENTER);
            header.addCell(createCell("Reporte Histórico", font, TextAlignment.CENTER, true, ColorConstants.WHITE));
            document.add(header);

            document.add(new Paragraph("\n"));

            // Facturas Table
            Table facturasTable = new Table(4);
            facturasTable.setWidth(500);
            facturasTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
            facturasTable.addCell(createCell("Número", font, TextAlignment.CENTER, true, ColorConstants.LIGHT_GRAY));
            facturasTable.addCell(createCell("Fecha", font, TextAlignment.CENTER, true, ColorConstants.LIGHT_GRAY));
            facturasTable.addCell(createCell("Cajero", font, TextAlignment.CENTER, true, ColorConstants.LIGHT_GRAY));
            facturasTable.addCell(createCell("Cliente", font, TextAlignment.CENTER, true, ColorConstants.LIGHT_GRAY));

            for (Factura factura : model.getListFac()) {
                facturasTable.addCell(createCell(factura.getNumero(), font, TextAlignment.CENTER, false, ColorConstants.WHITE));
                facturasTable.addCell(createCell(factura.getFecha().toString(), font, TextAlignment.CENTER, false, ColorConstants.WHITE));
                facturasTable.addCell(createCell(factura.getCajero().getId(), font, TextAlignment.CENTER, false, ColorConstants.WHITE)); // Ajusta según toString() de Cajero
                facturasTable.addCell(createCell(factura.getCliente().getId(), font, TextAlignment.CENTER, false, ColorConstants.WHITE)); // Ajusta según toString() de Cliente
            }
            document.add(facturasTable);

            document.add(new Paragraph("\n"));

            // Lineas Table
            Table lineasTable = new Table(8);
            lineasTable.setWidth(500);
            lineasTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
            lineasTable.addCell(createCell("Código", font, TextAlignment.CENTER, true, ColorConstants.LIGHT_GRAY));
            lineasTable.addCell(createCell("Artículo", font, TextAlignment.CENTER, true, ColorConstants.LIGHT_GRAY));
            lineasTable.addCell(createCell("Categoría", font, TextAlignment.CENTER, true, ColorConstants.LIGHT_GRAY));
            lineasTable.addCell(createCell("Cantidad", font, TextAlignment.CENTER, true, ColorConstants.LIGHT_GRAY));
            lineasTable.addCell(createCell("Precio Unitario", font, TextAlignment.CENTER, true, ColorConstants.LIGHT_GRAY));
            lineasTable.addCell(createCell("Descuento", font, TextAlignment.CENTER, true, ColorConstants.LIGHT_GRAY));
            lineasTable.addCell(createCell("Neto", font, TextAlignment.CENTER, true, ColorConstants.LIGHT_GRAY));
            lineasTable.addCell(createCell("Importe", font, TextAlignment.CENTER, true, ColorConstants.LIGHT_GRAY));

            for (Linea linea : model.getListLin()) {
                lineasTable.addCell(createCell(linea.getProducto().getCodigo(), font, TextAlignment.CENTER, false, ColorConstants.WHITE));
                lineasTable.addCell(createCell(linea.getProducto().getDescripcion(), font, TextAlignment.CENTER, false, ColorConstants.WHITE));
                lineasTable.addCell(createCell(linea.getProducto().getCategoria().toString(), font, TextAlignment.CENTER, false, ColorConstants.WHITE));
                lineasTable.addCell(createCell(String.valueOf(linea.getCantidad()), font, TextAlignment.CENTER, false, ColorConstants.WHITE));
                lineasTable.addCell(createCell(String.valueOf(linea.getProducto().getPrecioUnitario()), font, TextAlignment.CENTER, false, ColorConstants.WHITE));
                lineasTable.addCell(createCell(String.valueOf(linea.getDescuento()), font, TextAlignment.CENTER, false, ColorConstants.WHITE));
                lineasTable.addCell(createCell(String.valueOf(linea.getNeto()), font, TextAlignment.CENTER, false, ColorConstants.WHITE));
                lineasTable.addCell(createCell(String.valueOf(linea.getImporte()), font, TextAlignment.CENTER, false, ColorConstants.WHITE));
            }
            document.add(lineasTable);

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el reporte", e);
        }
    }

    private Cell createCell(String text, PdfFont font, TextAlignment alignment, boolean isHeader, com.itextpdf.kernel.colors.Color background) {
        Cell cell = new Cell().add(new Paragraph(text).setFont(font));
        cell.setTextAlignment(alignment);
        cell.setBackgroundColor(background);
        if (isHeader) {
            cell.setBold();
        }
        return cell;
    }
}


