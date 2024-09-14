package pos.presentation.estadisticas;

import pos.logic.Categoria;
import pos.presentation.AbstractModel;
import pos.presentation.AbstractTableModel;

import java.beans.PropertyChangeListener;
import java.util.List;

public class Model extends AbstractModel {
    List<Categoria> categoriasAll;
    List<Categoria> categorias;
    //Rango rango;
    String[] rows;
    String[] columns;
    float[][] data;

    /*@Override
    public void addPropertyChangeListener(Categoria categoria, PropertyChangeListener listener) {
        super.addPropertyChangeListener()
                firePropertyChange(CATEGORIES )
    }*/

    public TableModel getTableModel(){
        return new AbstractTableModel() {() {
            @Override
                    public int getRowCount
        }}
    }
}
