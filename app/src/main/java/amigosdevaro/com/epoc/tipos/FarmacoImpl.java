package amigosdevaro.com.epoc.tipos;

/**
 * Created by Alberto on 18/04/2016.
 */
public class FarmacoImpl {
    private String nombre;
    private TipoFarmaco tipo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoFarmaco getTipo() {
        return tipo;
    }

    public void setTipo(TipoFarmaco tipo) {
        this.tipo = tipo;
    }

    public Posologia getPosologia() {
        return posologia;
    }

    public void setPosologia(Posologia posologia) {
        this.posologia = posologia;
    }

    private Posologia posologia;


}
