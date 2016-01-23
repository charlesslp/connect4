package control;


import logica.Ficha;
import logica.Tablero;

public class JugadorAleatorioGravity extends Jugador {

	@Override
	public void obtenFilaColumna(Tablero tab, Ficha color) {
		boolean fin = false;
		int columna = 0;
		int fila = 0;
		while (!fin) {
			columna = (int) (tab.getColumnas() * Math.random());
			fila = (int) (tab.getFilas() * Math.random());
			if (tab.getFicha(fila, columna) == Ficha.VACIA)
				fin = true;
		}
		this.columna = columna;
		this.fila = fila;
	}
}

