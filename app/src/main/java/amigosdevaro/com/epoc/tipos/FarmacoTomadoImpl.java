package amigosdevaro.com.epoc.tipos;

/**
 * Created by albertovaro on 24/05/16.
 */
public class FarmacoTomadoImpl implements FarmacoTomado {
    private Farmaco farmaco;
    private Integer hora;
    private Integer minutos;


    public FarmacoTomadoImpl(Farmaco f, Integer h, Integer m){
        this.farmaco = f;
        this.hora = h;
        this.minutos = m;


    }

    @Override
    public Farmaco getFarmaco() {
        return farmaco;
    }

    @Override
    public void setFarmaco(Farmaco farmaco) {
        this.farmaco = farmaco;
    }

    @Override
    public Integer getHora() {
        return hora;
    }

    @Override
    public void setHora(Integer hora) {
        this.hora = hora;
    }

    @Override
    public Integer getMinutos() {
        return minutos;
    }

    @Override
    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }


}
