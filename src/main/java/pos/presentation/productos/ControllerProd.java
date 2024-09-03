package pos.presentation.productos;

import pos.Application;
import pos.logic.Producto;
import pos.logic.Service;
import pos.presentation.productos.ControllerProd;
import pos.presentation.productos.ModelProd;
import pos.presentation.productos.TableModelProd;

import javax.swing.table.TableColumnModel;
import java.beans.PropertyChangeEvent;
import java.util.List;

public class ControllerProd {
    ViewProd view;
    ModelProd model;

    public ControllerProd(ViewProd view, ModelProd model) {
        model.init(Service.instance().search(new Producto()));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void search(Producto filter) throws Exception {
        model.setFilter(filter);
        model.setMode(Application.MODE_CREATE);
        model.setCurrent(new Producto());
        model.setList(Service.instance().search(model.getFilter()));
    }

    public void save(Producto e) throws Exception {
        switch (model.getMode()) {
            case Application.MODE_CREATE:
                Service.instance().create(e);
                break;
            case Application.MODE_EDIT:
                Service.instance().update(e);
                break;
        }
        model.setFilter(new Producto());
        search(model.getFilter());
    }

    public void edit(int row) {
        Producto e = model.getList().get(row);
        try {
            model.setMode(Application.MODE_EDIT);
            model.setCurrent(Service.instance().read(e));
        } catch (Exception ex) {
        }
    }

    public void delete() throws Exception {
        Service.instance().delete(model.getCurrent());
        search(model.getFilter());
    }

    public void clear() {
        model.setMode(Application.MODE_CREATE);
        model.setCurrent(new Producto());
    }
}