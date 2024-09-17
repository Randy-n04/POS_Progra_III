package pos;
//prueba y error
import pos.logic.Service;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
// da
public class Application {
    public static void main(String[] args) throws Exception {
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
        pos.presentation.facturas.Model facturasModel= new pos.presentation.facturas.Model();
        pos.presentation.facturas.View facturasView = new pos.presentation.facturas.View();
        facturasController = new pos.presentation.facturas.Controller(facturasView, facturasModel);
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
        pos.presentation.productos.ModelProd productosModel= new pos.presentation.productos.ModelProd();
        pos.presentation.productos.ViewProd productosView = new pos.presentation.productos.ViewProd();
        productosController = new pos.presentation.productos.ControllerProd(productosView, productosModel);
        Icon productosIcon= new ImageIcon(Application.class.getResource("/pos/presentation/icons/product.png"));

        tabbedPane.addTab("Productos  ",productosIcon,productosView.getPanelGen());

        //----------------------------------------Historico----------------------------------------------------
        pos.presentation.historico.Model historicoModel = new pos.presentation.historico.Model();
        pos.presentation.historico.View historicoView = new pos.presentation.historico.View();
        historicoController = new pos.presentation.historico.Controller(historicoView, historicoModel);
        Icon historicoIcon = new ImageIcon(Application.class.getResource("/pos/presentation/icons/historico.png"));

        tabbedPane.addTab("Historico  ", historicoIcon, historicoView.getPanel());

        window.setSize(900,450);
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

    public static pos.presentation.historico.Controller historicoController;



    public static JFrame window;

    public final static int MODE_CREATE=1;
    public final static int MODE_EDIT=2;

    public static Border BORDER_ERROR = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
}
