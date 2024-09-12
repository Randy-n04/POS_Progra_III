package pos.presentation.facturas;

import javax.swing.*;
import java.awt.event.*;

public class Cobrar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField efectivo;
    private JTextField tarjeta;
    private JTextField cheque;
    private JTextField sinpe;
    private JLabel sinpeLbl;
    private JLabel chequeLbl;
    private JLabel TarjetaLbl; // Asegúrate de que este nombre coincida con el archivo .form
    private JLabel efectivoLbl;
    private JLabel importe;

    public Cobrar() {
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
    }

    private void onOK() {
        try {
            float efectivoValue = Float.parseFloat(efectivo.getText());
            float tarjetaValue = Float.parseFloat(tarjeta.getText());
            float chequeValue = Float.parseFloat(cheque.getText());
            float sinpeValue = Float.parseFloat(sinpe.getText());

            // Aquí puedes agregar la lógica para procesar los datos de pago
            processPayment(efectivoValue, tarjetaValue, chequeValue, sinpeValue);

            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        dispose();
    }

    private void processPayment(float efectivo, float tarjeta, float cheque, float sinpe) {
        float totalPago = efectivo + tarjeta + cheque + sinpe;
        System.out.println("Total de pago: " + totalPago);
    }

    public float getEfectivo() {
        return Float.parseFloat(efectivo.getText());
    }

    public float getTarjeta() {
        return Float.parseFloat(tarjeta.getText());
    }

    public float getCheque() {
        return Float.parseFloat(cheque.getText());
    }

    public float getSinpe() {
        return Float.parseFloat(sinpe.getText());
    }

    public static void main(String[] args) {
        Cobrar dialog = new Cobrar();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}

