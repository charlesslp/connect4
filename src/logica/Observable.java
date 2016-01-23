package logica;

import interfazGrafica.Observador;

public interface Observable {

	/**
	 * Anade un observador a la lista de observadores
	 * 
	 * @param o
	 *            observador a anadir
	 */
	void addObservador(Observador o);

	/**
	 * Extrae un observador de la lista de observadores
	 * 
	 * @param o
	 *            observador a quitar
	 */
	void removeObservador(Observador o);

}
