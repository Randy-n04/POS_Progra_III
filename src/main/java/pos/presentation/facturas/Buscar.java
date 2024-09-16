package pos.presentation.facturas;

import javax.swing.*;

import pos.logic.Linea;
import pos.logic.Producto;
import pos.presentation.productos.ControllerProd;
import pos.presentation.productos.ModelProd;
import pos.presentation.productos.TableModelProd;

import java.awt.event.*;
import java.beans.PropertyChangeListener;

public class Buscar extends JDialog  {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField descripcion;
    private JTable table1;
    private JLabel descripcionLbl;
    public Buscar() {

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    public static void main(String[] args) {
        Buscar dialog = new Buscar();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}