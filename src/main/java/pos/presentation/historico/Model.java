package pos.presentation.historico;

import pos.logic.Factura;
import pos.logic.Linea;
import pos.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    private Factura filterFac;
    private List<Factura> listFac;
    private Factura currentFac;
    private Linea filterLin;
    private List<Linea> listLin;
    private Linea currentLin;
    private int mode;

    public Model() {
        this.listFac = new ArrayList<>();
        this.listLin = new ArrayList<>();
        this.mode = 0; // Default mode, adjust as needed
    }

    public void init(List<Factura> facLista, List<Linea> linLista) {
        this.currentFac = new Factura();
        this.listFac = facLista;
        this.filterFac = new Factura();
        this.currentLin = new Linea();
        this.listLin = linLista;
        this.filterLin = new Linea();
        firePropertyChange(LISTFAC);
        firePropertyChange(LISTLIN);
    }

    public void searchByFecha(LocalDate fecha) throws Exception {
        List<Factura> filteredList = new ArrayList<>();
        for (Factura f : listFac) {
            if (f.getFecha().equals(fecha)) {
                filteredList.add(f);
            }
        }
        setListFac(filteredList);
    }

    public void searchByNumFactura(String numFactura) throws Exception {
        List<Factura> filteredList = new ArrayList<>();
        for (Factura f : listFac) {
            if (f.getNumero().equals(numFactura)) {
                filteredList.add(f);
            }
        }
        setListFac(filteredList);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LISTFAC);
        firePropertyChange(CURRENTFAC);
        firePropertyChange(FILTERFAC);
        firePropertyChange(LISTLIN);
        firePropertyChange(CURRENTLIN);
        firePropertyChange(FILTERLIN);
        this.mode = 0; // Default mode, adjust as needed
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

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public static final String LISTFAC = "listFac";
    public static final String LISTLIN = "listLin";
    public static final String CURRENTFAC = "currentFac";
    public static final String CURRENTLIN = "currentLin";
    public static final String FILTERFAC = "filterFac";
    public static final String FILTERLIN = "filterLin";

    public Factura getFacturaByNumero(String numero) {
        return null;
    }

    public List<Factura> getFacturasByFecha(LocalDate fecha) {
        return null;
    }
}
