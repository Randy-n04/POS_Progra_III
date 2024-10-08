package pos.presentation.productos;

import pos.data.Data;
import pos.data.XmlPersister;
import pos.Application;
import pos.logic.Categoria;
import pos.logic.Producto;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.List;

public class ViewProd implements PropertyChangeListener {
        private JPanel panelGen;
        private JLabel codigoLbl;
        private JTextField codigo;
        private JLabel descripcionLbl;
        private JTextField descripcion;
        private JLabel unidadMedidaLbl;
        private JTextField unidadMedida;
        private JLabel precioUnidadLbl;
        private JTextField precioUnidad;
        private JLabel existenciasLbl;
        private JTextField existencias;
        private JButton save;
        private JButton delete;
        private JButton clear;
        private JLabel searchDescripcionLbl;
        private JTextField searchDescripcion;
        private JButton reportDescripcionBtn;
        private JButton searchDescripcionBtn;
        private JTable list;
        private JComboBox categorias;
        private JLabel categoriaLbl;
        private JLabel searchCodigoLbl;
        private JTextField searchCodigo;
        private JButton searchCodigoBtn;
        private JButton reportCodigoBtn;

        public JPanel getPanelGen(){return panelGen;}

        public ViewProd(){
            reportDescripcionBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        controller.report();
                        if(Desktop.isDesktopSupported()){
                            File myFile = new File("productos.pdf");
                            Desktop.getDesktop().open(myFile);
                        }
                    }catch (Exception ex){ }
                }
            });
            reportCodigoBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        controller.report();
                        if(Desktop.isDesktopSupported()) {
                            File myFile = new File("productos.pdf");
                            Desktop.getDesktop().open(myFile);
                        }
                    }catch (Exception ex){  }
                }
            });
            searchDescripcionBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Producto filter = new Producto();
                        filter.setDescripcion(searchDescripcion.getText());
                        controller.search(filter);
                    } catch(Exception ex){
                        JOptionPane.showMessageDialog(panelGen, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });

            searchCodigoBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Producto filter = new Producto();
                        filter.setCodigo(searchCodigo.getText());
                        controller.search(filter);
                    } catch(Exception ex){
                        JOptionPane.showMessageDialog(panelGen, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });

            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(validate()){
                        Producto n = take();
                        try{
                            controller.save(n);
                            JOptionPane.showMessageDialog(panelGen, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception ex){
                            JOptionPane.showMessageDialog(panelGen, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });

            list.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = list.getSelectedRow();
                    controller.edit(row);
                }
            });

            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        controller.delete();
                        JOptionPane.showMessageDialog(panelGen, "REGISTRO BORRADO", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panelGen, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            clear.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.clear();
                }
            });

            categorias.setEditable(false);
            categorias.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Categoria selectedCat = (Categoria) categorias.getSelectedItem();
                    System.out.println("Selected Category: " + selectedCat.getNombre());
                }
            });
            try {
                Data data = XmlPersister.instance().load();
                List<Categoria> categoriasData = data.getCategorias();
                DefaultComboBoxModel<Categoria> model = new DefaultComboBoxModel<>();
                for (Categoria categoria : categoriasData) {
                    model.addElement(categoria);
                }
                categorias.setModel(model);
            } catch (Exception e){
                JOptionPane.showMessageDialog(panelGen, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private boolean validate() {
            boolean valid = true;
            if (codigo.getText().isEmpty()) {
                valid = false;
                codigoLbl.setBorder(Application.BORDER_ERROR);
                codigoLbl.setToolTipText("Codigo requerido");
            } else {
                codigoLbl.setBorder(null);
                codigoLbl.setToolTipText(null);
            }

            if (descripcion.getText().isEmpty()) {
                valid = false;
                descripcion.setBorder(Application.BORDER_ERROR);
                descripcionLbl.setToolTipText("Descripcion requerida");
            } else {
                descripcionLbl.setBorder(null);
                descripcionLbl.setToolTipText(null);
            }

            if (unidadMedida.getText().isEmpty()) {
                valid = false;
                unidadMedidaLbl.setBorder(Application.BORDER_ERROR);
                unidadMedidaLbl.setToolTipText("Unidad de medida requerida");
            } else {
                unidadMedidaLbl.setBorder(null);
                unidadMedidaLbl.setToolTipText(null);
            }

            try {
                Float.parseFloat(precioUnidad.getText());
                precioUnidadLbl.setBorder(null);
                precioUnidadLbl.setToolTipText(null);
            } catch(Exception e) {
                valid = false;
                precioUnidadLbl.setBorder(Application.BORDER_ERROR);
                precioUnidadLbl.setToolTipText("Precio Unidad invalido");
            }

            try{
                Integer.parseInt(existencias.getText());
                existenciasLbl.setBorder(null);
                existenciasLbl.setToolTipText(null);
            } catch(NumberFormatException ex){
                valid = false;
                existenciasLbl.setBorder(Application.BORDER_ERROR);
                existenciasLbl.setToolTipText("Existencias invalida");
            }

            if (categorias.getSelectedItem() == null) {
                valid = false;
                categoriaLbl.setBorder(Application.BORDER_ERROR);
                categoriaLbl.setToolTipText("Categoria invalida");
            } else {
                categoriaLbl.setBorder(null);
                categoriaLbl.setToolTipText(null);
            }


            return valid;
        }

        public Producto take() {
            Producto e = new Producto();
            e.setCodigo(codigo.getText());
            e.setDescripcion(descripcion.getText());
            e.setUnidadMedida(unidadMedida.getText());
            e.setPrecioUnitario(Float.parseFloat(precioUnidad.getText()));
            e.setExistencias(Integer.parseInt(existencias.getText()));
            Categoria selectedCat = (Categoria) categorias.getSelectedItem();
            if(selectedCat != null){
                e.setCategoria(selectedCat.getNombre());
            }
            return e;
        }

        // MVC
        ModelProd model;
        ControllerProd controller;

        public void setModel(ModelProd model) {
            this.model = model;
            model.addPropertyChangeListener(this);
        }

        public void setController(ControllerProd controller) {
            this.controller = controller;
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            switch (evt.getPropertyName()) {
                case ModelProd.LIST:
                    int[] cols = {TableModelProd.CODIGO, TableModelProd.DESCRIPCION, TableModelProd.UNIDADMEDIDA, TableModelProd.PRECIOUNITARIO, TableModelProd.EXISTENCIAS, TableModelProd.CATEGORIA};
                    list.setModel(new TableModelProd(cols, model.getList()));
                    list.setRowHeight(30);
                    TableColumnModel columnModel = list.getColumnModel();
                    columnModel.getColumn(1).setPreferredWidth(150);
                    columnModel.getColumn(3).setPreferredWidth(150);
                    break;
                case ModelProd.CURRENT:
                    codigo.setText(model.getCurrent().getCodigo());
                    descripcion.setText(model.getCurrent().getDescripcion());
                    unidadMedida.setText(model.getCurrent().getUnidadMedida());
                    precioUnidad.setText("" + model.getCurrent().getPrecioUnitario());
                    existencias.setText("" + model.getCurrent().getExistencias());
                    String nombreCat = model.getCurrent().getCategoria();
                    if(nombreCat != null){
                        for(int i = 0; i < categorias.getItemCount(); i++){
                            Categoria cat = (Categoria) categorias.getItemAt(i);
                            if(cat.getNombre().equals(nombreCat)){
                                categorias.setSelectedIndex(i);
                                break;
                            }
                        }
                    }
                    if (model.getMode() == Application.MODE_EDIT) {
                        codigo.setEnabled(false);
                        delete.setEnabled(true);
                    } else {
                        codigo.setEnabled(true);
                        delete.setEnabled(false);
                    }

                    codigoLbl.setBorder(null);
                    codigoLbl.setToolTipText(null);
                    descripcionLbl.setBorder(null);
                    descripcionLbl.setToolTipText(null);
                    unidadMedidaLbl.setBorder(null);
                    unidadMedidaLbl.setToolTipText(null);
                    precioUnidadLbl.setBorder(null);
                    precioUnidadLbl.setToolTipText(null);
                    existenciasLbl.setBorder(null);
                    existenciasLbl.setToolTipText(null);
                    break;
                case ModelProd.FILTER:
                    searchDescripcion.setText(model.getFilter().getDescripcion());
                    searchCodigo.setText(model.getFilter().getCodigo());
                    break;
            }

            this.panelGen.revalidate();
        }

    }
