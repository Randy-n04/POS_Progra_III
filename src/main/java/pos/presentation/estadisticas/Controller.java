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
        view.setModel(model);

    }

    public void actualizarData(){
        Rango r = model.getRango();
        int colCount = (r.getAnnoHasta().getYear() - r.getAnnoDesde().getYear()) * 12 + r.getMesHasta().getMonth() - r.getMesDesde().getMonth() + 1;
        int rowCount = model.getcategorias().size();
        String[] cols = new String[colCount];
        for (int i = 0; i < colCount; i++) {
            cols[i] = model.getcategorias().get(i).getNombre();
        }

    }

    public void add(Categoria c){

    }

    public void addAll(List<Categoria> c){

    }

    public void remove(Categoria c){

    }
    public void removeAll(List<Categoria> c){

    }


//
//    public void search(Cliente filter) throws  Exception{
//        model.setFilter(filter);
//        model.setMode(Application.MODE_CREATE);
//        model.setCurrent(new Cliente());
//        model.setList(Service.instance().search(model.getFilter()));
//    }
//
//    public void save(Cliente e) throws  Exception{
//        switch (model.getMode()) {
//            case Application.MODE_CREATE:
//                Service.instance().create(e);
//                break;
//            case Application.MODE_EDIT:
//                Service.instance().update(e);
//                break;
//        }
//        model.setFilter(new Cliente());
//        search(model.getFilter());
//    }
//
//    public void edit(int row){
//        Cliente e = model.getList().get(row);
//        try {
//            model.setMode(Application.MODE_EDIT);
//            model.setCurrent(Service.instance().read(e));
//        } catch (Exception ex) {}
//    }
//
//    public void delete() throws Exception {
//        Service.instance().delete(model.getCurrent());
//        search(model.getFilter());
//    }
//
//    public void clear() {
//        model.setMode(Application.MODE_CREATE);
//        model.setCurrent(new Cliente());
//    }

}
