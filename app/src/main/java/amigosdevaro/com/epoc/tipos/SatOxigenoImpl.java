package amigosdevaro.com.epoc.tipos;

/**
 * Created by Alberto on 16/03/2016.
 */
public class SatOxigenoImpl {
    private LocalDate fecha;
    private Integer valor;

    public SatOxigenoImpl(Integer valor, LocalDate fecha) {
        this.valor = valor;
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}
