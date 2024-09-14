package pos.presentation.facturas;

import pos.data.Data;
import pos.Application;
import pos.logic.Categoria;
import pos.logic.Linea;
import pos.presentation.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import pos.presentation.productos.*;
import java.util.List;
import pos.logic.Producto;
import pos.logic.Service;

public class Buscar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField descripcion;
    private JTable table1;
    private JLabel descripcionLbl;
    private Controller controller;
    private Service service;

    public Buscar() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // Configurar la tabla
        table1.setModel(new DefaultTableModel(
                new Object[]{"Codigo", "Descripcion", "Unidad", "Precio", "Existencias", "Categoria"},
                0
        ));
    }

    private void onOK() {
        // Llama a la búsqueda
        String textoBusqueda = descripcion.getText();
        buscarProductos(textoBusqueda);
    }

    private void onCancel() {
        // Cierra el diálogo
        dispose();
    }

    private void buscarProductos(String textoBusqueda) {
        List<Producto> resultados = Service.instance().search(new Producto()); // Ajusta según la lógica de búsqueda
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0); // Limpia la tabla

        for (Producto producto : resultados) {
            model.addRow(new Object[]{
                    producto.getCodigo(), // Código del producto
                    producto.getDescripcion(), // Descripción del producto
                    producto.getUnidadMedida(), // Unidad de medida del producto
                    producto.getPrecioUnitario(), // Precio del producto
                    producto.getExistencias(), // Existencias del producto
                    producto.getCategoria() // Categoría del producto
            });
        }
    }
    public static void main(String[] args) {
        Buscar dialog = new Buscar();
        dialog.pack();
        dialog.setVisible(true);
        System.out.println(0);
        System.exit(0);
    }
}