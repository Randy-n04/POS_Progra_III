package pos.presentation.facturas;
import pos.data.*;
import pos.logic.*;

import pos.presentation.facturas.*;
import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class View implements PropertyChangeListener {
    private JPanel panel;
    private JComboBox<Cliente> clienteBox;
    private JLabel clienteIcon;
    private JComboBox<Cajero> cajeroBox;
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
    private JTextField textProducto;
    private JButton agregarBtn;
    private JTable productosTbl;
    private JPanel cajerobox;

    public JPanel getPanel() {
        return panel;
    }

    public View() {
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

    public void cargarClientes(List<Cliente> clientes) {
        clienteBox.removeAllItems();
        for (Cliente cliente : clientes) {
            clienteBox.addItem(cliente);
        }
    }

    public void cargarCajeros(List<Cajero> cajeros) {
        cajeroBox.removeAllItems();
        for (Cajero cajero : cajeros) {
            cajeroBox.addItem(cajero);
        }

    }

    public void actualizarTextoProductos() {
        TableModel tableModel = (TableModel) productosTbl.getModel();
        StringBuilder productosText = new StringBuilder();

        for (int row = 0; row < tableModel.getRowCount(); row++) {
            String codigo = tableModel.getValueAt(row, TableModel.CODIGO).toString();
            String descripcion = tableModel.getValueAt(row, TableModel.ARTICULO).toString();
            productosText.append(codigo).append(" - ").append(descripcion).append("\n");
        }

        textProducto.setText(productosText.toString());
    }

    Model model;
    Controller controller;

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}