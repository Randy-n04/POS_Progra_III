package pos.presentation.estadisticas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Rango {

    List<Date> rango;

    Rango(){
        rango = new ArrayList<Date>();
    }


    void addFecha(Date fecha){
        rango.add(fecha);
    }

    Date getAnnoDesde(){
        if(!rango.isEmpty())
            return Collections.min(rango);
        else
            return null;
    }

    Date getMesDesde(){
        if(!rango.isEmpty())
            return Collections.min(rango);
         else
            return null;
    }

    Date getAnnoHasta(){return new Date(); }

    Date getMesHasta(){return new Date(); }

}
