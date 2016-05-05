package amigosdevaro.com.epoc.tipos;

import java.util.GregorianCalendar;

/**
 * Created by Alberto on 16/03/2016.
 */
public interface SatOxigeno {
        GregorianCalendar getFecha();
        Integer   getValor();
        void setFecha(GregorianCalendar gc);
        void setValor(Integer i);
}
