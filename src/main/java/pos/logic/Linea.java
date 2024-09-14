package pos.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.XmlElement;

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
        neto = 0;
        importe = 0;
    }
    public Linea(String numero,Producto producto, int cantidad, float descuento, float neto, float importe) {
        this.numero = numero;
        this.producto = producto;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.neto = neto;
        this.importe = importe;
    }
    public String getNumero() {return numero;}
    public Producto getProducto() {return producto;}
    public int getCantidad() {return cantidad;}
    public float getDescuento() {return descuento;}
    public float getNeto() {return neto;}
    public float getImporte() {return importe;}

    public void setNumero(String numero) {this.numero = numero;}
    public void setProducto(Producto producto) {this.producto = producto;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
    public void setDescuento(float descuento) {this.descuento = descuento;}
    public void setNeto(float neto) {this.neto = neto;}
    public void setImporte(float importe) {this.importe = importe;}

    public String toString(){
        return numero;
    }

}
