package logica;

import exceptions.MovimientoInvalido;
import logica.Ficha;
import logica.Tablero;

abstract public class Movimiento {

	protected int columna;
	protected Ficha turno;
	protected int fila;


	/**
	 * Constructora de movimiento
	 * 
	 * @param columna
	 * @param turno
	 */
	public Movimiento(int columna, Ficha turno) {
		this.columna = columna;
		this.turno = turno;
		
	}

	/**
	 * Metodo de consulta de jugador
	 * 
	 * @return
	 */
	public Ficha getJugador() {
		return this.turno;
	}

	/**
	 * Metodo de consulta de la columna
	 * 
	 * @return
	 */
	public int getColumna() {
		return this.columna;
	}

	/**
	 * Metodo de consulta de la fila
	 * 
	 * @return
	 */
	public int getFila() {
		return this.fila;
	}

	/**
	 * Situa la ficha en el tablero
	 * @param t	tablero
	 * @throws MovimientoInvalido
	 */
	public abstract void ejecutaMovimiento(Tablero t) throws MovimientoInvalido;

	/**
	 * Deshace el ultimo movimiento registrado
	 * @param t	tablero
	 */
	public abstract void undo(Tablero t);
	
	/**
	 * Comprueba que la columna esta dentro del tablero
	 * @param t	tablero
	 * @return true si la columna esta dentro
	 */
	public boolean columnaCorrecta(Tablero t){
		return this.columna >= 0 && this.columna < t.getColumnas();
	}
	
	/**
	 * Comprueba si la columna esta llena
	 * @param t	tablero
	 * @return true si la columna esta llena
	 */
	public boolean columnaLlena(Tablero t){
		return t.getFicha(0, this.columna) != Ficha.VACIA;
	}

}
