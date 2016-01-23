package control;


import logica.Ficha;
import logica.Tablero;

public class JugadorAleatorioConecta4 extends Jugador {

	@Override
	public void obtenFilaColumna(Tablero tab, Ficha color) {
		boolean fin = false;
		int columna = 0;
		while (!fin) {
			columna = (int) (tab.getColumnas() * Math.random());
			if (tab.getFicha(0, columna) == Ficha.VACIA)
				fin = true;
		}
		this.columna = columna;
	}
}
