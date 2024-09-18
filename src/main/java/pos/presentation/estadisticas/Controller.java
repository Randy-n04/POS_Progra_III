package pos.presentation.estadisticas;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import org.glassfish.jaxb.core.api.impl.NameConverter;
import pos.Application;
import pos.logic.Categoria;
import pos.logic.Cliente;
import pos.logic.Service;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.io.font.constants.StandardFonts;

import java.util.List;

public class Controller {

    View view;
    Model model;

    public Controller(View view, Model model) {
        model.init(Service.instance().search(new Categoria()));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(this.model);

    }

    public void actualizarData(){
        Rango r = model.getRango();
        int colCount = (r.getAnnoHasta() - r.getAnnoDesde()) * 12 + r.getMesHasta() - r.getMesDesde() + 1;
        int rowCount = model.getcategorias().size();
        String[] cols = new String[colCount];
        for (int i = 0; i < colCount; i++) {
            cols[i] = model.getcategorias().get(i).getNombre();
        }

    }
    //TODO llenar metodos vacios
    public void add(Categoria c){

    }

    public void addAll(List<Categoria> c){

    }

    public void remove(Categoria c){

    }
    public void removeAll(List<Categoria> c){

    }




}