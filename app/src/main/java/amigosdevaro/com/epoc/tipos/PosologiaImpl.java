package amigosdevaro.com.epoc.tipos;

import java.util.GregorianCalendar;
import java.util.Set;

public class PosologiaImpl {
	
	
	private Set<DiasSemana> diassemanas;
	private int cadaCuantosDias;
	private GregorianCalendar primeraDosisHora;
	private AdministracionFarmaco administracion;
	
	public PosologiaImpl(Set<DiasSemana> setdiasemana, int cadaCuantosdias, GregorianCalendar primeradosishora, AdministracionFarmaco administra){
		diassemanas = setdiasemana;
		cadaCuantosDias = cadaCuantosdias;
		primeraDosisHora = primeradosishora;
		administracion = administra;
	}
	
	public Set<DiasSemana> getDiassemanas() {
		return diassemanas;
	}
	public void setDiassemanas(Set<DiasSemana> diassemanas) {
		this.diassemanas = diassemanas;
	}
	public int getCadaCuantosDias() {
		return cadaCuantosDias;
	}
	public void setCadaCuantosDias(int cadaCuantosDias) {
		this.cadaCuantosDias = cadaCuantosDias;
	}
	public GregorianCalendar getPrimeraDosisHora() {
		return primeraDosisHora;
	}
	public void setPrimeraDosisHora(GregorianCalendar primeraDosisHora) {
		this.primeraDosisHora = primeraDosisHora;
	}
	public AdministracionFarmaco getAdministracion() {
		return administracion;
	}
	public void setAdministracion(AdministracionFarmaco administracion) {
		this.administracion = administracion;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((administracion == null) ? 0 : administracion.hashCode());
		result = prime * result + cadaCuantosDias;
		result = prime * result + ((diassemanas == null) ? 0 : diassemanas.hashCode());
		result = prime * result + ((primeraDosisHora == null) ? 0 : primeraDosisHora.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PosologiaImpl other = (PosologiaImpl) obj;
		if (administracion != other.administracion)
			return false;
		if (cadaCuantosDias != other.cadaCuantosDias)
			return false;
		if (diassemanas == null) {
			if (other.diassemanas != null)
				return false;
		} else if (!diassemanas.equals(other.diassemanas))
			return false;
		if (primeraDosisHora == null) {
			if (other.primeraDosisHora != null)
				return false;
		} else if (!primeraDosisHora.equals(other.primeraDosisHora))
			return false;
		return true;
	}
}
