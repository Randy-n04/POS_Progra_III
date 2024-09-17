package pos.data;

import pos.logic.*;
import jakarta.xml.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    private Integer facturasConsecutivo = 1;
    private Integer lineasConsecutivo = 1;

    //-------------CLIENTES-------------
    @XmlElementWrapper(name = "clientes")
    @XmlElement(name = "cliente")
    private List<Cliente> clientes;

    //-------------CAJEROS-------------
    @XmlElementWrapper(name = "cajeros")
    @XmlElement(name = "cajero")
    private List<Cajero> cajeros;

    //-------------CATEGORIAS------------
    @XmlElementWrapper(name = "categorias")
    @XmlElement(name = "categoria")
    private List<Categoria> categorias;

    //-------------PRODUCTOS-------------
    @XmlElementWrapper(name = "productos")
    @XmlElement(name = "producto")
    private List<Producto> productos;

    //-------------FACTURAS-------------
    @XmlElementWrapper(name = "facturas")
    @XmlElement(name = "factura")
    private List<Factura> facturas;

    //--------------Lineas--------------
    @XmlElementWrapper(name = "lineas")
    @XmlElement(name = "linea")
    private List<Linea> lineas;

    public Data() {
        clientes = new ArrayList<>();
        cajeros = new ArrayList<>();
        categorias = new ArrayList<>();
        productos = new ArrayList<>();
        facturas = new ArrayList<>();
        lineas = new ArrayList<>();
    }

    public String nextFactura(){
        DecimalFormat df = new DecimalFormat("000000");
        return "FAC-" + df.format(facturasConsecutivo++);
    }

    public String nextLinea(){
        DecimalFormat df = new DecimalFormat("000000");
        return "LIN-" + df.format(lineasConsecutivo++);
    }
    
    public List<Cliente> getClientes() {
        return clientes;
    }  
    public List<Cajero> getCajeros() {
      return cajeros;
    }
    public List<Categoria> getCategorias() { return categorias; }
    public List<Producto> getProductos() {
        return productos;
    }
    public List<Factura> getFacturas() { return facturas; }
    public List<Linea> getLineas() { return lineas; }

}
