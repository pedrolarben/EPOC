package amigosdevaro.com.epoc.tipos;

import java.util.GregorianCalendar;

/**
 * Created by Alberto on 16/03/2016.
 */
public interface Descompensacion {

    GregorianCalendar getFecha();
    Double getFiebre();
    Integer getDisnea();
    Boolean getTos();
    SatOxigeno getOxigeno();
    Double getFev();
    Boolean getExpectoracion();
    Boolean getRuidosRespiratorios();
    Boolean getHinchazonTobillos();
    Boolean getDolorTobillos();
    Boolean getEstarIrritable();
    Boolean getDesorientacion();
    Boolean getDolorCabeza();
    Boolean getSomnolencia();
    void setFecha(GregorianCalendar p);
    void setFiebre(Double f);
    void setDisnea(Integer d);
    void setTos(Boolean t);
    void setOxigeno(SatOxigeno so);
    void setFev(Double f);
    void setExpectoracion(Boolean e);
    void setRuidosRespiratorios(Boolean r);
    void setHinchazonTobillos(Boolean h);
    void setDolorTobillos(Boolean d);
    void setEstarIrritable(Boolean ei);
    void setDesorientacion(Boolean de);
    void setDolorCabeza(Boolean dc);
    void setSomnolencia(Boolean sm);

}
