package pos.presentation.estadisticas;

import pos.Application;
import pos.data.Data;
import pos.data.XmlPersister;
import pos.presentation.AbstractModel;
import pos.presentation.facturas.TableModel;
import pos.logic.*;

import javax.swing.table.AbstractTableModel;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
            rango = this.crearRango();
            rows = new String[categoriasALL.size()];
            if(rango != null)
                cols = rango.getFechas();
            else
                cols = new String[3];
            data = new float[categoriasALL.size()][cols.length];
            this.mode= Application.MODE_CREATE;
        }

        public AbstractTableModel getTableModel(){
            return new AbstractTableModel() {
                @Override
                public int getRowCount() {return rows.length;} //Queda ver si esto sirve

                @Override
                public int getColumnCount() {return cols.length+1;}

                @Override
                public Object getValueAt(int rowIndex, int columnIndex) {
                    if(columnIndex==0){
                        return rows[rowIndex];
                    }else{
                        return cols[columnIndex-1];
                    }
                }
                public String getColumnName(int column){
                    if(column==0) {
                        return "Categoria";
                    }else{
                        return "Otro";
                    }
                }
            };
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

        Rango crearRango(){
//                Rango nuevoRango= null;  //Todo Datos de prueba abajo, ya que aun no hay facturas con fecha
//                Data data = null;
//                try {
//                    data = XmlPersister.instance().load();
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//                List<Factura> facturaList = data.getFacturas();
//
//                int anioInicio = -1;
//                int mesInicio = -1;
//                int anioFin = -1;
//                int mesFin = -1;
//
//                for (Factura factura : facturaList) {
//                    Date fecha = factura.getFecha();
//                    Calendar cal = Calendar.getInstance();
//                    cal.setTime(fecha);
//
//                    int anio = cal.get(Calendar.YEAR);
//                    int mes = cal.get(Calendar.MONTH) + 1;
//
//                    if (anioInicio == -1 || anio < anioInicio || (anio == anioInicio && mes < mesInicio)) {
//                        anioInicio = anio;
//                        mesInicio = mes;
//                    }
//
//                    if (anioFin == -1 || anio > anioFin || (anio == anioFin && mes > mesFin)) {
//                        anioFin = anio;
//                        mesFin = mes;
//                    }
//                }
//                nuevoRango = new Rango(anioInicio, mesInicio, anioFin, mesFin);
//                return nuevoRango;
            return new Rango(2004,5,2024,7);
        }


        public static final String CATEGORIES_ALL = "categories_all";
        public static final String RANGE = "range";
        public static final String DATA = "data";
