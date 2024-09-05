package pos.presentation.factura;

import pos.Application;
import pos.logic.Factura;
import pos.logic.Service;

    public class Controller {
        ViewFactura view;
        ModelFactura model;

        public Controller(ViewFactura view, ModelFactura model) {
            model.init(Service.instance().search(new Factura()));
            this.view = view;
            this.model = model;
            view.setController(this);
            view.setModel(model);
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
                    Service.instance().create(e);
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
                // Manejar excepciones si es necesario
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

}
