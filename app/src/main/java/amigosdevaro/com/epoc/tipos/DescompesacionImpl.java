package amigosdevaro.com.epoc.tipos;

import java.util.GregorianCalendar;

/**
 * Created by Alberto on 16/03/2016.
 */
public class DescompesacionImpl implements Descompensacion {


    private GregorianCalendar fecha;
    private Double fiebre;
    private Integer disrea;
    private Boolean tos;
    private SatOxigeno oxigeno;
    private Double fev;
    private Boolean expectoracion;
    private Boolean ruidosRespiratorios;
    private Boolean hinchazonTobillos;
    private Boolean dolorTobillos;
    private Boolean dolorPecho;
    private Boolean estarIrritable;
    private Boolean desorientacion;
    private Boolean dolorCabeza;
    private Boolean somnolencia;


    public DescompesacionImpl(GregorianCalendar fecha) {
        this.fecha = fecha;
    }


    @Override
    public Boolean getDolorTobillos() {
        return dolorTobillos;
    }

    public void setDolorTobillos(Boolean dolorTobillos) {
        this.dolorTobillos = dolorTobillos;
    }

    public DescompesacionImpl(int year, int month, int day, int hour, int minute){
        fecha = new GregorianCalendar(year,month,day,hour,minute);
    }




    private void checkdisrea(Integer d){
        if(d<0 || d>4){
            throw new IllegalArgumentException("disnea can not be <0 , >4");
        }
    }

    private void checkfev(Double f){
        if(f<0 || f>100){
            throw new IllegalArgumentException();
        }
    }

    public GregorianCalendar getFecha() {
        return fecha;
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    public Double getFiebre() {
        return fiebre;
    }

    public void setFiebre(Double fiebre) {
        this.fiebre = fiebre;
    }

    public Integer getDisnea() {
        return disrea;
    }

    public void setDisnea(Integer disrea) {
        checkdisrea(disrea);
        this.disrea = disrea;
    }

    public Boolean getTos() {
        return tos;
    }

    public void setTos(Boolean tos) {
        this.tos = tos;
    }

    public SatOxigeno getOxigeno() {
        return oxigeno;
    }

    public void setOxigeno(SatOxigeno oxigeno) {
        this.oxigeno = oxigeno;
    }

    public Double getFev() {
        return fev;
    }

    public void setFev(Double fev) {
        checkfev(fev);
        this.fev = fev;
    }

    public Boolean getRuidosRespiratorios() {
        return ruidosRespiratorios;
    }

    public void setRuidosRespiratorios(Boolean ruidosRespiratorios) {
        this.ruidosRespiratorios = ruidosRespiratorios;
    }

    public Boolean getExpectoracion() {
        return expectoracion;
    }

    public void setExpectoracion(Boolean expectoracion) {
        this.expectoracion = expectoracion;
    }

    public Boolean getHinchazonTobillos() {
        return hinchazonTobillos;
    }

    public void setHinchazonTobillos(Boolean hinchazonTobillos) {
        this.hinchazonTobillos = hinchazonTobillos;
    }

    public Boolean getDolorPecho() {
        return dolorPecho;
    }

    public void setDolorPecho(Boolean dolorPecho) {
        this.dolorPecho = dolorPecho;
    }

    public Boolean getEstarIrritable() {
        return estarIrritable;
    }

    public void setEstarIrritable(Boolean estarIrritable) {
        this.estarIrritable = estarIrritable;
    }

    public Boolean getDesorientacion() {
        return desorientacion;
    }

    public void setDesorientacion(Boolean desorientacion) {
        this.desorientacion = desorientacion;
    }

    public Boolean getSomnolencia() {
        return somnolencia;
    }

    public void setSomnolencia(Boolean somnolencia) {
        this.somnolencia = somnolencia;
    }

    public Boolean getDolorCabeza() {
        return dolorCabeza;
    }

    public void setDolorCabeza(Boolean dolorCabeza) {
        this.dolorCabeza = dolorCabeza;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DescompesacionImpl)) return false;

        DescompesacionImpl that = (DescompesacionImpl) o;

        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;
        if (fiebre != null ? !fiebre.equals(that.fiebre) : that.fiebre != null) return false;
        if (disrea != null ? !disrea.equals(that.disrea) : that.disrea != null) return false;
        if (tos != null ? !tos.equals(that.tos) : that.tos != null) return false;
        if (oxigeno != null ? !oxigeno.equals(that.oxigeno) : that.oxigeno != null) return false;
        if (fev != null ? !fev.equals(that.fev) : that.fev != null) return false;
        if (expectoracion != null ? !expectoracion.equals(that.expectoracion) : that.expectoracion != null)
            return false;
        if (ruidosRespiratorios != null ? !ruidosRespiratorios.equals(that.ruidosRespiratorios) : that.ruidosRespiratorios != null)
            return false;
        if (hinchazonTobillos != null ? !hinchazonTobillos.equals(that.hinchazonTobillos) : that.hinchazonTobillos != null)
            return false;
        if (dolorPecho != null ? !dolorPecho.equals(that.dolorPecho) : that.dolorPecho != null)
            return false;
        if (estarIrritable != null ? !estarIrritable.equals(that.estarIrritable) : that.estarIrritable != null)
            return false;
        if (desorientacion != null ? !desorientacion.equals(that.desorientacion) : that.desorientacion != null)
            return false;
        if (dolorCabeza != null ? !dolorCabeza.equals(that.dolorCabeza) : that.dolorCabeza != null)
            return false;
        return !(somnolencia != null ? !somnolencia.equals(that.somnolencia) : that.somnolencia != null);

    }

    @Override
    public int hashCode() {
        int result = fecha != null ? fecha.hashCode() : 0;
        result = 31 * result + (fiebre != null ? fiebre.hashCode() : 0);
        result = 31 * result + (disrea != null ? disrea.hashCode() : 0);
        result = 31 * result + (tos != null ? tos.hashCode() : 0);
        result = 31 * result + (oxigeno != null ? oxigeno.hashCode() : 0);
        result = 31 * result + (fev != null ? fev.hashCode() : 0);
        result = 31 * result + (expectoracion != null ? expectoracion.hashCode() : 0);
        result = 31 * result + (ruidosRespiratorios != null ? ruidosRespiratorios.hashCode() : 0);
        result = 31 * result + (hinchazonTobillos != null ? hinchazonTobillos.hashCode() : 0);
        result = 31 * result + (dolorPecho != null ? dolorPecho.hashCode() : 0);
        result = 31 * result + (estarIrritable != null ? estarIrritable.hashCode() : 0);
        result = 31 * result + (desorientacion != null ? desorientacion.hashCode() : 0);
        result = 31 * result + (dolorCabeza != null ? dolorCabeza.hashCode() : 0);
        result = 31 * result + (somnolencia != null ? somnolencia.hashCode() : 0);
        return result;
    }
    
}
