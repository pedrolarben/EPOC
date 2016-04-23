package amigosdevaro.com.epoc.tipos;

import java.util.GregorianCalendar;

/**
 * Created by Alberto on 16/03/2016.
 */
public class SatOxigenoImpl {
    private GregorianCalendar fecha;
    private Integer valor;

    public SatOxigenoImpl(Integer valor, GregorianCalendar fecha) {
        this.valor = valor;
        this.fecha = fecha;
    }

    public GregorianCalendar getFecha() {
        return fecha;
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SatOxigenoImpl)) return false;

        SatOxigenoImpl that = (SatOxigenoImpl) o;

        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;
        return !(valor != null ? !valor.equals(that.valor) : that.valor != null);

    }

    @Override
    public int hashCode() {
        int result = fecha != null ? fecha.hashCode() : 0;
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        return result;
    }
}
