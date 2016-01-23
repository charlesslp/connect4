package control;


import logica.Ficha;
import logica.Tablero;

public class JugadorAleatorioComplica extends Jugador {

	@Override
	public void obtenFilaColumna(Tablero tab, Ficha color) {
		this.columna = (int) (tab.getColumnas() * Math.random());
	}
}

