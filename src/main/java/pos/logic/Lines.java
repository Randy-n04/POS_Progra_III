package pos.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "lines")
@XmlAccessorType(XmlAccessType.FIELD)
public class Lines {

    @XmlElement(name = "factura")
    private List<Factura> facturas;

    public Lines() {
        this.facturas = new ArrayList<>();
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public void addFactura(Factura factura) {
        this.facturas.add(factura);
    }

    public void removeFactura(Factura factura) {
        this.facturas.remove(factura);
    }

    public List<Factura> buscarFacturas(String criterio) {
        List<Factura> resultados = new ArrayList<>();
        for (Factura factura : facturas) {
            if (factura.getNumero().toLowerCase().contains(criterio.toLowerCase())) {
                resultados.add(factura);
            }
        }
        return resultados;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Factura factura : facturas) {
            sb.append(factura.toString()).append("\n");
        }
        return sb.toString();
    }
}

