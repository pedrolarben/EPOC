package amigosdevaro.com.epoc.tipos;

/**
 * Created by Alberto on 16/03/2016.
 */
public interface Paciente extends Usuario {

    List<Farmaco> getFarmacos();
    Double getFev();
    Integer getDisrea();
    Integer getActFisica();
    Integer getHospitalizaciones();
    List<Descompensacion> getDescompensaciones();
    List<SatOxigeno>    getSatOxigeno();

}
