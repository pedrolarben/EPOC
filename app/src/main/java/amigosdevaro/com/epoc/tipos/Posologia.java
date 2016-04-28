package amigosdevaro.com.epoc.tipos;


import java.util.GregorianCalendar;
import java.util.Set;
import amigosdevaro.com.epoc.tipos.AdministracionFarmaco;

public interface Posologia {
	Set<DiasSemana> getDiassemanas();
	int getCadaCuantosDias();
	GregorianCalendar getPrimeraDosisHora();
	AdministracionFarmaco getAdministracion();

}
