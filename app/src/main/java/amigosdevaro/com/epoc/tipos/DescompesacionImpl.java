package amigosdevaro.com.epoc.tipos;

/**
 * Created by Alberto on 16/03/2016.
 */
public class DescompesacionImpl {


    private LocalDate fecha;
    private Double fiebre;
    private Integer disrea;
    private Boolean tos;
    private SatOxigeno oxigeno;
    private Double fev;
    private Boolean expectoracion;
    private Boolean ruidosRespiratorios;
    private Boolean hinchazonTobillos;
    private Boolean dolorPecho;
    private Boolean estarIrritable;
    private Boolean desorientacion;
    private Boolean dolorCabeza;
    private Boolean somnolencia;


    public DescompesacionImpl(LocalDate fecha) {
        this.fecha = fecha;
    }

    private void checkdisrea(Integer d){
        if(d<0 || d>4){
            throw new IllegalArgumentException();
        }
    }

    private void checkfev(Double f){
        if(f<0 || f>100){
            throw new IllegalArgumentException();
        }
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getFiebre() {
        return fiebre;
    }

    public void setFiebre(Double fiebre) {
        this.fiebre = fiebre;
    }

    public Integer getDisrea() {
        return disrea;
    }

    public void setDisrea(Integer disrea) {
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
}
