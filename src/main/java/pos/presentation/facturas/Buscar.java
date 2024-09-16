package pos.presentation.facturas;

import javax.swing.*;

import pos.logic.Linea;
import pos.presentation.productos.ModelProd;
import pos.presentation.productos.TableModelProd;

import java.awt.event.*;

public class Buscar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField descripcion;
    private JTable table1;
    private JLabel descripcionLbl;
    private Linea linea; // Instancia de Lines
    private Model model; // Referencia al model
    private Controller controller; // Variable de instancia para el controlador

    public Buscar(){
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        int[] cols = {TableModelProd.CODIGO,
                TableModelProd.DESCRIPCION,
                TableModelProd.UNIDADMEDIDA,
                TableModelProd.PRECIOUNITARIO,
                TableModelProd.EXISTENCIAS,
                TableModelProd.CATEGORIA};
        ModelProd model = null;
        table1.setModel(new TableModelProd(cols, model.getList()));
    }
}