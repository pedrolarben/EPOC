package amigosdevaro.com.epoc.tipos;

import java.util.GregorianCalendar;

/**
 * Created by Alberto on 16/03/2016.
 */
public interface Descompensacion {

    GregorianCalendar getFecha();
    Double getFiebre();
    Integer getDisrea();
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

}
