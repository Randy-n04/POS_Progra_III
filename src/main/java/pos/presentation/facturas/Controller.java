package pos.presentation.facturas;

import pos.Application;
import pos.data.XmlPersister;
import pos.logic.*;

import java.util.List;


public class Controller {
    private View view;
    private Model model;

    public Controller(View view, Model model) throws Exception {
        try {
            List<Linea> lineas = Service.instance().getLineas();
            List<Cliente> clientes = XmlPersister.instance().load().getClientes();
            List<Cajero> cajeros = XmlPersister.instance().load().getCajeros();

            if (lineas != null && clientes != null && cajeros != null) {
                model.init(lineas, clientes, cajeros);
                if (!lineas.isEmpty()) {
                    model.setCurrent(lineas.get(0));
                }
            } else {
                System.out.println("listas nulas");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void search(Linea filter) throws Exception {
        model.setFilter(filter);
        model.setMode(Application.MODE_CREATE);
        model.setCurrent(new Linea());
        model.setList(Service.instance().search(model.getFilter()));
    }

    public void init(){
        try{
            List<Cliente> clientes = XmlPersister.instance().load().getClientes();
            List<Cajero> cajeros = XmlPersister.instance().load().getCajeros();

            model.init(Service.instance().getLineas(), clientes , cajeros);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void save(Factura e) throws Exception {
        try {
            switch (model.getMode()) {
                case Application.MODE_CREATE:
                    Service.instance().create(e);
                    break;
            }
            model.setFilter(new Linea());
            search(model.getFilter());
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void edit(int row) {
        Linea e = model.getList().get(row);
        try {
            model.setMode(Application.MODE_EDIT);
            model.setCurrent(Service.instance().read(e));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete() throws Exception {
        Service.instance().delete(model.getCurrent());
        search(model.getFilter());
    }

    public void clear() {
        model.setMode(Application.MODE_CREATE);
        model.setCurrent(new Linea());
    }

    public void openCobrarDialog(float total) {
        Cobrar dialog = new Cobrar(total);
        dialog.pack();
        dialog.setVisible(true);
    }

    public void openCantidadDialog() {
        Cantidad dialog = new Cantidad();
        dialog.pack();
        dialog.setVisible(true);
        int cantidad = dialog.getCantidadIngresada();
    }

    public void openDescuentoDialog(){
        Descuento dialog = new Descuento();
        dialog.pack();
        dialog.setVisible(true);
        float descuento = dialog.getDiscountValue();
    }

    public void openBuscarDialog() {
        Buscar dialog = new Buscar();
        dialog.pack();
        dialog.setVisible(true);
    }

}