package amigosdevaro.com.epoc.tipos;


import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Set;

public interface Posologia{
	Set<DiasSemana> getDiassemanas();
	int getCadaCuantosDias();
	GregorianCalendar getPrimeraDosisHora();
	AdministracionFarmaco getAdministracion();
	void setDiassemanas(Set<DiasSemana> p);
	void setCadaCuantosDias(int i);
	void setPrimeraDosisHora(GregorianCalendar gc);
	void setAdministracion(AdministracionFarmaco af);


}
