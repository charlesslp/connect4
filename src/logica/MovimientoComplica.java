package logica;

import exceptions.MovimientoInvalido;
import exceptions.ExcepcionColumna;
import logica.Ficha;
import logica.Tablero;

public class MovimientoComplica extends Movimiento{
	
	private Ficha fichaDesplazada;

	
	/**
	 * Constructora de Movimiento Complica
	 * 
	 * @param columna
	 *            donde se quiere colocar la ficha
	 * @param turno
	 *            del que juega
	 * @param t
	 *            tablero
	 */
	public MovimientoComplica(int columna, Ficha turno){
		super(columna, turno);
		this.fichaDesplazada = Ficha.VACIA;
	}

	/**
	 * Encuentra la fila donde debe colocarse la ficha
	 * 
	 * @param columna
	 * @param t
	 *            tablero
	 * @return fila donde se colocara la ficha
	 */
	private int getFila(int columna, Tablero t) {

		int n = t.getFilas() - 1;
		boolean vacia = false;

		while (n >= 0 && !vacia) {

			if (t.getFicha(n, columna) != Ficha.VACIA)
				n--;
			else
				vacia = true;
		}
		return n; // devuelve -1 si la columna está llena
	}
	
	
	
	@Override
	public void ejecutaMovimiento(Tablero t) throws MovimientoInvalido{
		
		if (!this.columnaCorrecta(t)) { // columna mal introducida
			throw new ExcepcionColumna(t.getColumnas());
		} else {
			this.fila = this.getFila(this.columna, t);
			
			int filaAColocar = this.fila;

			if (this.columnaLlena(t)) { // columna llena
				this.fichaDesplazada = t.getFicha(t.getFilas()-1, columna);
				int filaActual = t.getFilas() - 1;
				while (filaActual > 0) {
					t.setFicha(filaActual, this.columna,
							t.getFicha(filaActual - 1, this.columna));
					filaActual--;
				}
				filaAColocar = 0;
			}

			t.setFicha(filaAColocar, this.columna, this.turno);
		}
	}

	@Override
	public void undo(Tablero t) {

		boolean undoRealizado = false;
		int fila = t.getFilas() - 1;

		if (this.fichaDesplazada == Ficha.VACIA) { // el deshacer no implica mover toda la columna

			// mientras no llegue arriba del tablero y no haya quitado la ficha
			while (fila >= 0 && !undoRealizado) {
				// si encuentra una ficha vacía o es la del tope, elimina la de
				// debajo
				if (t.getFicha(fila, this.columna) == Ficha.VACIA || fila == 0) {

					// si no es la última se suma 1 a la fila para que no quite
					// esa vacía
					if (t.getFicha(fila, this.columna) == Ficha.VACIA) // para poder deshacer la ficha de arriba
						fila++;

					// intercambia la ficha por una vacía
					t.setFicha(fila, this.columna, Ficha.VACIA);
					undoRealizado = true; // el deshacer se ha hecho con éxito
				}
				// si no, sube una fila y vuelve a repetir
				else
					fila--;
			}
		} else { // desplaza toda la columna hacia arriba
			int filaActual = 0;
			while (filaActual < t.getFilas() - 1) {
				t.setFicha(filaActual, columna, t.getFicha(filaActual + 1, this.columna));
				filaActual++;
			}
			// coloca la ficha abajo
			t.setFicha(t.getFilas()-1, this.columna, this.fichaDesplazada);

		}

	}

}
