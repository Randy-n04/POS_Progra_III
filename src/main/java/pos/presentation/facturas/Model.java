package pos.presentation.facturas;

import pos.Application;
import pos.logic.Factura;
import pos.presentation.AbstractModel;
import java.beans.PropertyChangeListener;
import java.util.List;
import pos.logic.Producto;
import pos.logic.Lines;
import java.util.ArrayList;

public class Model extends AbstractModel {
    private Factura filter;
    private List<Factura> list;
    private Factura current;
    private List<Producto> productos;
    private Lines lines; // Asegúrate de que esto se inicializa correctamente
    private int mode;

    public Model() {
        this.lines = new Lines(); // Inicializar Lines
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LIST);
        firePropertyChange(CURRENT);
        firePropertyChange(FILTER);
    }

    public void init(List<Factura> list) {
        this.list = list;
        this.current = new Factura();
        this.filter = new Factura();
        this.mode = Application.MODE_CREATE;
        // No necesitas inicializar lines aquí si ya está inicializado en el constructor
    }

    public List<Factura> getList() {
        return list;
    }

    public void setList(List<Factura> list) {
        this.list = list;
        firePropertyChange(LIST);
    }

    public Factura getCurrent() {
        return current;
    }

    public void setCurrent(Factura current) {
        this.current = current;
        firePropertyChange(CURRENT);
    }

    public Factura getFilter() {
        return filter;
    }

    public void setFilter(Factura filter) {
        this.filter = filter;
        firePropertyChange(FILTER);
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public static final String LIST = "list";
    public static final String CURRENT = "current";
    public static final String FILTER = "filter";

    public void addProducto(Producto producto) {
        if (productos == null) {
            productos = new ArrayList<>();
        }
        productos.add(producto);
        firePropertyChange(LIST);  // Notifica cambios en la lista de productos
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        firePropertyChange(LIST);  // Notifica cambios en la lista de productos
    }

    public Lines getLines() {
        return lines;
    }

    public void setLines(Lines lines) {
        this.lines = lines;
    }
}
