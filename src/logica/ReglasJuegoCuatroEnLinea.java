package logica;

import logica.Ficha;
import logica.Tablero;

public class ReglasJuegoCuatroEnLinea {

	/**
	 * Comprueba a partir de una posicion si hay 4 en linea
	 * 
	 * @param t
	 *            tablero
	 * @param turno
	 * @param f
	 *            fila
	 * @param c
	 *            columna
	 * @return true si hay 4 en linea
	 */
	static public boolean cuatroEnLinea(Tablero t, Ficha turno, int f, int c) {

		if (compruebaVerticales(f, c, t, turno))
			return true;
		else if (compruebaDiagonales(f, c, t, turno))
			return true;
		else if (compruebaHorizontales(f, c, t, turno))
			return true;
		else
			return false;
	}

	
	/**
	 * Comprueba en vertical si hay 4 en linea
	 * 
	 * @param fila
	 * @param columna
	 * @param t
	 *            turno
	 * @param turno
	 * @return true si hay 4 en linea
	 */
	static private boolean compruebaVerticales(int fila, int columna, Tablero t,Ficha turno){
		int cont = 1;
		int filaAComprobar = fila-1;
		boolean grupo = false;

		// comprueba primero hacia arriba
		while (!grupo && fila < t.getFilas() && filaAComprobar >= 0
				&& t.getFicha(filaAComprobar, columna) == turno) {
			cont++;
			filaAComprobar--;
			if (cont == 4)
				grupo = true;
		}

		filaAComprobar = fila +1;

		// y si no hay grupo todavía, comprueba hacia abajo
		while (!grupo && filaAComprobar < t.getFilas() && filaAComprobar >= 0
				&& t.getFicha(filaAComprobar, columna) == turno) {
			cont++;
			filaAComprobar++;
			if (cont == 4)
				grupo = true;
		}

		return grupo;
	}

	/**
	 * Comprueba en horizontal si hay 4 en linea
	 * 
	 * @param fila
	 * @param columna
	 * @param t
	 *            turno
	 * @param turno
	 * @return true si hay 4 en linea
	 */
	static private boolean compruebaHorizontales(int fila, int columna, Tablero t, Ficha turno) {
		int cont = 1;
		int columnaAComprobar = columna;
		boolean grupo = false;

		// comprueba primero hacia la izquierda
		while (!grupo && columnaAComprobar <= t.getColumnas() - 1 && columnaAComprobar > 0
				&& t.getFicha(fila, columnaAComprobar - 1) == turno) {
			cont++;
			columnaAComprobar--;
			if (cont == 4)
				grupo = true;
		}

		columnaAComprobar = columna;

		// y si no hay grupo todavía, comprueba hacia la derecha
		while (!grupo && columnaAComprobar < t.getColumnas() - 1 && columnaAComprobar >= 0
				&& t.getFicha(fila, columnaAComprobar + 1) == turno) {
			cont++;
			columnaAComprobar++;
			if (cont == 4)
				grupo = true;
		}

		return grupo;
	}

	/**
	 * Comprueba en las diagonales para ver si hay 4 en linea
	 * 
	 * @param fila
	 * @param columna
	 * @param t
	 *            tablero
	 * @param turno
	 * @return true si hay 4 en linea
	 */
	static private boolean compruebaDiagonales(int fila, int columna, Tablero t, Ficha turno) {

		int cont = 1;
		int filaAComprobar = fila, columnaAComprobar = columna;
		boolean grupo = false;

		// comprueba la diagonal superior izquierda
		while (!grupo && filaAComprobar > 0 && columnaAComprobar > 0
				&& t.getFicha(filaAComprobar - 1, columnaAComprobar - 1) == turno) {
			cont++;
			filaAComprobar--; // sube de fila
			columnaAComprobar--; // baja de columna
			if (cont == 4)
				grupo = true;
		}
		filaAComprobar = fila;
		columnaAComprobar = columna;

		// comprueba la diagonal inferior derecha
		while (!grupo && filaAComprobar < t.getFilas() - 1 && columnaAComprobar < t.getColumnas() - 1
				&& t.getFicha(filaAComprobar + 1, columnaAComprobar + 1) == turno) {
			cont++;
			filaAComprobar++; // baja de fila
			columnaAComprobar++; // sube de columna
			if (cont == 4)
				grupo = true;
		}
		filaAComprobar = fila;
		columnaAComprobar = columna;
		cont = 1;

		// comprueba la diagonal superior derecha
		while (!grupo && filaAComprobar > 0 && columnaAComprobar < t.getColumnas() - 1
				&& t.getFicha(filaAComprobar - 1, columnaAComprobar + 1) == turno) {
			cont++;
			filaAComprobar--; // sube de fila
			columnaAComprobar++; // sube de columna
			if (cont == 4)
				grupo = true;
		}
		filaAComprobar = fila;
		columnaAComprobar = columna;

		// comprueba la diagonal inferior izquierda
		while (!grupo && filaAComprobar < t.getFilas() - 1 && columnaAComprobar > 0
				&& t.getFicha(filaAComprobar + 1, columnaAComprobar - 1) == turno) {
			cont++;
			filaAComprobar++; // baja de fila
			columnaAComprobar--; // baja de columna
			if (cont == 4)
				grupo = true;
		}

		return grupo;
	}
	
}
