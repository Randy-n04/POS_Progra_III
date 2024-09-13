package pos.presentation.cajero;

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
import pos.logic.Cajero;
import pos.logic.Cliente;
import pos.logic.Service;

import java.util.List;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        model.init(Service.instance().search(new Cajero()));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void search (Cajero filter) throws Exception {
        model.setFilter(filter);
        model.setMode(Application.MODE_CREATE);
        model.setCurrent(new Cajero());
        model.setList(Service.instance().search(model.getFilter()));
    }

    public void save(Cajero e)throws Exception {
        switch (model.getMode()) {
            case Application.MODE_CREATE:
                Service.instance().create(e);
                break;
            case Application.MODE_EDIT:
                Service.instance().update(e);
                break;
        }
        model.setFilter(new Cajero());
        search(model.getFilter());
    }

    public void edit(int row){
        Cajero e = model.getList().get(row);
        try{
            model.setMode(Application.MODE_EDIT);
            model.setCurrent(Service.instance().read(e));
        }catch(Exception ex){}
    }

    public void delete()throws Exception{
        Service.instance().delete(model.getCurrent());
        search(model.getFilter());
    }

    public void clear(){
        model.setMode(Application.MODE_CREATE);
        model.setCurrent(new Cajero());
    }

    public void report() throws Exception {
        if (model.getList().isEmpty()) {
            throw new Exception("No hay clientes disponibles para generar el reporte.");
        }

        String dest = "cajeros.pdf";
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        try (PdfWriter writer = new PdfWriter(dest); PdfDocument pdf = new PdfDocument(writer); Document document = new Document(pdf)) {
            document.setMargins(20, 20, 20, 20);

            Table header = new Table(1);
            header.setWidth(400);
            header.setHorizontalAlignment(HorizontalAlignment.CENTER);
            header.addCell(getCell(new Paragraph("Listado de Cajeros").setFont(font).setBold().setFontSize(20f), TextAlignment.CENTER, false));
            document.add(header);

            document.add(new Paragraph(""));
            document.add(new Paragraph(""));

            Color bkg = ColorConstants.LIGHT_GRAY;
            Color frg = ColorConstants.WHITE;
            Table body = new Table(2);
            body.setWidth(400);
            body.setHorizontalAlignment(HorizontalAlignment.CENTER);
            body.addCell(getCell(new Paragraph("ID").setBackgroundColor(bkg).setFontColor(frg), TextAlignment.CENTER, true));
            body.addCell(getCell(new Paragraph("Nombre").setBackgroundColor(bkg).setFontColor(frg), TextAlignment.CENTER, true));

            for (Cajero e : model.getList()) {
                body.addCell(getCell(new Paragraph(e.getId()), TextAlignment.CENTER, true));
                body.addCell(getCell(new Paragraph(e.getNombre()), TextAlignment.CENTER, true));
            }
            document.add(body);
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el reporte", e);
        }
    }


    private Cell getCell(Paragraph paragraph, TextAlignment alignment, boolean hasBorder) {
        Cell cell = new Cell().add(paragraph);
        cell.setPadding(0);
        cell.setTextAlignment(alignment);
        if (!hasBorder) cell.setBorder(Border.NO_BORDER);
        return cell;
    }

}
