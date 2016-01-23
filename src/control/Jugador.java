package control;

import java.util.InputMismatchException;

import exceptions.DatosIncorrectos;
import logica.Ficha;
import logica.Movimiento;
import logica.Tablero;

abstract public class Jugador {
	public int fila;
	public int columna;

	public abstract void obtenFilaColumna(Tablero tab, Ficha color);

	public Movimiento getMovimiento(FactoriaTipoJuego factoria, Tablero tab,
			Ficha color) throws DatosIncorrectos {
		try {
			this.obtenFilaColumna(tab, color);
			return factoria.creaMovimiento(this.fila, this.columna, color);
		}

		catch (InputMismatchException e) {

			throw new DatosIncorrectos("Los datos introducidos no son numericos");
		}
	}
}