package pos.presentation.estadisticas;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import pos.Application;


public class View implements PropertyChangeListener {
    private JPanel Datos;
    private JPanel Grafico;
    private JComboBox InicioAnno;
    private JComboBox InicioFecha;
    private JComboBox FinalAnno;
    private JComboBox FinalFecha;
    private JComboBox Categorias;
    private JScrollPane Detalles;
    private JTable list;
    private JPanel panel;
    private JLabel InicioLbl;
    private JLabel FinalLbl;
    private JLabel CategoriaLbl;
    private JButton AgregarTodo;
    private JButton Agregar;
    private JButton EliminarTodo;
    private JButton Eliminar;

    private Model model;
    private Controller controller;

    public JPanel getPanel() {
        return panel;
    }

    public View(){
        //Agregar listeners





    }

    private boolean validateFields() {
        boolean valid = true;

        if(InicioAnno.getItemCount() == 0){
            valid = false;
            InicioLbl.setBorder(Application.BORDER_ERROR);
            InicioLbl.setToolTipText("Eliga una fecha correcta");
        }else{
            InicioLbl.setBorder(null);
            InicioLbl.setToolTipText(null);
        }

        if(InicioFecha.getItemCount() == 0){
            valid = false;
            InicioLbl.setBorder(Application.BORDER_ERROR);
            InicioLbl.setToolTipText("Eliga una fecha correcta");
        }else{
            InicioLbl.setBorder(null);
            InicioLbl.setToolTipText(null);
        }
        if(FinalAnno.getItemCount() == 0){
            valid = false;
            FinalLbl.setBorder(Application.BORDER_ERROR);
            FinalLbl.setToolTipText("Eliga una fecha correcta");
        }else{
            FinalLbl.setBorder(null);
            FinalLbl.setToolTipText(null);
        }
        if(FinalFecha.getItemCount() == 0){
            valid = false;
            FinalLbl.setBorder(Application.BORDER_ERROR);
            FinalLbl.setToolTipText("Eliga una fecha correcta");
        }else{
            FinalLbl.setBorder(null);
            FinalLbl.setToolTipText(null);
        }

        return valid;
    }


    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.CATEGORIES_ALL:
                if(Categorias != null)
                    Categorias.setModel(new DefaultComboBoxModel(model.getCategoriasALL().toArray()));
                break;
            case Model.RANGE:
                InicioAnno.setSelectedItem(""+model.getRango().getAnnoDesde()); //Todo metodos del rango no sirven
                InicioFecha.setSelectedItem(""+model.getRango().getMesDesde());
                FinalAnno.setSelectedItem(""+model.getRango().getAnnoHasta());
                FinalFecha.setSelectedItem(""+model.getRango().getMesHasta());
                break;
            case Model.DATA:
                //datos.setModel(model.getTableModel());     Todo   No se para que sirve esta linea
                JFreeChart chart = ChartFactory.createLineChart("Ventas por mes","Ventas","Mes",
                         createDataset(),
                         PlotOrientation.VERTICAL,
                         true,true,false);
                CategoryPlot plot = (CategoryPlot) chart.getPlot();
                LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
                renderer.setBaseShapesVisible(true);
                ChartPanel chartPanel = new ChartPanel(chart);
                Grafico.removeAll();
                Grafico.add(chartPanel);
        }
        this.panel.revalidate();
    }

    private DefaultCategoryDataset createDataset( ) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                //todo  AGREGAR LAS CATEGORIAS CON SUS VENTAS
        dataset.addValue( 15 , "AQUI VA LA CATEGORIA" , "MES Y ANIO" );

        return dataset;
    }

}
