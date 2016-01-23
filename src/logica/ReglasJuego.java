package logica;

abstract public class ReglasJuego {

	public abstract Tablero iniciaTablero();

	/**
	 * Coloca la ficha a BLANCA para empezar el juego
	 * 
	 * @return ficha para comenzar juego
	 */
	public abstract Ficha jugadorInicial();

	/**
	 * Comprueba si la partida ha terminado en tablas
	 * 
	 * @param t
	 *            tablero
	 * @return true si hay tablas
	 */
	public abstract boolean tablas(Tablero t);

	/**
	 * Comprueba si hay ganador
	 * 
	 * @param f
	 *            fila de la ficha
	 * @param c
	 *            columna de la ficha
	 * @param ultimo
	 *            turno del ultimo jugador
	 * @param tablero
	 * @return si hay un grupo de 4 en raya del turno ultimo
	 */
	public abstract Ficha hayGanador(int f, int c, Ficha ultimo, Tablero tablero);

	/**
	 * Cambia el turno
	 * 
	 * @param ultimo
	 *            turno
	 * @return siguiente turno
	 */
	public abstract Ficha siguienteTurno(Ficha ultimo,TableroSoloLectura t);
	
	public abstract Ficha ganadorImposibleMover(TableroSoloLectura t);

}
