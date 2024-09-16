package pos.presentation.facturas;

import pos.Application;
import pos.logic.*;
import pos.presentation.AbstractModel;
import java.beans.PropertyChangeListener;
import java.util.List;


public class Model extends AbstractModel {
    Linea filter;
    Linea current;
    int mode;
    List<Linea> list;
    List<Cajero> cajeros;
    List<Cliente> clientes;

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LIST);
        firePropertyChange(CURRENT);
        firePropertyChange(FILTER);
        firePropertyChange(CAJEROS);
        firePropertyChange(CLIENTES);
    }

    public void init(List<Linea> list, List<Cliente> clientes, List<Cajero> cajeros) {
        this.list = list;
        this.clientes = clientes;
        this.cajeros = cajeros;
        this.filter = new Linea();
        this.current = new Linea();
        this.mode = Application.MODE_CREATE;
    }

    public Model() {

    }

    public List<Linea> getList(){ return list; }

    public void setList(List<Linea> list){ this.list = list; firePropertyChange(LIST); }

    public Linea getCurrent() { return current; }

    public void setCurrent(Linea current) { this.current = current; firePropertyChange(CURRENT); }

    public Linea getFilter() {
        return filter;
    }

    public void setFilter(Linea filter) { this.filter = filter; firePropertyChange(FILTER); }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public List<Cajero> getCajeros() { return cajeros; }

    public void setCajeros(List<Cajero> cajeros){ this.cajeros = cajeros; firePropertyChange(CAJEROS); }

    public List<Cliente> getClientes() { return clientes; }

    public void setClientes(List <Cliente> clientes){ this.clientes = clientes; firePropertyChange(CLIENTES); }

    public static final String LIST = "list";
    public static final String CURRENT = "current";
    public static final String FILTER = "filter";
    public static final String CAJEROS = "cajers";
    public static final String CLIENTES = "clientes";

}
