package interfazGrafica;

import exceptions.MovimientoInvalido;
import logica.Ficha;
import logica.TableroSoloLectura;

public interface Observador {

	// GESTION DE LA PARTIDA
	//_____________________________________________________


	public void onMovimientoStart(Ficha turno);
	
	/**
	 * 
	 * Mtodo invocado por la clase Partida que permite notificar a sus
	 * observadores(las vistas) que se ha reiniciado la partida. Proporciona
	 * informaciOn del estado inicial del tablero y el turno (que serA una ficha
	 * blanca o negra).
	 *
	 * @param tab tablero solo lectura
	 * @param turno turno del jugador actual
	 */
	void onReset(TableroSoloLectura tab, Ficha turno);

	/**
	 * 
	 * La partida notifica a los observadores que ha terminado la partida
	 * llamando a este mEtodo. AdemAs proporciona al observador una vista del
	 * tablero de sOlo lectura y el ganador.
	 * 
	 * @param tab
	 *            tablero solo lectura
	 * @param turno
	 *            turno del jugador actual
	 */
	void onPartidaTerminada(TableroSoloLectura tablero, Ficha ganador);

	/**
	 * La partida notifica a los observadores que se ha cambiado el juego. Se
	 * proporciona el estado inicial del tablero y el turno
	 * 
	 * @param tab
	 *            tablero solo lectura
	 * @param turno
	 *            turno del jugador actual
	 */
	void onCambioJuego(TableroSoloLectura tab, Ficha turno);
	
	
	
	
	// GESTION DE MOVIMIENTOS
	//_____________________________________________________

	/**
	 * La partida notifica a los observadores que una operaciOn deshacer no ha
	 * tenido Exito porque no se puede deshacer
	 */
	void onUndoNotPossible();

	/**
	 * La partida notifica a los observadores que se ha deshecho un movimiento.
	 * AdemAs, proporciona el estado final del tablero, el turno del siguiente
	 * jugador y si hay mAs movimientos a deshacer o no.
	 * 
	 * @param tab
	 *            tablero solo lectura
	 * @param turno
	 *            turno del jugador actual
	 * @param hayMas
	 *            Si hay aun movimientos restantes en la pila
	 */
	void onUndo(TableroSoloLectura tablero, Ficha turno, boolean hayMas);

	/**
	 * * La partida notifica a los observadores que se ha terminado de realizar
	 * un movimiento. Se proporciona ademAs una vista del tablero de sOlo
	 * lectura, el jugador que ha jugado, y el turno del siguiente jugador.
	 * 
	 * @param tablero
	 *            tablero solo lectura
	 * @param jugador
	 *            color del jugador que acaba de jugar
	 * @param turno
	 *            color del siguiente jugador
	 */
	void onMovimientoEnd(TableroSoloLectura tablero, Ficha jugador, Ficha turno);

	/**
	 * La partida notifica que se ha producido un movimiento incorrecto
	 * proporcionando el objeto MovimientoInvalido con una explicaciOn del
	 * problema que se ha producido.
	 *
	 * @param movimientoException
	 *            Exception con el mensaje de error del movimiento
	 */
	void onMovimientoIncorrecto(MovimientoInvalido movimientoException);

}
