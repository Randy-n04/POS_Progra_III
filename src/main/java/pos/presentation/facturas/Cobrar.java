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
    private boolean pagoRealizado = true;

    private final DecimalFormat decimalFormat;

    public Cobrar(float importeTotal) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        decimalFormat = new DecimalFormat("#,##0.00", symbols);

        importe.setText(decimalFormat.format(importeTotal));

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Number importeNumber = decimalFormat.parse(importe.getText().trim());
                    float importeFin = importeNumber.floatValue();

                    float efectivoFin = parseFloatSafe(efectivo.getText().trim());
                    float tarjetaFin = parseFloatSafe(tarjeta.getText().trim());
                    float chequeFin = parseFloatSafe(cheque.getText().trim());
                    float sinpeFin = parseFloatSafe(sinpe.getText().trim());

                    float suma = efectivoFin + tarjetaFin + chequeFin + sinpeFin;

                    if (Math.abs(suma - importeFin) < 0.01) {
                        JOptionPane.showMessageDialog(contentPane, "Pago hecho...");
                        pagoRealizado = true;
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "El pago no coincide...");
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(contentPane, "Por favor ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pagoRealizado = false;
                dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                pagoRealizado = false;
                dispose();
            }
        });

    }

    public boolean isPagoRealizado() {
        return pagoRealizado;
    }

    private void confirmarPago(){
        pagoRealizado = true;
        this.dispose();
    }

    private float parseFloatSafe(String text) {
        try {
            return Float.parseFloat(text);
        } catch (NumberFormatException e) {
            return 0;
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