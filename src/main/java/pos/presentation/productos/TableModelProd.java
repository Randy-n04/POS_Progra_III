package pos.presentation.productos;

import pos.logic.Producto;
import pos.presentation.AbstractTableModel;

import java.security.ProtectionDomain;
import java.util.List;

public class TableModelProd extends AbstractTableModel<Producto> implements javax.swing.table.TableModel {

    public TableModelProd(int[] cols, List<Producto> rows) {
        super(cols, rows);
    }

    public static final int CODIGO=0;
    public static final int DESCRIPCION=1;
    public static final int UNIDADMEDIDA =2;
    public static final int PRECIOUNITARIO=3;
    public static final int EXISTENCIAS=4;
    public static final int CATEGORIA=5;

    @Override
    protected Object getPropetyAt(Producto e, int col) {
        switch (cols[col]){
            case CODIGO: return e.getCodigo();
            case DESCRIPCION: return e.getDescripcion();
            case UNIDADMEDIDA: return e.getUnidadMedida();
            case PRECIOUNITARIO: return e.getPrecioUnitario();
            case EXISTENCIAS: return e.getExistencias();
            case CATEGORIA: return e.getCategoria();
            default: return "";
        }
    }

    @Override
    protected void initColNames(){
        colNames = new String[6];
        colNames[CODIGO]= "Codigo";
        colNames[DESCRIPCION]= "Descripcion";
        colNames[UNIDADMEDIDA]= "Unidad de medida";
        colNames[PRECIOUNITARIO]= "Precio por unidad";
        colNames[EXISTENCIAS]= "Existencias";
        colNames[CATEGORIA]= "Categoria";
    }
    public void setProducts(List<Producto> productos) {
        this.rows = productos;
        fireTableDataChanged(); // Notifica que los datos han cambiado
    }

}

