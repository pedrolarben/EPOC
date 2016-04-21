package tipos;

import java.time.LocalTime;
import java.util.Set;

public interface Posologia {
	Set<DiasSemana> getDias();
	int getCadaCuantosDias();
	LocalTime getPrimeraDosisHora();
	AdministracionFarmaco getAdministracion();

}
