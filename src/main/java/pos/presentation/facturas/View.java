package pos.presentation.facturas;

import pos.Application;
import pos.presentation.clientes.Controller;
import pos.presentation.clientes.Model;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View {
    private JPanel panel;
    private JComboBox clienteBox;
    private JLabel clienteIcon;
    private JComboBox cajeroBox;
    private JLabel cajeroIcon;
    private JLabel articulosLbl;
    private JLabel articulosResultado;
    private JLabel subtotalLbl;
    private JLabel subtotalResultado;
    private JLabel descuentosLbl;
    private JLabel descuentosResultados;
    private JLabel totalLbl;
    private JLabel totalResultado;
    private JButton cobrarBtn;
    private JButton buscarBtn;
    private JButton cantidadBtn;
    private JButton quitarBtn;
    private JButton descuentoBtn;
    private JButton cancelarBtn;
    private JTextField textField1;
    private JButton agregarBtn;
    private JTable productosTbl;

    public JPanel getPanel(){return panel;}

    public View(){

    }

    // MVC
    pos.presentation.facturas.Model model;
    pos.presentation.facturas.Controller controller;

    public void setModel(pos.presentation.facturas.Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(pos.presentation.facturas.Controller controller) {
        this.controller = controller;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case pos.presentation.facturas.Model.LIST:
                int[] cols = {pos.presentation.facturas.TableModel.CODIGO,
                        pos.presentation.facturas.TableModel.ARTICULO, pos.presentation.facturas.TableModel.CATEGORIA,
                        pos.presentation.facturas.TableModel.CANTIDAD, pos.presentation.facturas.TableModel.PRECIO,
                        pos.presentation.facturas.TableModel.DESCUENTO, pos.presentation.facturas.TableModel.NETO,
                        pos.presentation.facturas.TableModel.IMPORTE,};
                productosTbl.setModel(new TableModel(cols, model.getList()));
                productosTbl.setRowHeight(30);
                TableColumnModel columnModel = productosTbl.getColumnModel();
                columnModel.getColumn(1).setPreferredWidth(150);
                columnModel.getColumn(3).setPreferredWidth(150);
                break;
        }

        this.panel.revalidate();
    }

}
