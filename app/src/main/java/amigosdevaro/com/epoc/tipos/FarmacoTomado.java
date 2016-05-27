package amigosdevaro.com.epoc.tipos;

/**
 * Created by albertovaro on 24/05/16.
 */
public interface FarmacoTomado {
    Farmaco getFarmaco();
    Integer getHora();
    Integer getMinutos();
    void setFarmaco(Farmaco f);
    void setHora(Integer h);
    void setMinutos(Integer m);
}
