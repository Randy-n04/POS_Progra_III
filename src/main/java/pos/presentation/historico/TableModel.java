package pos.presentation.historico;


import pos.logic.Linea;
import pos.presentation.AbstractTableModel;

import java.security.ProtectionDomain;
import java.util.List;

public class TableModel extends AbstractTableModel<Linea> implements javax.swing.table.TableModel {

    public TableModel(int[] cols, List<Linea> rows) {
        super(cols, rows);
    }

    public static final int CODIGO=0;
    public static final int ARTICULO=1;
    public static final int CATEGORIA =2;
    public static final int CANTIDAD=3;
    public static final int PRECIOUNITARIO=4;
    public static final int DESCUENTO=5;
    public static final int PRECIONETO=6;
    public static final int IMPORTE=7;

    @Override
    protected Object getPropetyAt(Linea e, int col) {
        switch (cols[col]){
            case CODIGO: return e.getProducto().getCodigo();
            case ARTICULO: return e.getProducto().getDescripcion();
            case CATEGORIA: return e.getProducto().getCategoria();
            case CANTIDAD: return e.getCantidad();
            case PRECIOUNITARIO: return e.getProducto().getPrecioUnitario();
            case DESCUENTO: return e.getDescuento();
            case PRECIONETO: return e.getNeto();
            case IMPORTE: return e.getImporte();
            default: return "";
        }
    }
    @Override
    protected void initColNames(){
        colNames = new String[8];
        colNames[CODIGO]= "Codigo";
        colNames[ARTICULO]= "Articulo";
        colNames[CATEGORIA]= "Categoria";
        colNames[CANTIDAD]= "Cantidad";
        colNames[PRECIOUNITARIO]= "Precio";
        colNames[DESCUENTO]= "Descuento";
        colNames[PRECIONETO]= "PrecioUnitario";
        colNames[IMPORTE]= "Importe";
    }
}


