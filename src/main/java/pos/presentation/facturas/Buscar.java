package pos.presentation.facturas;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;
import pos.logic.Factura;
import pos.logic.Lines;

public class Buscar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField descripcion;
    private JTable table1;
    private JLabel descripcionLbl;
    private Lines lines; // Instancia de Lines

    public Buscar(Lines lines) {
        this.lines = lines; // Inicializa Lines
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

        // Call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // Call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // Configurar la tabla
        table1.setModel(new DefaultTableModel(
                new Object[]{"Número", "Fecha", "Cajero", "Cliente", "Total"},
                0
        ));
    }

    private void onOK() {
        // Llama a la búsqueda
        String textoBusqueda = descripcion.getText();
        buscarFacturas(textoBusqueda);
    }

    private void onCancel() {
        // Cierra el diálogo
        dispose();
    }

    private void buscarFacturas(String textoBusqueda) {
        List<Factura> resultados = lines.buscarFacturas(textoBusqueda);
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0); // Limpia la tabla

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Factura factura : resultados) {
            model.addRow(new Object[]{
                    factura.getNumero(), // Número de la factura
                    dateFormat.format(factura.getFecha()), // Fecha de la factura
                    factura.getCajero() != null ? factura.getCajero().toString() : "N/A", // Cajero (ajusta si es necesario)
                    factura.getCliente() != null ? factura.getCliente().toString() : "N/A", // Cliente (ajusta si es necesario)
                    factura.getTotal() // Total de la factura
            });
        }
    }
}


