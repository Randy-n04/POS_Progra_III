package pos.presentation.historico;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import pos.logic.Factura;

public class View {
    private JPanel Búsqueda;
    private JPanel Listado;
    private JButton SearchFactBtn;
    private JButton reporteButton;
    private JTextField Fecha;
    private JButton SearchFechaBtn;
    private JButton reporteButton1;
    private JLabel FechaLbl;
    private JLabel NumFactLbl;
    private JTextField NumFact;
    private JTable list;
    private JLabel DoubleClickLbl;
    private JPanel panel;

    public View() {


        //Listener para que cuando haga doble click, muestre los detalles.
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    int row = list.getSelectedRow();
                    if(row >= 0){
                     //Factura facturaAt = ;                //No se si esto es del controller, pero falta asignar la factura seleccionada a un nuevo objeto
                     //JOptionPane.showMessageDialog(panel,facturaAt.toString(),"",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }















}
