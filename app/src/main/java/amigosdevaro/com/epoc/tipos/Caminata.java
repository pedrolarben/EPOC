package amigosdevaro.com.epoc.tipos;

/**
 * Created by albertovaro on 19/05/16.
 */
public interface Caminata {
    String getFecha();
    String getHora();
    Integer getDuracion();
    Double getDisnea();
    String getEjercicios();
    String getObservaciones();
    void setFecha(String f);
    void setHora(String h);
    void setDuracion(Integer d);
    void setDisnea(Double d);
    void setEjercicios(String e);
    void setObservaciones(String o);

}
