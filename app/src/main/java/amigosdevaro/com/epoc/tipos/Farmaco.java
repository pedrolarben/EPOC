package amigosdevaro.com.epoc.tipos;

/**
 * Created by Alberto on 16/03/2016.
 */
public interface Farmaco {
    String getNombre();
    TipoFarmaco getTipo();
    Posologia getPosologia();
    void setNombre(String s);
    void setTipo(TipoFarmaco tf);
    void setPosologia(Posologia p);
}
