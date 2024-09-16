package pos.presentation.historico;

import com.itextpdf.layout.properties.IListSymbolFactory;
import pos.Application;
import pos.presentation.AbstractModel;
import pos.logic.*;
import pos.presentation.AbstractModel;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    Factura filterFac;
    List<Factura> listFac;
    Factura currentFac;
    Linea filterLin;
    List<Linea> listLin;
    Linea currentLin;
    int mode;

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LISTFAC);
        firePropertyChange(CURRENTFAC);
        firePropertyChange(FILTERFAC);
        firePropertyChange(LISTLIN);
        firePropertyChange(CURRENTLIN);
        firePropertyChange(FILTERLIN);
        this.mode = Application.MODE_CREATE;
    }
    public Model(){
        this.listFac = new ArrayList<>();
        this.listLin = new ArrayList<>();
    }

    public void init(List<Factura> facLista, List<Linea> linLista){
        this.currentFac = new Factura();
        this.listFac = facLista;
        this.filterFac = new Factura();
        this.currentLin = new Linea();
        this.listLin = linLista;
        this.filterLin = new Linea();
        this.mode = Application.MODE_CREATE;
        firePropertyChange(LISTFAC);
        firePropertyChange(LISTLIN);
    }

    public List<Factura> getListFac() {
        return listFac;
    }

    public void setListFac(List<Factura> listFac) {
        this.listFac = listFac;
        firePropertyChange(LISTFAC);
    }

    public Factura getCurrentFac() {
        return currentFac;
    }

    public void setCurrentFac(Factura currentFac) {
        this.currentFac = currentFac;
        firePropertyChange(CURRENTFAC);
    }

    public Factura getFilterFac() {
        return filterFac;
    }

    public void setFilterFac(Factura filterFac) {
        this.filterFac = filterFac;
        firePropertyChange(FILTERFAC);
    }

    public List<Linea> getListLin() {
        return listLin;
    }

    public void setListLin(List<Linea> listLin) {
        this.listLin = listLin;
        firePropertyChange(LISTLIN);
    }

    public Linea getCurrentLin() {
        return currentLin;
    }

    public void setCurrentLin(Linea currentLin) {
        this.currentLin = currentLin;
        firePropertyChange(CURRENTLIN);
    }

    public Linea getFilterLin() {
        return filterLin;
    }

    public void setFilterLin(Linea filterLin) {
        this.filterLin = filterLin;
        firePropertyChange(FILTERLIN);
    }

    public int getMode(){
        return mode;
    }

    public void setMode(int mode){
        this.mode = mode;
    }

    public static final String LISTFAC = "listFac";
    public static final String LISTLIN = "listLin";
    public static final String CURRENTFAC = "currentFac";
    public static final String CURRENTLIN = "currentLin";
    public static final String FILTERFAC = "filterFac";
    public static final String FILTERLIN = "filterLin";

}
