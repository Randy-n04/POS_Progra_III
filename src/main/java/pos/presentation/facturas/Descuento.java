package pos.presentation.facturas;

import javax.swing.*;
import java.awt.event.*;

public class Descuento extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField descuento;
    private JLabel descuentoLbl;
    private float discountValue;

    public Descuento() {
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
        String descuentoText = descuento.getText();
        try {
            float descuentoValue = Float.parseFloat(descuentoText);
            if (descuentoValue < 0 || descuentoValue > 100) {
                JOptionPane.showMessageDialog(this, "El descuento debe estar entre 0 y 100", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            processDiscount(descuentoValue);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        dispose();
    }

    private void processDiscount(float discountValue) {
        this.discountValue = discountValue;
        // Implementa la lógica para notificar al controlador o modelo
        // Por ejemplo, podrías usar un método de callback o pasar el valor al controlador
        System.out.println("Descuento procesado: " + discountValue);
        // Notificar al controlador
        // controller.setDiscount(discountValue);
    }

    public float getDiscountValue() {
        return discountValue;
    }

    public static void main(String[] args) {
        Descuento dialog = new Descuento();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}


