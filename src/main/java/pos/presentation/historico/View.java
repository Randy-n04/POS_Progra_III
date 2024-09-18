package pos.presentation.historico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class View {
    private JPanel Búsqueda;
    private JPanel Listado;
    private JButton SearchFactBtn;
    private JButton SearchFechaBtn;
    private JTextField Fecha;
    private JButton reporteButton;
    private JButton reporteButton1;
    private JLabel FechaLbl;
    private JLabel NumFactLbl;
    private JTextField NumFact;
    private JTable list;
    private JLabel DoubleClickLbl;
    private JPanel panel;
    private Controller controller;

    public JPanel getPanel() {
        return panel;
    }

    public View() {
        // Listener para que cuando haga doble click, muestre los detalles.
        list.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = list.getSelectedRow();
                    if (row >= 0) {
                        // Factura facturaAt = ; // Aquí deberías implementar la lógica para obtener la factura seleccionada.
                        // JOptionPane.showMessageDialog(panel, facturaAt.toString(), "", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        SearchFechaBtn.addActionListener(e -> {
            try {
                String fechaStr = Fecha.getText();
                LocalDate fecha = LocalDate.parse(fechaStr);
                if (controller != null) {
                    controller.searchByFecha(fecha);
                }
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(panel, "Fecha inválida. Por favor, use el formato YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });



        SearchFactBtn.addActionListener(e -> {
            try {
                String numFactura = NumFact.getText();
                if (controller != null) {
                    controller.searchByNumFactura(numFactura);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}



