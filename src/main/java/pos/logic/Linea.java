package pos.logic;


import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Linea {
    @XmlID
    String numero;
    @XmlIDREF
    Producto producto;
    @XmlElement
    int cantidad;
    float descuento;
    float neto;
    float importe;

    public Linea() {
        numero = "";
        producto = new Producto();
        cantidad = 0;
        descuento = 0;
        this.neto = calcularNeto();
        this.importe = calcularImporte();
    }

    public Linea(String numero, Producto producto, int cantidad, float descuento) {
        this.numero = numero;
        this.producto = producto;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.neto = calcularNeto();
        this.importe = calcularImporte();
    }

    public String getNumero() { return numero; }
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public float getDescuento() { return descuento; }
    public float getNeto() { return neto; }
    public float getImporte() { return importe; }

    public void setNumero(String numero) { this.numero = "LIN-" + numero; }
    public void setProducto(Producto producto) {
        this.producto = producto;
        this.neto = calcularNeto();
        this.importe = calcularImporte();
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.neto = calcularNeto();
        this.importe = calcularImporte();
    }
    public void setDescuento(float descuento) {
        this.descuento = descuento;
        this.importe = calcularImporte();
    }
    public void setNeto(float neto) { this.neto = neto; }
    public void setImporte(float importe) { this.importe = importe; }

    public float calcularNeto() {
        return (producto != null ? producto.getPrecioUnitario() : 0) * this.cantidad;
    }

    public float calcularImporte() {
        return neto - (neto * (descuento / 100));
    }

    @Override
    public String toString() {
        return numero;
    }
}
