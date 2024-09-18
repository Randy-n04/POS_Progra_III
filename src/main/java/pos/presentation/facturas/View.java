package pos.presentation.facturas;
import pos.Application;
import pos.data.Data;
import pos.data.LocalDateAdapter;
import pos.data.XmlPersister;
import pos.logic.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private JLabel descuentosResultado;
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
        agregarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String codigoProd = textProducto.getText();
                    if(codigoProd.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Ingrese el codigo de producto");
                        return;
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
                        return;
                    }
                    for (Linea linea : model.getList()) {
                        if (linea.getProducto().getCodigo().equals(prod.getCodigo())) {
                            JOptionPane.showMessageDialog(null, "El producto ya está agregado.");
                            return;
                        }
                    }
                    Linea nuevoProd = new Linea();
                    nuevoProd.setProducto(prod);
                    model.getList().add(nuevoProd);
                    model.setList(model.getList());
                    JOptionPane.showMessageDialog(null, "El producto se agregó...");
                    textProducto.setText("");
                    textProducto.requestFocus();
                    actualizarResultados();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        cobrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String totalText = totalResultado.getText().trim();
                    if (totalText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El valor del total está vacío.");
                        return;
                    }
                    totalText = totalText.replace(',', '.');
                    float total = Float.parseFloat(totalText);
                    Cobrar dialog = new Cobrar(total);
                    dialog.pack();
                    dialog.setVisible(true);
                    if (dialog.isPagoRealizado()) {
                        guardarFactura();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El valor del total no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar la factura: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione un producto");
                    return;
                }

                Cantidad dialog = new Cantidad();
                dialog.pack();
                dialog.setVisible(true);

                // Obtener la cantidad ingresada
                int nuevaCantidad = dialog.getCantidadIngresada();

                // Asegúrate de que la cantidad sea válida
                if (nuevaCantidad > 0) {
                    Linea linea = model.getList().get(fila);
                    linea.setCantidad(nuevaCantidad);
                    model.setList(model.getList());  // Notificar cambios
                    productosTbl.revalidate();
                    productosTbl.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Cantidad inválida");
                }
            }
        });

        quitarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = productosTbl.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione un producto");
                    return;
                }

                // Confirmación para eliminar el producto
                int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este producto?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Eliminar la línea del producto del modelo
                    model.getList().remove(fila);

                    // Actualizar la lista del modelo y la tabla
                    model.setList(model.getList());
                    productosTbl.revalidate();
                    productosTbl.repaint();
                }
            }
        });


        descuentoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = productosTbl.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione un producto");
                    return;
                }

                Descuento dialog = new Descuento();
                dialog.pack();
                dialog.setVisible(true);

                // Obtener la cantidad ingresada
                float nuevoDescuento = dialog.getDiscountValue();

                // Asegúrate de que la cantidad sea válida
                if (nuevoDescuento > 0 && nuevoDescuento <= 100) {
                    Linea linea = model.getList().get(fila);
                    linea.setDescuento(nuevoDescuento);
                    model.setList(model.getList());
                    productosTbl.revalidate();
                    productosTbl.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Cantidad inválida");
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

        cancelarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
            }
        });


        productosTbl.getSelectionModel().addListSelectionListener(e -> {
            boolean isSelected = productosTbl.getSelectedRow() != -1; // Verifica si hay una fila seleccionada
            cantidadBtn.setEnabled(isSelected);
            descuentoBtn.setEnabled(isSelected);
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

    private void guardarFactura() {
        try {
            Factura factura = takeFactura();
            controller.save(factura);
            JOptionPane.showMessageDialog(null, "Factura guardada exitosamente.");
            actualizar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar la factura: " + ex.getMessage());
        }
    }


    private void actualizar() {
        model.setList(new ArrayList<>()); // Asegúrate de que la lista nunca sea null
        productosTbl.setModel(new TableModel(new int[]{
                TableModel.CODIGO, TableModel.ARTICULO, TableModel.CATEGORIA,
                TableModel.CANTIDAD, TableModel.PRECIO, TableModel.DESCUENTO,
                TableModel.NETO, TableModel.IMPORTE
        }, model.getList())); // Reiniciar la tabla
        productosTbl.revalidate();
        productosTbl.repaint();

        // Limpiar el campo de texto del producto
        textProducto.setText("");

        // Restablecer los JComboBox de clientes y cajeros
        try {
            Data data = XmlPersister.instance().load();
            List<Cliente> clientesData = data.getClientes();
            List<Cajero> cajeroData = data.getCajeros();

            clienteBox.setModel(new DefaultComboBoxModel<>(clientesData.toArray(new Cliente[0])));
            cajeroBox.setModel(new DefaultComboBoxModel<>(cajeroData.toArray(new Cajero[0])));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Actualizar el estado de los botones y campos
        textProducto.setEditable(true);
        quitarBtn.setEnabled(false);
    }

    private void actualizarResultados() {
        int totalArticulos = 0;
        float totalNeto = 0;
        float totalDescuentos = 0;
        float totalImporte = 0;

        for (Linea linea : model.getList()) {
            totalArticulos += linea.getCantidad();
            totalNeto += linea.getNeto();
            totalDescuentos += linea.getDescuento();
            totalImporte += linea.getImporte();
        }

        // Asegúrate de que el formato sea correcto
        articulosResultado.setText(String.valueOf(totalArticulos));
        subtotalResultado.setText(String.format("%.2f", totalNeto));
        descuentosResultado.setText(String.format("%.2f", totalDescuentos));
        totalResultado.setText(String.format("%.2f", totalImporte));
    }

    public Factura takeFactura() {
        Factura factura = new Factura();
        factura.setCliente((Cliente) clienteBox.getSelectedItem());
        factura.setCajero((Cajero) cajeroBox.getSelectedItem());
        factura.setFecha(LocalDate.now());

        TableModel tableModel = (TableModel) productosTbl.getModel();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Linea linea = new Linea();
            String codigoProd = (String) tableModel.getValueAt(i, TableModel.CODIGO);
            int cantidad = (Integer) tableModel.getValueAt(i, TableModel.CANTIDAD);
            float precio = (Float) tableModel.getValueAt(i, TableModel.PRECIO);
            float descuento = (Float) tableModel.getValueAt(i, TableModel.DESCUENTO);

            Producto producto = getProductoPorCodigo(codigoProd);
            if (producto != null) {
                linea.setProducto(producto);
                linea.setCantidad(cantidad);
                linea.setNeto(precio);
                linea.setDescuento(descuento);
                linea.setImporte(precio * cantidad - descuento);
                factura.getLineas().add(linea);
            }
        }
        return factura;
    }


    private Producto getProductoPorCodigo(String codigo) {
        for (Producto producto : Service.instance().getProductos()) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;  // Devuelve el producto si coincide el código
            }
        }
        return null;  // Devuelve null si no se encuentra el producto
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
                productosTbl.revalidate();
                productosTbl.repaint();
                actualizarResultados();
                break;
            case Model.CURRENT:
                cajeroBox.setSelectedItem(model.getCajeros());
                clienteBox.setSelectedItem(model.getClientes());
                if(model.getMode() == Application.MODE_EDIT){
                    textProducto.setEditable(true);
                    quitarBtn.setEnabled(true);
                } else{
                    textProducto.setEditable(true);
                    quitarBtn.setEnabled(false);
                }
                actualizarResultados();
                break;
            case Model.CAJEROS:
                cajeroBox.setModel(new DefaultComboBoxModel<>(model.getCajeros().toArray(new Cajero[0])));
                break;
            case Model.CLIENTES:
                clienteBox.setModel(new DefaultComboBoxModel<>(model.getClientes().toArray(new Cliente[0])));
                break;
            case Model.FILTER:
                textProducto.setText(model.getFilter().getProducto().getCodigo());
                actualizarResultados();

        }
        panel.revalidate();
        actualizarResultados();
    }

}