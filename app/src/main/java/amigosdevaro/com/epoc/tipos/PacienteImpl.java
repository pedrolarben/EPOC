package amigosdevaro.com.epoc.tipos;

/**
 * Created by Alberto on 16/03/2016.
 */
public class PacienteImpl extends Usuario {
        private List<Farmaco> farmacos;
        private Double fev;
        private Integer disrea;
        private Integer actFisica;
        private Integer hospitalizaciones;
        private List<Descompensacion> descompensaciones;
        private List<SatOxigeno>    satoxigeno;

    public PacienteImpl(String email, String name, String id, Uri photo){
        super(email, name, id, photo);
    }

    public void setFarmacos(List<Farmaco> farmacos) {
        this.farmacos = farmacos;
    }

    public void setFev(Double fev) {
        checkfev(fev);
        this.fev = fev;
    }

    public void setDisrea(Integer disrea) {
        checkdisrea(disrea);
        this.disrea = disrea;
    }

    public void setActFisica(Integer actFisica) {
        checkactfisica(actFisica);
        this.actFisica = actFisica;
    }

    public void setHospitalizaciones(Integer hospitalizaciones) {
        checkhospitalizaciones(hospitalizaciones);
        this.hospitalizaciones = hospitalizaciones;
    }

    public void setDescompensaciones(List<Descomposicion> descompensaciones) {
        this.descompensaciones = descompensaciones;
    }

    public void setSatoxigeno(List<SatOxigeno> satoxigeno) {
        this.satoxigeno = satoxigeno;
    }

    public Integer getActFisica() {

        return actFisica;
    }

    public List<Farmaco> getFarmacos() {
        return farmacos;
    }

    public Double getFev() {
        return fev;
    }

    public Integer getDisrea() {
        return disrea;
    }

    public Integer getHospitalizaciones() {
        return hospitalizaciones;
    }

    public List<Descompensacion> getDescompensaciones() {
        return descompensaciones;
    }

    public List<SatOxigeno> getSatoxigeno() {
        return satoxigeno;
    }
    private void checkfev(Double f){
        if(f<0 || f>100){
            throw new IllegalArgumentException();
        }
    }
    private void checkdisrea(Integer d){
        if(d<0 || d>4){
            throw new IllegalArgumentException();
        }
    }
    private void checkactfisica(Integer af){
        if(af<0 || af>1400){
            throw new IllegalArgumentException();
        }
    }
    private void checkhospitalizaciones(integer h){
        if(h<0){
            throw new IllegalArgumentException();
        }
    }

}
