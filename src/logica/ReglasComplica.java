package logica;

import logica.Ficha;
import logica.ReglasJuego;
import logica.Tablero;

public class ReglasComplica extends ReglasJuego{

	private final static int FILAS = 7;
	private final static int COLUMNAS = 4;

	/**
	 * Constructora de reglas complica
	 */
	public ReglasComplica() {
	}

	@Override
	public Tablero iniciaTablero() {
		return new Tablero(FILAS, COLUMNAS);
	}

	@Override
	public boolean tablas(Tablero t) {
		return false;
	}

	@Override
	public Ficha hayGanador(int f, int c, Ficha ultimo, Tablero tablero) {

		boolean ganaBlancas = false, ganaNegras = false, ganaAmbas = false;
		Ficha fichaAComprobar;

		if (f == -1) {// la columna estaba llena
			f = tablero.getFilas() - 1;
			while (f >= 0 && !ganaAmbas) {
				fichaAComprobar = tablero.getFicha(f, c);
				if (ReglasJuegoCuatroEnLinea.cuatroEnLinea(tablero, fichaAComprobar, f, c))
					if (fichaAComprobar == Ficha.BLANCA)
						ganaBlancas = true;
					else
						ganaNegras = true;
				if (ganaBlancas && ganaNegras)
					ganaAmbas = true;
				f--;
			}

			if (!ganaAmbas) {
				if (ganaBlancas) // si las blancas hacen 4 en raya y las negras no
					return Ficha.BLANCA;
				else if (ganaNegras) //si las negras hacen 4 en raya y las blancas no
					return Ficha.NEGRA;
				else
					return Ficha.VACIA;// si ninguno hace 4 en raya
			} else
				// si los dos hacen 4 en raya
				return Ficha.VACIA;

		} else {// si la columna no estaba llena 
			if (ReglasJuegoCuatroEnLinea.cuatroEnLinea(tablero, ultimo, f, c))
				return ultimo;
			else
				return Ficha.VACIA;
		}

	}

	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}
	
	@Override
	public Ficha siguienteTurno(Ficha ultimo,TableroSoloLectura t) {
		if (ultimo == Ficha.BLANCA)
			return Ficha.NEGRA;
		else
			return Ficha.BLANCA;
	}
	
	public Ficha ganadorImposibleMover(TableroSoloLectura t){
		return null;
	}

}
