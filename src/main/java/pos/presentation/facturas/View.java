package pos.presentation.facturas;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import pos.logic.Cliente;
import pos.logic.Cajero;

public class View {
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

    private Model model; // Asegúrate de tener esta variable
    private Controller controller;

    public JPanel getPanel() {
        return panel;
    }

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

    // Método para cargar clientes en el JComboBox clienteBox
    public void cargarClientes(List<Cliente> clientes) {
        clienteBox.removeAllItems();  // Limpiar cualquier ítem previo
        for (Cliente cliente : clientes) {
            clienteBox.addItem(cliente);  // Agregar cada cliente al JComboBox
        }
    }

    // Método para cargar cajeros en el JComboBox cajeroBox
    public void cargarCajeros(List<Cajero> cajeros) {
        cajeroBox.removeAllItems();  // Limpiar cualquier ítem previo
        for (Cajero cajero : cajeros) {
            cajeroBox.addItem(cajero);  // Agregar cada cajero al JComboBox
        }
      
    }

    // Método para actualizar el JTextField con los productos
    public void actualizarTextoProductos() {
        // Obtén el modelo de la tabla de productos
        TableModel tableModel = (TableModel) productosTbl.getModel();
        StringBuilder productosText = new StringBuilder();

        // Itera sobre las filas de la tabla para construir el texto
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            String codigo = tableModel.getValueAt(row, TableModel.CODIGO).toString();
            String descripcion = tableModel.getValueAt(row, TableModel.ARTICULO).toString();
            productosText.append(codigo).append(" - ").append(descripcion).append("\n");
        }

        // Actualiza el JTextField con el texto de los productos
        textProducto.setText(productosText.toString());
    }

    // Método para configurar el modelo y agregar el PropertyChangeListener
    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (Model.LIST.equals(evt.getPropertyName())) {
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

                    // Actualiza el JTextField con los productos
                    actualizarTextoProductos();
                }
                panel.revalidate();
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}



