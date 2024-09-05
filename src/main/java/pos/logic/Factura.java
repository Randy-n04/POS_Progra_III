package pos.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlID;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Factura {
    @XmlID
    String numero;
    Date fecha;

    @XmlIDREF
    Cajero cajero;

    @XmlIDREF
    Cliente cliente;

    List<Producto> productos;
    float total;

    public Factura() {
        this.numero = "";
        this.fecha = new Date();
        this.cajero = null;
        this.cliente = null;
        this.productos = new ArrayList<>();
        this.total = 0;
    }

    public Factura(String numero, Date fecha, Cajero cajero, Cliente cliente) {
        this.numero = numero;
        this.fecha = fecha;
        this.cajero = cajero;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.total = 0;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cajero getCajero() {
        return cajero;
    }

    public void setCajero(Cajero cajero) {
        this.cajero = cajero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void calcularTotal() {
        total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecioUnitario() * producto.getExistencias();
        }
        if (cliente != null) {
            total -= total * cliente.getDescuento();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return Objects.equals(numero, factura.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return "Factura{" +
                "numero='" + numero + '\'' +
                ", fecha=" + fecha +
                ", cajero=" + cajero +
                ", cliente=" + cliente +
                ", total=" + total +
                '}';
    }
}
