package pos.presentation.facturas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import pos.logic.*;

public class Buscar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField descripcion;
    private JTable table1;
    private JLabel descripcionLbl;
    private Model model;
    private Controller controller;

    public Buscar() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // Configurar la tabla
        table1.setModel(new DefaultTableModel(
                new Object[]{"Codigo", "Descripcion", "Unidad de medida", "Precio por unidad", "Existencias", "Categoria"},
                0
        ));

        actualizarTabla(null);

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
    }

    private void onOK() {
        String textoBusqueda = descripcion.getText().trim();
        actualizarTabla(textoBusqueda);
    }

    private void onCancel() {
        dispose();
    }

    private void actualizarTabla(String descripcionFiltro) {
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0); // Limpiar la tabla

        List<Producto> productos = Service.instance().getProductos();

        for (Producto producto : productos) {
            if (descripcionFiltro == null || descripcionFiltro.isEmpty() ||
                    producto.getDescripcion().toLowerCase().contains(descripcionFiltro.toLowerCase())) {
                tableModel.addRow(new Object[]{
                        producto.getCodigo(),
                        producto.getDescripcion(),
                        producto.getUnidadMedida(),
                        producto.getPrecioUnitario(),
                        producto.getExistencias(),
                        producto.getCategoria()
                });
            }
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        Buscar dialog = new Buscar();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
