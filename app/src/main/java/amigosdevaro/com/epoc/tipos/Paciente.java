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
    void setFarmacos(List<Farmaco> l);
    void setFev(Double f);
    void setDescompensaciones(List<Descompensacion> li);
    void setSatOxigeno(List<SatOxigeno> st);
    void setActFisica(Integer in);
    void setHospitalizaciones(Integer i);

}
