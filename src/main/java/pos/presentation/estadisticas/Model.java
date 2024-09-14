package pos.presentation.estadisticas;

import pos.Application;
import pos.logic.Factura;
import pos.presentation.AbstractModel;
import pos.presentation.facturas.View;

import java.beans.PropertyChangeListener;
import java.util.List;

public class Model extends AbstractModel {

    List<Factura> list;
    int mode;

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(LIST);
    }




    public static final String LIST = "list";

    public void addPropertyChangeListener(View view) {
    }
}
