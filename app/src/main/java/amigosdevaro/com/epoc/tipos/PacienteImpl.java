package amigosdevaro.com.epoc.tipos;

import android.net.Uri;

import java.util.List;

/**
 * Created by Alberto on 16/03/2016.
 */
public class PacienteImpl /*extends Usuario*/ {
        private List<Farmaco> farmacos;
        private Double fev;
        private Integer disrea;
        private Integer actFisica;
        private Integer hospitalizaciones;
        private List<Descompensacion> descompensaciones;
        private List<SatOxigeno>    satoxigeno;

    public PacienteImpl(){

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

    public void setDescompensaciones(List<Descompensacion> descompensaciones) {
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
    private void checkhospitalizaciones(Integer h){
        if(h<0){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PacienteImpl)) return false;

        PacienteImpl paciente = (PacienteImpl) o;

        if (farmacos != null ? !farmacos.equals(paciente.farmacos) : paciente.farmacos != null)
            return false;
        if (fev != null ? !fev.equals(paciente.fev) : paciente.fev != null) return false;
        if (disrea != null ? !disrea.equals(paciente.disrea) : paciente.disrea != null)
            return false;
        if (actFisica != null ? !actFisica.equals(paciente.actFisica) : paciente.actFisica != null)
            return false;
        if (hospitalizaciones != null ? !hospitalizaciones.equals(paciente.hospitalizaciones) : paciente.hospitalizaciones != null)
            return false;
        if (descompensaciones != null ? !descompensaciones.equals(paciente.descompensaciones) : paciente.descompensaciones != null)
            return false;
        return !(satoxigeno != null ? !satoxigeno.equals(paciente.satoxigeno) : paciente.satoxigeno != null);

    }

    @Override
    public int hashCode() {
        int result = farmacos != null ? farmacos.hashCode() : 0;
        result = 31 * result + (fev != null ? fev.hashCode() : 0);
        result = 31 * result + (disrea != null ? disrea.hashCode() : 0);
        result = 31 * result + (actFisica != null ? actFisica.hashCode() : 0);
        result = 31 * result + (hospitalizaciones != null ? hospitalizaciones.hashCode() : 0);
        result = 31 * result + (descompensaciones != null ? descompensaciones.hashCode() : 0);
        result = 31 * result + (satoxigeno != null ? satoxigeno.hashCode() : 0);
        return result;
    }
}
