package pos;

import pos.logic.Service;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
// da
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


    public static JFrame window;

    public final static int MODE_CREATE=1;
    public final static int MODE_EDIT=2;

    public static Border BORDER_ERROR = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);
}
