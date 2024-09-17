package pos.presentation.facturas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import pos.logic.Producto;

import java.awt.event.*;
import java.util.List;

public class Buscar extends JDialog  {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField descripcion;
    private JTable table1;
    private JLabel descripcionLbl;
    private Controller controller;

    public Buscar(Controller controller) {
        setContentPane(contentPane);
        setModal(true);
        this.controller = controller;
        actualizarTab();
        
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarTab();
            }
        });
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProd();
            }

        });
    }
    
    public Controller getController() {
        return controller;
    }
    
    public void actualizarTab() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Codigo", "Descripcion",
                "Precio", "Cantidad", "Existencias"}, 0);
        table1.setModel(model);
        for(Producto prod : getController().ListaProd()){
            model.addRow(new Object[]{prod.getCodigo(),prod.getDescripcion(),prod.getPrecioUnitario(), prod.getExistencias()})
        }
        
    }

    private void buscarProd(){
        String desc = descripcion.getText();
        Producto filtro = new Producto();
        if(descripcion != null){
            filtro.setDescripcion(desc);
        } else{
            JOptionPane.showMessageDialog(null, "La descripción no puede estar vacía");
            return;
        }
        try{
            List<Producto> productos = getController().search(filtro);
            if(productos.isEmpty()){
                throw new Exception(("No hay productos con la descripcion"));
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                model.setRowCount(0);
                for(Producto prod: productos){
                    model.addRow(new Object[]{
                        prod.getCodigo(),
                        prod.getDescripcion(),
                        prod.getPrecioUnitario(), 
                        prod.getExistencias()
                    });
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }

    public static void main(String[] args) {
        Buscar dialog = new BuscarProd();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }}