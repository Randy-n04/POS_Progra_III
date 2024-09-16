package pos.presentation.facturas;
import pos.Application;
import pos.data.Data;
import pos.data.XmlPersister;
import pos.logic.*;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class View implements PropertyChangeListener {
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
                int fila = productosTbl.getSelectedRow();
                Linea linea = model.getList().get(fila);
                try {
                    String cantStr = JOptionPane.showInputDialog(null, "Ingrese la cantidad: ", linea.getCantidad());
                    int cantInt = Integer.parseInt(cantStr);
                    if (cantInt > linea.getCantidad()) {
                        linea.setCantidad(cantInt);
                        model.setList(model.getList());
                        productosTbl.revalidate();
                        productosTbl.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Cantidad inválida...");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
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

        agregarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String codigoProd = textProducto.getText();
                    Producto prod = null;
                    for (Producto producto : Service.instance().getProductos()) {
                        if (producto.getCodigo().equals(codigoProd)) {
                            prod = producto;
                            break;
                        }
                    }
                    if (prod == null) {
                        JOptionPane.showMessageDialog(null, "El producto no existe");
                    } else {
                        JOptionPane.showMessageDialog(null, "El producto se agregó...");
                        Linea nuevoProd = new Linea();
                        nuevoProd.setProducto(prod);
                        model.getList().add(nuevoProd);
                        model.setList(model.getList());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        productosTbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = productosTbl.getSelectedRow();
                controller.edit(row);
            }
        });

        clienteBox.setEditable(false);
        clienteBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente selectedCli = (Cliente) clienteBox.getSelectedItem();
            }
        });
        try {
            Data data = XmlPersister.instance().load();
            List<Cliente> clientesData = data.getClientes();
            DefaultComboBoxModel<Cliente> model = new DefaultComboBoxModel<>();
            for (Cliente cliente : clientesData) {
                model.addElement(cliente);
            }
            clienteBox.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        cajeroBox.setEditable(false);
        cajeroBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cajero selectedCaj = (Cajero) cajeroBox.getSelectedItem();
            }
        });
        try {
            Data data = XmlPersister.instance().load();
            List<Cajero> cajeroData = data.getCajeros();
            DefaultComboBoxModel<Cajero> model = new DefaultComboBoxModel<>();
            for (Cajero cajero : cajeroData) {
                model.addElement(cajero);
            }
            cajeroBox.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validate() {
        boolean valid = true;
        String codigoProd = textProducto.getText();
        ;
        if (codigoProd.isEmpty() || codigoProd.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el codigo del producto");
            valid = false;
        }
        Producto prod = null;
        for (Producto producto : Service.instance().getProductos()) {
            if (producto.getCodigo().equals(codigoProd)) {
                prod = producto;
                break;
            }
        }
        if (prod == null) {
            JOptionPane.showMessageDialog(null, "El producto no existe");
            valid = false;
        }

        valid = true;
        return valid;
    }

    // MVC
    Model model;
    Controller controller;

    public void setModel(Model model){
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(Controller controller){
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                int[] cols = {TableModel.CODIGO, TableModel.ARTICULO, TableModel.CATEGORIA,
                TableModel.CANTIDAD, TableModel.PRECIO, TableModel.DESCUENTO,
                TableModel.NETO, TableModel.IMPORTE};
                productosTbl.setModel(new TableModel(cols, model.getList()));
                productosTbl.setRowHeight(30);
                TableColumnModel columnModel = productosTbl.getColumnModel();
                columnModel.getColumn(1).setPreferredWidth(150);
                break;
            case Model.CURRENT:
                cajeroBox.setSelectedItem(model.getCajeros());
                clienteBox.setSelectedItem(model.getClientes());
                if(model.getMode() == Application.MODE_EDIT){
                    textProducto.setEditable(false);
                    quitarBtn.setEnabled(true);
                } else{
                    textProducto.setEditable(true);
                    quitarBtn.setEnabled(false);
                }
                break;
            case Model.CAJEROS:
                cajeroBox.setModel(new DefaultComboBoxModel<>(model.getCajeros().toArray(new Cajero[0])));
                break;
            case Model.CLIENTES:
                clienteBox.setModel(new DefaultComboBoxModel<>(model.getClientes().toArray(new Cliente[0])));
                break;
            case Model.FILTER:
                textProducto.setText(model.getFilter().getProducto().getCodigo());

        }
        panel.revalidate();
    }
}