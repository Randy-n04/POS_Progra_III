package pos.presentation.historico;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import pos.Application;
import pos.data.XmlPersister;
import pos.logic.*;
import java.util.List;

public class Controller {
    View view;
    Model model;
    public Controller(View view, Model model) {
        try{
            List<Factura> facturas = XmlPersister.instance().load().getFacturas();
            List<Linea> lineas = XmlPersister.instance().load().getLineas();
            if(facturas != null && lineas != null){
                model.init(facturas, lineas);
                if(!facturas.isEmpty()) {
                    model.setCurrentFac(facturas.get(0));
                } if(!lineas.isEmpty()) {
                    model.setCurrentLin(lineas.get(0));
                }
            } else{
                System.out.println("No se puede cargar los facturas o las lineas");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.view = view;
        this.model = model;
       // view.setController(this);
        //view.setModel(model);
    }

    public void search(Factura filter) throws Exception{
        model.setFilterFac(filter);
        model.setMode(Application.MODE_CREATE);
        model.setFilterFac(new Factura());
        model.setListFac(Service.instance().search(model.getFilterFac()));
    }

    public void search(Linea filter) throws Exception{
        model.setFilterLin(filter);
        model.setMode(Application.MODE_CREATE);
        //model.setFilterLin(new Factura());
        //model.setListLin(Service.instance().search(model.getFilterFac()));
    }

    public void init(){
        try{
            List<Factura> facturas = XmlPersister.instance().load().getFacturas();
            List<Linea> lineas = XmlPersister.instance().load().getLineas();
            model.init(facturas, lineas);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void editFac(Factura row){
        //Factura e = model.getListFac(Service.instance().read(row));
        try{
            model.setMode(Application.MODE_EDIT);
            //model.setCurrentFac(Service.instance().read(e));
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void editLin(Linea row){
        //Linea e = model.getListLin(Service.instance().read(row));
        try{
            model.setMode(Application.MODE_EDIT);
        //    model.setCurrentLin(Service.instance().read(e));
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void report() throws Exception {
        if (model.getListFac().isEmpty()) {
            throw new Exception("No hay facturas disponibles para generar el reporte.");
        }

        if (model.getListLin().isEmpty()) {
            throw new Exception("No hay lineas disponibles para generar el reporte.");
        }

        String dest = "historico.pdf";
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        try (PdfWriter writer = new PdfWriter(dest); PdfDocument pdf = new PdfDocument(writer); Document document = new Document(pdf)) {
            document.setMargins(20, 20, 20, 20);

            Table header = new Table(1);
            header.setWidth(400);
            header.setHorizontalAlignment(HorizontalAlignment.CENTER);
            header.addCell(getCell(new Paragraph("Listado de Clientes").setFont(font).setBold().setFontSize(20f), TextAlignment.CENTER, false, ColorConstants.WHITE));
            document.add(header);

            document.add(new Paragraph(""));
            document.add(new Paragraph(""));

            Color bkg = ColorConstants.BLUE;
            Color frg = ColorConstants.WHITE;
            Table body = new Table(7);
            body.setWidth(400);
            body.setHorizontalAlignment(HorizontalAlignment.CENTER);
            body.addCell(getCell(new Paragraph("Factura").setBackgroundColor(bkg).setFontColor(frg), TextAlignment.CENTER, true, bkg));
            body.addCell(getCell(new Paragraph("Producto").setBackgroundColor(bkg).setFontColor(frg), TextAlignment.CENTER, true, bkg));

            for (Factura e : model.getListFac()) {
                body.addCell(getCell(new Paragraph(e.getNumero()), TextAlignment.CENTER, true, frg));
                body.addCell(getCell(new Paragraph(String.valueOf(e.getCajero())), TextAlignment.CENTER, true, frg));
                body.addCell(getCell(new Paragraph(String.valueOf(e.getCliente())), TextAlignment.CENTER, true, frg));
                body.addCell(getCell(new Paragraph(String.valueOf(e.getFecha())), TextAlignment.CENTER, true, frg));
            }

            for (Linea e : model.getListLin()) {
                body.addCell(getCell(new Paragraph(e.getProducto().getCodigo()), TextAlignment.CENTER, true, frg));
                body.addCell(getCell(new Paragraph(e.getProducto().getDescripcion()), TextAlignment.CENTER, true, frg));
                body.addCell(getCell(new Paragraph(e.getProducto().getCategoria().toString()), TextAlignment.CENTER, true, frg));
                body.addCell(getCell(new Paragraph(e.getProducto().getUnidadMedida()), TextAlignment.CENTER, true, frg));
                body.addCell(getCell(new Paragraph(String.valueOf(e.getProducto().getPrecioUnitario())), TextAlignment.CENTER, true, frg));
            }
            document.add(body);
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el reporte", e);
        }
    }


    private Cell getCell(Paragraph paragraph, TextAlignment alignment, boolean hasBorder, Color bc) {
        Cell cell = new Cell().add(paragraph);
        cell.setPadding(0);
        cell.setTextAlignment(alignment);
        cell.setBackgroundColor(bc);
        if (!hasBorder) cell.setBorder(Border.NO_BORDER);
        return cell;
    }
}
