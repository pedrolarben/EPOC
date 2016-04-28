package amigosdevaro.com.epoc.tipos;

import java.util.List;

/**
 * Created by Alberto on 16/03/2016.
 */
public interface Paciente /*extends Usuario*/ {

    List<Farmaco> getFarmacos();
    Double getFev();
    Integer getDisnea();
    void setDisnea(Integer disnea);
    Integer getActFisica();
    Integer getHospitalizaciones();
    List<Descompensacion> getDescompensaciones();
    List<SatOxigeno>    getSatOxigeno();

}
