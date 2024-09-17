package pos;
import pos.logic.Service;
import pos.presentation.facturas.Controller;
import pos.presentation.facturas.Model;
import pos.presentation.facturas.View;
import pos.presentation.productos.ControllerProd;
import pos.presentation.productos.ModelProd;
import pos.presentation.productos.ViewProd;
import pos.presentation.estadisticas.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Application {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception ex) {};

        window = new JFrame();
        JTabbedPane tabbedPane = new JTabbedPane();
        window.setContentPane(tabbedPane);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Service.instance().stop();
            }
        });
        //-----------------------------------------Facturas------------------------------------------------
        Model facturasModel= new Model();
        View facturasView = new View();
        facturasController = new Controller(facturasView, facturasModel);
        Icon facturasIcon= new ImageIcon(Application.class.getResource("/pos/presentation/icons/invoice.png"));

        tabbedPane.addTab("Facturas  ",facturasIcon,facturasView.getPanel());

        //-----------------------------------------Cliente------------------------------------------------
        pos.presentation.clientes.Model clientesModel= new pos.presentation.clientes.Model();
        pos.presentation.clientes.View clientesView = new pos.presentation.clientes.View();
        clientesController = new pos.presentation.clientes.Controller(clientesView,clientesModel);
        Icon clientesIcon= new ImageIcon(Application.class.getResource("/pos/presentation/icons/client.png"));

        tabbedPane.addTab("Clientes  ",clientesIcon,clientesView.getPanel());


        //-----------------------------------------Cajero-------------------------------------------------------
        pos.presentation.cajero.Model cajeroModel = new pos.presentation.cajero.Model();
        pos.presentation.cajero.View cajeroView = new pos.presentation.cajero.View();
        cajerosController = new pos.presentation.cajero.Controller(cajeroView,cajeroModel);
        Icon cajerosIcon = new ImageIcon(Application.class.getResource("/pos/presentation/icons/cashier.png"));

        tabbedPane.addTab("Cajeros  ",cajerosIcon,cajeroView.getPanel());

        //----------------------------------------Productos-----------------------------------------------------
        ModelProd productosModel= new ModelProd();
        ViewProd productosView = new ViewProd();
        productosController = new ControllerProd(productosView, productosModel);
        Icon productosIcon= new ImageIcon(Application.class.getResource("/pos/presentation/icons/product.png"));

        tabbedPane.addTab("Productos  ",productosIcon,productosView.getPanelGen());

        //---------------------------------------Estadistica------------------------------------------------------
        pos.presentation.estadisticas.Model estModel = new pos.presentation.estadisticas.Model();
        pos.presentation.estadisticas.View estView = new pos.presentation.estadisticas.View();
        pos.presentation.estadisticas.Controller estadisticasController = new pos.presentation.estadisticas.Controller(estView,estModel);
        Icon estadisticasIcon = new ImageIcon(Application.class.getResource("/pos/presentation/icons/chart.png"));

        tabbedPane.addTab("Estadisticas ",estadisticasIcon,estView.getPanel());


        window.setSize(1000,500);
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setIconImage((new ImageIcon(Application.class.getResource("presentation/icons/icon.png"))).getImage());
        window.setTitle("POS: Point Of Sale");
        window.setVisible(true);
    }

    public static pos.presentation.clientes.Controller clientesController;

    public static pos.presentation.cajero.Controller cajerosController;

    public static pos.presentation.productos.ControllerProd productosController;

    public static pos.presentation.facturas.Controller facturasController;


    public static JFrame window;

    public final static int MODE_CREATE=1;
    public final static int MODE_EDIT=2;

    public static Border BORDER_ERROR = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
}
