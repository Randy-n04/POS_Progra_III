package pos.presentation.facturas;

import pos.Application; // Asegúrate de que esta importación sea correcta
import pos.logic.Factura;
import pos.logic.Service;
import pos.logic.Lines; // Asegúrate de que esta importación sea correcta
import java.util.List;
import pos.logic.Cliente;
import pos.logic.Cajero;


public class Controller {
    private View view;
    private Model model;

    public Controller(View view, Model model) {
        model.init(Service.instance().search(new Factura()));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);

        // Cargar clientes y cajeros en los combo box
        List<Cliente> clientes = Service.instance().search(new Cliente());
        List<Cajero> cajeros = Service.instance().search(new Cajero());

        view.cargarClientes(clientes);  // Llenar el clienteBox con los clientes
        view.cargarCajeros(cajeros);    // Llenar el cajeroBox con los cajeros

    }


    public void search(Factura filter) throws Exception {
        model.setFilter(filter);
        model.setMode(Application.MODE_CREATE);
        model.setCurrent(new Factura());
        model.setList(Service.instance().search(model.getFilter()));
    }

    public void save(Factura e) throws Exception {
        switch (model.getMode()) {
            case Application.MODE_CREATE:
                Service.instance().create(e.getCliente());
                break;
            case Application.MODE_EDIT:
                Service.instance().update(e);
                break;
        }
        model.setFilter(new Factura());
        search(model.getFilter());
    }

    public void edit(int row) {
        Factura e = model.getList().get(row);
        try {
            model.setMode(Application.MODE_EDIT);
            model.setCurrent(Service.instance().read(e));
        } catch (Exception ex) {
            // Manejar excepción
        }
    }

    public void delete() throws Exception {
        Service.instance().delete(model.getCurrent());
        search(model.getFilter());
    }

    public void clear() {
        model.setMode(Application.MODE_CREATE);
        model.setCurrent(new Factura());
    }

    public void openCobrarDialog() {
        Cobrar dialog = new Cobrar();
        dialog.pack();
        dialog.setVisible(true);
        // Aquí puedes manejar el pago realizado si es necesario
    }

    public void openBuscarDialog() {
        // Asegúrate de pasar una instancia válida de Lines
        Lines lines = new Lines(); // Asegúrate de que esta instancia sea válida en tu proyecto
        Buscar dialog = new Buscar(lines);
        dialog.pack();
        dialog.setVisible(true);
        // Aquí puedes manejar los resultados de búsqueda si es necesario
    }

    public void openCantidadDialog() {
        Cantidad dialog = new Cantidad();
        dialog.pack();
        dialog.setVisible(true);
        int cantidad = dialog.getCantidadIngresada();
        // Aquí puedes manejar la cantidad ingresada si es necesario
    }

    public void openDescuentoDialog(){
        Descuento dialog = new Descuento();
        dialog.pack();
        dialog.setVisible(true);
        float descuento = dialog.getDiscountValue();
        // Aqui puedes manejar el descuento ingresado si es necesario
    }
}


