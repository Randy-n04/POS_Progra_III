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

    public Cobrar(float importeTotal) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // Configura el valor del campo Importe
        importe.setText(String.format("%.2f", importeTotal));

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtén el valor del importe
                    float importeFin = Float.parseFloat(importe.getText().trim());

                    // Suma los valores de los JTextField
                    float efectivoFin = parseFloatSafe(efectivo.getText().trim());
                    float tarjetaFin = parseFloatSafe(tarjeta.getText().trim());
                    float chequeFin = parseFloatSafe(cheque.getText().trim());
                    float sinpeFin = parseFloatSafe(sinpe.getText().trim());

                    float suma = efectivoFin + tarjetaFin + chequeFin + sinpeFin;

                    // Verifica si la suma es igual al importe
                    if (Math.abs(suma - importeFin) < 0.01) { // Usar una tolerancia para evitar errores de precisión
                        JOptionPane.showMessageDialog(null, "La suma es correcta.");
                        onOK(); // Llama al método onOK para procesar el pago
                    } else {
                        JOptionPane.showMessageDialog(null, "La suma de los montos no coincide con el importe.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
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

        // Inicializar los JTextField en 0
        efectivo.setText("0");
        tarjeta.setText("0");
        cheque.setText("0");
        sinpe.setText("0");
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

    private float parseFloatSafe(String text) {
        try {
            return Float.parseFloat(text);
        } catch (NumberFormatException e) {
            return 0; // Retorna 0 si hay un error en el formato
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
}