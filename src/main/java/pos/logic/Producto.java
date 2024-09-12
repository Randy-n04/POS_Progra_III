package pos.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Producto {
    @XmlID
    String codigo;
    String descripcion;
    String unidadMedida;
    float precioUnitario;
    int existencias;
    String categoria;
    private int cantidadComprada;


    public Producto() {
        this.codigo = "";
        this.descripcion = "";
        this.unidadMedida = "";
        this.precioUnitario = 0;
        this.existencias = 0;
        this.categoria = "";
    }
   public Producto(String codigo, String descripcion, String unidadMedida,float precioUnitario, int existencias, String categoria) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
        this.precioUnitario = precioUnitario;
        this.existencias = existencias;
        this.categoria = categoria;
   }

   public String getCodigo() {
        return codigo;
   }

   public void setCodigo(String codigo) {
        this.codigo = "PRO-" + codigo;
   }

   public String getDescripcion() {
        return descripcion;
   }

   public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
   }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(codigo, producto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return descripcion;
    }

    public int getCantidadComprada() {return cantidadComprada;}

    public void setCantidadComprada(int cantidadComprada) {this.cantidadComprada = cantidadComprada;}
}
