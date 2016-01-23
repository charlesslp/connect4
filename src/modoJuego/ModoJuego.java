package modoJuego;

public interface ModoJuego {

	/**
	 * se debe ejecutar cuando le toca jugar al turno correspondiente.
	 */
	void comenzar();

	
	
	/**
	 * se debe ejecutar cuando termina de jugar el turno correspondiente.
	 */
	void terminar();
	
	
	

	/**
	 * se debe ejecutar cuando el usuario pulsa deshacer.
	 */
	void deshacerPulsado();

	
	
	
	/**
	 * Recibe la fila y la columna del movimiento
	 * 
	 * @param f
	 *            fila
	 * @param c
	 *            columna
	 */
	void tableroPulsado(int f, int c);

}
