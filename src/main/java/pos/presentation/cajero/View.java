package pos.presentation.cajero;

import pos.Application;
import pos.logic.Cajero;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class View implements PropertyChangeListener {
    private JPanel panel;
    private JPanel Listado;
    private JPanel Búsqueda;
    private JPanel Cajero;
    private JTextField searchNombre;
    private JButton report;
    private JButton search;
    private JLabel searchNombreLbl;
    private JTable list;
    private JButton save;
    private JButton delete;
    private JButton clear;
    private JTextField ID;
    private JTextField Nombre;
    private JLabel IDlbl;
    private JLabel Nombrelbl;

    public JPanel getPanel() {
        return panel;
    }

    public View() {
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Cajero filter = new Cajero();
                    filter.setNombre(searchNombre.getText());
                    controller.search(filter);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Cajero n = take();
                    try {
                        controller.save(n);
                        JOptionPane.showMessageDialog(panel, "REGISTRO APLICADO", "", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(panel, "REGISTRO BORRADO", "", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clear();
            }
        });
    }

    private boolean validate() {
        boolean valid = true;
        if (ID.getText().isEmpty()) {
            valid = false;
            IDlbl.setBorder(Application.BORDER_ERROR);
            IDlbl.setToolTipText("Codigo requerido");
        } else {
            IDlbl.setBorder(null);
            IDlbl.setToolTipText(null);
        }

        if (Nombre.getText().isEmpty()) {
            valid = false;
            Nombrelbl.setBorder(Application.BORDER_ERROR);
            Nombrelbl.setToolTipText("Nombre requerido");
        } else {
            Nombrelbl.setBorder(null);
            Nombrelbl.setToolTipText(null);
        }

        return valid;
    }

    public Cajero take() {
        Cajero e = new Cajero();
        e.setId(ID.getText());
        e.setNombre(Nombre.getText());
        return e;
    }


    //MVC---------------------------------
    Model model;
    Controller controller;


    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.LIST:
                int[] cols = {TableModel.ID, TableModel.NOMBRE};
                list.setModel(new TableModel(cols, model.getList()));
                list.setRowHeight(30);
                TableColumnModel columnModel = list.getColumnModel();
                columnModel.getColumn(1).setPreferredWidth(150);
                break;
            case Model.CURRENT:
                ID.setText(model.getCurrent().getId());
                Nombre.setText(model.getCurrent().getNombre());

                if(model.getMode() == Application.MODE_EDIT){
                    ID.setEnabled(false);
                    delete.setEnabled(true);
                }else{
                    ID.setEnabled(true);
                    delete.setEnabled(false);
                }
                IDlbl.setBorder(null);
                IDlbl.setToolTipText(null);
                Nombrelbl.setBorder(null);
                Nombrelbl.setToolTipText(null);
                break;
            case Model.FILTER:
                searchNombre.setText(model.getFilter().getNombre());
                break;
        }
        this.panel.revalidate();
    }
}
