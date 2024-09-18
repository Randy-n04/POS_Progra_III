package pos.logic;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import pos.data.LocalDateAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Factura {
    @XmlID
    String numero;

    @XmlIDREF
    Cajero cajero;

    @XmlIDREF
    Cliente cliente;

    @XmlElementWrapper(name = "lineas")
    @XmlElement(name = "linea")
    List<Linea> lineas;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    LocalDate fecha;

    public Factura() {
        this.numero = "";
        this.cajero = null;
        this.cliente = null;
        this.lineas = new ArrayList<>();;
        this.fecha = LocalDate.now();
    }

    public Factura(String numero, LocalDate fecha, Cajero cajero, Cliente cliente) {
        this.numero = numero;
        this.fecha = fecha;
        this.cajero = cajero;
        this.cliente = cliente;
        this.lineas = new ArrayList<>();;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = "FAC-" + numero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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

    public void setLineas(List<Linea> lineas) {
        this.lineas = lineas;
    }

    public List<Linea> getLineas() {
        return lineas;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return numero.equals(factura.numero);
    }

    @Override
    public int hashCode(){ return Objects.hash(numero); }

    @Override
    public String toString() {
        return numero;
    }

}