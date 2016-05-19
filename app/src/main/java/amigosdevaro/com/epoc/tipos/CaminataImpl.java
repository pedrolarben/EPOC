package amigosdevaro.com.epoc.tipos;

/**
 * Created by albertovaro on 19/05/16.
 */
public class CaminataImpl implements  Caminata {
    private String fecha;
    private String hora;
    private Integer duracion;
    private Double disnea;
    private String ejercicios;
    private String observaciones;

    public CaminataImpl() {
        this.fecha = "";
        this.hora = "";
        this.duracion = 0;
        this.disnea = 0.0;
        this.ejercicios = "";
        this.observaciones = "";
    }


    @Override
    public String getFecha() {
        return fecha;
    }

    @Override
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String getHora() {
        return hora;
    }

    @Override
    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public Integer getDuracion() {
        return duracion;
    }

    @Override
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    @Override
    public Double getDisnea() {
        return disnea;
    }

    @Override
    public void setDisnea(Double disnea) {
        this.disnea = disnea;
    }

    @Override
    public String getEjercicios() {
        return ejercicios;
    }

    @Override
    public void setEjercicios(String ejercicios) {
        this.ejercicios = ejercicios;
    }

    @Override
    public String getObservaciones() {
        return observaciones;
    }

    @Override
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
