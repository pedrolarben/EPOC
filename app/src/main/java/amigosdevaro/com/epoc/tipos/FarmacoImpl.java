package amigosdevaro.com.epoc.tipos;

import java.io.Serializable;

/**
 * Created by Alberto on 18/04/2016.
 */
public class FarmacoImpl implements Farmaco, Serializable {

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


    public FarmacoImpl(String n, TipoFarmaco tp, Posologia po){
        this.nombre=n;
        this.tipo=tp;
        this.posologia=po;

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FarmacoImpl)) return false;

        FarmacoImpl farmaco = (FarmacoImpl) o;

        if (nombre != null ? !nombre.equals(farmaco.nombre) : farmaco.nombre != null) return false;
        if (tipo != farmaco.tipo) return false;
        return !(posologia != null ? !posologia.equals(farmaco.posologia) : farmaco.posologia != null);

    }

    @Override
    public int hashCode() {
        int result = nombre != null ? nombre.hashCode() : 0;
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (posologia != null ? posologia.hashCode() : 0);
        return result;
    }
}
