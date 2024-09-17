package pos.presentation.facturas;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import static java.lang.Float.parseFloat;

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
    private JLabel TarjetaLbl;
    private JLabel efectivoLbl;
    private JLabel importe;

    private final DecimalFormat decimalFormat;

    public Cobrar(float importeTotal) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // Configura el formato decimal
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        decimalFormat = new DecimalFormat("#,##0.00", symbols);

        // Configura el valor del campo Importe
        importe.setText(decimalFormat.format(importeTotal));

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtén el valor del importe
                    Number importeNumber = decimalFormat.parse(importe.getText().trim());
                    float importeFin = importeNumber.floatValue();

                    // Suma los valores de los JTextField
                    float efectivoFin = parseFloatSafe(efectivo.getText().trim());
                    float tarjetaFin = parseFloatSafe(tarjeta.getText().trim());
                    float chequeFin = parseFloatSafe(cheque.getText().trim());
                    float sinpeFin = parseFloatSafe(sinpe.getText().trim());

                    float suma = efectivoFin + tarjetaFin + chequeFin + sinpeFin;

                    // Verifica si la suma es igual al importe
                    if (Math.abs(suma - importeFin) < 0.01) { // Usar una tolerancia para evitar errores de precisión
                        JOptionPane.showMessageDialog(contentPane, "Pago hecho...");
                        dispose(); // Cierra el diálogo actual
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "El pago no coincide...");
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(contentPane, "Por favor ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra el diálogo actual
            }
        });
    }

    private float parseFloatSafe(String text) {
        try {
            return Float.parseFloat(text);
        } catch (NumberFormatException e) {
            return 0; // Retorna 0 si hay un error en el formato
        }
    }

    public double getEfectivo() {
        return parseFloatSafe(efectivo.getText());
    }

    public double getTarjeta() {
        return parseFloatSafe(tarjeta.getText());
    }

    public double getCheque() {
        return parseFloatSafe(cheque.getText());
    }

    public double getSinpe() {
        return parseFloatSafe(sinpe.getText());
    }
}