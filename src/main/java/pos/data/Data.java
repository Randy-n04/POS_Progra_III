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

    public Data() {
        clientes = new ArrayList<>();
        cajeros = new ArrayList<>();
      
    //-------------PRODUCTOS-------------
    @XmlElementWrapper(name = "productos")
    @XmlElement(name = "producto")
    private List<Producto> productos;
    public Data() {
        clientes = new ArrayList<>();
        productos = new ArrayList<>();
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

   

}
