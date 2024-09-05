package pos.data;

import pos.logic.*;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    //-------------CLIENTES-------------
    @XmlElementWrapper(name = "clientes")
    @XmlElement(name = "cliente")
    private List<Cliente> clientes;

    //-------------CAJEROS-------------
    @XmlElementWrapper(name = "cajeros")
    @XmlElement(name = "cajero")
    private List<Cajero> cajeros;
    //-------------PRODUCTOS-------------
    @XmlElementWrapper(name = "productos")
    @XmlElement(name = "producto")
    private List<Producto> productos;

    //-------------FACTURAS-------------
    @XmlElementWrapper(name = "facturas")
    @XmlElement(name = "factura")
    private List<Factura> facturas;

    public Data() {
        clientes = new ArrayList<>();
        cajeros = new ArrayList<>();
        productos = new ArrayList<>();
        facturas = new ArrayList<>();
    }
    
    public List<Cliente> getClientes() {
        return clientes;
    }  
    public List<Cajero> getCajeros() {
      return cajeros;
    }
    public List<Producto> getProductos() {
        return productos;
    }
    public List<Factura> getFacturas() { return facturas; }

   

}
