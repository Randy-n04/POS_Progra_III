package pos.presentation.estadisticas;

import pos.Application;
import pos.presentation.AbstractModel;
import pos.presentation.AbstractTableModel;
import pos.presentation.facturas.View;
import pos.logic.*;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {

    private List<Categoria> categoriasALL;
    private List<Categoria> categorias;
    private Rango rango;
    private String[] rows;
    private String[] cols;
    private float[][] data;
    private int mode;

    public Model() {}


    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CATEGORIES_ALL);
        firePropertyChange(RANGE);
        firePropertyChange(DATA);
    }

    public void init(List<Categoria> list){
        this.categoriasALL= list;
        this.categorias= new ArrayList<Categoria>();
        rango= new Rango();
        rows = new String[categoriasALL.size()];
        cols = new String[categorias.size()];//TODO: cambiar por la cantidad de categorias inicializadas
        data = new float[categoriasALL.size()][categorias.size()];
        this.mode= Application.MODE_CREATE;
    }


    public List<Categoria> getCategoriasALL() { return categoriasALL;}
    public void setCAtALL(List<Categoria> list){this.categoriasALL= list;}

    public List<Categoria> getcategorias(){return categorias;}
    public void setcategorias(List<Categoria> list){this.categorias= list;}

    public Rango getRango(){return rango;}
    public void setRango(Rango rango){this.rango= rango;}

    public String[] getRows(){return rows;}
    public void setRows(String[] rows){this.rows= rows;}

    public String[] getCols(){return cols;}
    public void setCols(String[] cols){this.cols= cols;}

    public float[][] getData(){return data;}
    public void setData(float[][] data){this.data= data;}



    public static final String CATEGORIES_ALL = "categories_all";
    public static final String RANGE = "range";
    public static final String DATA = "data";

}
