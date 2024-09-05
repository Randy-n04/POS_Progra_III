package pos.presentation.facturas;

import pos.logic.Factura;
import pos.logic.Producto;
import pos.presentation.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<Factura> implements javax.swing.table.TableModel {

    public TableModel(int[] cols, List<Factura> rows) {
        super(cols, rows);
    }

    public static final int CODIGO=0;
    public static final int ARTICULO=1;
    public static final int CATEGORIA =2;
    public static final int CANTIDAD =3;
    public static final int PRECIO =4;
    public static final int DESCUENTO =5;
    public static final int NETO = 6;
    public static final int IMPORTE = 7;

    @Override
    protected Object getPropetyAt(Factura e, int col) {
        switch (cols[col]){
            //case CODIGO: return e.getCodigo();
            //case ARTICULO: return e.getDescripcion();
            //case CATEGORIA: return e.getCategoria();
            //case CANTIDAD: return e.getCantidadComp();
            //case PRECIO: return e.getPrecioUnitario();
            //case DESCUENTO: return e.getImpuesto();
            //case NETO: return e.calculoNeto();
            //case IMPORTE: return e.calculoImporte();
            default: return "";
        }
    }

    @Override
    protected void initColNames(){
        colNames = new String[5];
        colNames[CODIGO]= "Codigo";
        colNames[ARTICULO]= "Articulo";
        colNames[CATEGORIA]= "Categoria";
        colNames[CANTIDAD]= "Cantidad";
        colNames[PRECIO]= "Precio";
        colNames[DESCUENTO]= "Descuento";
        colNames[NETO]= "Neto";
        colNames[IMPORTE]= "Importe";
    }

}
