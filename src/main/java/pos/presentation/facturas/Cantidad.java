package pos.presentation.facturas;

import javax.swing.*;
import java.awt.event.*;

public class Cantidad extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel cantidadLbl;
    private JTextField cantidadText;
    private JPanel cantidad;
    private int cantidadIngresada;

    public Cantidad() {
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
    }

    private void onOK() {
        try {
            cantidadIngresada = Integer.parseInt(cantidadText.getText());
            if (cantidadIngresada <= 0) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un número mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                dispose();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        dispose();
    }

    public int getCantidadIngresada() {
        return cantidadIngresada;
    }

    public static void main(String[] args) {
        Cantidad dialog = new Cantidad();
        dialog.pack();
        dialog.setVisible(true);
        System.out.println("Cantidad ingresada: " + dialog.getCantidadIngresada());
        System.exit(0);
    }
}

