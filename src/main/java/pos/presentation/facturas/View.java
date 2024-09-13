package pos.presentation.facturas;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {
    private JPanel panel;
    private JComboBox clienteBox;
    private JLabel clienteIcon;
    private JComboBox cajeroBox;
    private JLabel cajeroIcon;
    private JLabel articulosLbl;
    private JLabel articulosResultado;
    private JLabel subtotalLbl;
    private JLabel subtotalResultado;
    private JLabel descuentosLbl;
    private JLabel descuentosResultados;
    private JLabel totalLbl;
    private JLabel totalResultado;
    private JButton cobrarBtn;
    private JButton buscarBtn;
    private JButton cantidadBtn;
    private JButton quitarBtn;
    private JButton descuentoBtn;
    private JButton cancelarBtn;
    private JTextField textField1;
    private JButton agregarBtn;
    private JTable productosTbl;

    private Model model; // Asegúrate de tener esta variable
    private Controller controller;

    public JPanel getPanel() { return panel; }

    public View() {
        // Asignar manejadores de eventos a los botones
        cobrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.openCobrarDialog();
                }
            }
        });

        buscarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.openBuscarDialog();
                }
            }
        });

        cantidadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.openCantidadDialog();
                }
            }
        });

        descuentoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.openDescuentoDialog();
                }
            }
        });
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                switch (evt.getPropertyName()) {
                    case Model.LIST:
                        int[] cols = {TableModel.CODIGO,
                                TableModel.ARTICULO, TableModel.CATEGORIA,
                                TableModel.CANTIDAD, TableModel.PRECIO,
                                TableModel.DESCUENTO, TableModel.NETO,
                                TableModel.IMPORTE};
                        productosTbl.setModel(new TableModel(cols, model.getList()));
                        productosTbl.setRowHeight(30);
                        TableColumnModel columnModel = productosTbl.getColumnModel();
                        columnModel.getColumn(1).setPreferredWidth(150);
                        columnModel.getColumn(3).setPreferredWidth(150);
                        break;
                }

                panel.revalidate();
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}


