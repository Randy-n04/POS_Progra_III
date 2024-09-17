package pos.presentation.estadisticas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Rango {

    int annoDesde;
    int mesDesde;
    int annoHasta;
    int mesHasta;

   public Rango(int aDesde,int mDesde,int aHasta,int mHasta){
       this.annoDesde=aDesde;
       this.mesDesde=mDesde;
       this.annoHasta=aHasta;
       this.mesHasta=mHasta;
   }

    int getAnnoDesde(){
       return annoDesde;
    }

    int getMesDesde(){
        return mesDesde;
    }

    int getAnnoHasta(){return annoHasta; }

    int getMesHasta(){return mesHasta; }

    public int getTotalMeses() {
        return ((annoHasta - annoDesde) * 12) + (mesHasta - mesDesde + 1);
    }

    public String getFechaInicio() {
        return String.format("%02d/%d", mesDesde, annoDesde);
    }

    public String getFechaFin() {
        return String.format("%02d/%d", mesHasta, annoHasta);
    }

    public boolean contieneFecha(int anno, int mes) {
        if (anno < annoDesde || anno > annoHasta) {
            return false;
        }
        if (anno == annoDesde && mes < mesDesde) {
            return false;
        }
        if (anno == annoHasta && mes > mesHasta) {
            return false;
        }
        return true;
    }

    public String[] getFechas() {
        int totalMeses = getTotalMeses();
        String[] fechas = new String[totalMeses];
        int currentAnno = annoDesde;
        int currentMes = mesDesde;

        for (int i = 0; i < totalMeses; i++) {
            fechas[i] = String.format("%02d/%d", currentMes, currentAnno);
            currentMes++;
            if (currentMes > 12) {
                currentMes = 1;
                currentAnno++;
            }
        }

        return fechas;
    }
































































}
