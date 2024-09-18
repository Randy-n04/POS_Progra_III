package pos.presentation.estadisticas;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import pos.Application;
import pos.data.Data;
import pos.data.XmlPersister;
import pos.logic.Categoria;

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



    public JPanel getPanel() {
        return panel;
    }

    private Model model;
    private Controller controller;

    public View(){

        //Agregar listeners
        Categorias.setEditable(false);
        Categorias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Categoria selectedCat = (Categoria) Categorias.getSelectedItem();
                System.out.println("Selected Category: " + selectedCat.getNombre());
            }
        });


        Agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Categoria selectedCat = (Categoria) Categorias.getSelectedItem();
                if (selectedCat != null) {
                    DefaultTableModel model = (DefaultTableModel) list.getModel();
                    boolean exists = false;
                    for (int i = 0; i < model.getRowCount(); i++)
                        if (model.getValueAt(i, 0).equals(selectedCat.getNombre())) {
                            exists = true;
                            break;
                        }
                    if (!exists)
                        model.addRow(new Object[]{selectedCat.getNombre()});
                    else
                        JOptionPane.showMessageDialog(panel, "La categoría ya existe en la lista.");

                }
            }
        });


        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Categoria selectedCat = (Categoria) Categorias.getSelectedItem();
                if (selectedCat != null) {
                    DefaultTableModel model = (DefaultTableModel) list.getModel();
                    boolean exists = false;
                    int pos = -1;
                    for (int i = 0; i < model.getRowCount(); i++)
                        if (model.getValueAt(i, 0).equals(selectedCat.getNombre())) {
                            exists = true;
                            pos = i;
                            break;
                        }
                    if (exists && pos != -1)
                        model.removeRow(pos);
                    else
                        JOptionPane.showMessageDialog(panel, "Seleccione una categoría para eliminar.");
                }
            }
        });

        AgregarTodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) list.getModel();

                for (int i = 0; i < Categorias.getItemCount(); i++) {
                    Categoria categoria = (Categoria) Categorias.getItemAt(i);

                    boolean exists = false;
                    for (int j = 0; j < model.getRowCount(); j++) {
                        if (model.getValueAt(j, 0).equals(categoria.getNombre())) {
                            exists = true;
                            break;
                        }
                    }

                    if (!exists) {
                        model.addRow(new Object[]{categoria.getNombre()});
                    }
                }
            }
        });


        EliminarTodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) list.getModel();

                model.setRowCount(0);
            }
        });
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
                Data data = null;
                try {
                    data = XmlPersister.instance().load();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                List<Categoria> categoriasData = data.getCategorias();
                DefaultComboBoxModel<Categoria> modell = new DefaultComboBoxModel<>();
                for (Categoria categoria : categoriasData) {
                    modell.addElement(categoria);
                }
                Categorias.setModel((DefaultComboBoxModel<Categoria>)modell);
                break;
            case Model.RANGE:
                if(model.getRango() != null) {
                    int mesLimite = (model.getRango().getAnnoDesde() == model.getRango().getAnnoHasta())?model.getRango().mesHasta:12;

                    InicioAnno.setSelectedItem("" + model.getRango().getAnnoDesde());
                    for (int i = model.getRango().getAnnoDesde(); i <= model.getRango().getAnnoHasta(); i++)
                        InicioAnno.addItem(i);
                    InicioFecha.setSelectedItem("" + model.getRango().getMesDesde());
                    for (int i = 1; i <= mesLimite; i++)
                        InicioFecha.addItem(i);


                    FinalAnno.setSelectedItem("" + model.getRango().getAnnoHasta());
                    for (int i = model.getRango().getAnnoDesde(); i <= model.getRango().getAnnoHasta(); i++)
                        FinalAnno.addItem(i);
                    FinalFecha.setSelectedItem("" + model.getRango().getMesHasta());
                    for (int i = 1; i <= model.getRango().getMesHasta(); i++)
                        FinalFecha.addItem(i);

                    //System.out.println(model.getRango().getFechaInicio() + ", " + model.getRango().getFechaFin());   //Para Depurar los datos
                }else
                    System.out.println("No se encontro el rango");
                break;
            case Model.DATA:
                //TODO: Datos de Prueba, solo para ver que sirva el List(Jtable)
                Object[][] datos = {};
                String[] nom = {"Categoria"};
                list.setModel(new DefaultTableModel(datos,nom));
//Esto crea el chart para mostrarlo en el grafico
                JFreeChart chart = ChartFactory.createLineChart(
                        "Ventas por mes", "Ventas", "Mes",
                        createDataset(),
                        PlotOrientation.VERTICAL,
                        true, true, false);
                CategoryPlot plot = (CategoryPlot) chart.getPlot();
                LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
                renderer.setDefaultShapesVisible(true);
                ChartPanel chartPanel = new ChartPanel(chart);
                if(Grafico != null){
                    Grafico.removeAll();
                    Grafico.add(chartPanel);
                }else
                    System.out.println("No se encontro el grafico");
            break;
        }
        this.panel.revalidate();
    }

    private DefaultCategoryDataset createDataset( ) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                //todo  AGREGAR LAS CATEGORIAS CON SUS VENTAS
        dataset.addValue( 150 , "Lacteo" , "12 2007");
        dataset.addValue( 300 , "Fruta" , "15 2013");
        dataset.addValue( 500 , "Fruta" , "7 2014");
        dataset.addValue( 470 , "Fruta" , "5 2006");
        dataset.addValue( 190 , "Snack" , "6 2006");
        dataset.addValue( 210 , "Snack" , "12 2018");
        dataset.addValue( 470 , "Lacteo" , "1 2020");
        dataset.addValue( 270 , "Lacteo" , "11 2023");

        return dataset;
    }

}
