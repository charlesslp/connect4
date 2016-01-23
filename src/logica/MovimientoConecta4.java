package logica;

import exceptions.ExcepcionLlena;
import exceptions.MovimientoInvalido;
import exceptions.ExcepcionColumna;


public class MovimientoConecta4 extends Movimiento {

	/**
	 * Constructora de movimiento conecta 4
	 * 
	 * @param columna
	 *            introducida por el usuario
	 * @param turno
	 *            turno del que juega
	 * @param t
	 *            tablero
	 */
	public MovimientoConecta4(int columna, Ficha turno){
		super(columna, turno);
	}

	/**
	 * Encuentra la fila donde debe colocarse la ficha
	 * 
	 * @param columna
	 * @param t
	 *            tablero
	 * @return fila donde se colocara la ficha
	 */
	private int getFila(int columna, Tablero t) throws MovimientoInvalido{

		int n = t.getFilas() - 1;
		boolean vacia = false;

		while (n >= 0 && !vacia) {

			if (t.getFicha(n, columna) != Ficha.VACIA)
				n--;
			else
				vacia = true;

		}
		if (n == -1) // Esto sólo pasa si la columna está llena
			n = -2;

		return n;

	}

	
	
	@Override
	public void ejecutaMovimiento(Tablero t) throws MovimientoInvalido{
		
		if (!this.columnaCorrecta(t)){ // columna mal introducida
			throw new ExcepcionColumna(t.getColumnas());
		}
		else{
			if(this.columnaLlena(t)){ // columna llena
				throw new ExcepcionLlena(this.columna + 1);
			}
			else{
				this.fila = this.getFila(this.columna, t);
				t.setFicha(this.fila, this.columna, this.turno);
			}
		}
		
	}

	@Override
	public void undo(Tablero t) {

		boolean undoRealizado = false;
		int fila = t.getFilas() - 1;

		// mientras no llegue arriba del tablero y no haya quitado la ficha
		while (fila >= 0 && !undoRealizado) {
			// si encuentra una ficha vacía o es la del tope, elimina la de
			// debajo
			if (t.getFicha(fila, this.columna) == Ficha.VACIA || fila == 0) {

				// si no es la última se suma 1 a la fila para que no quite esa
				// vacía
				if (t.getFicha(fila, this.columna) == Ficha.VACIA)
					fila++;

				// intercambia la ficha por una vacía
				t.setFicha(fila, this.columna, Ficha.VACIA);
				undoRealizado = true; // el deshacer se ha hecho con éxito

			}
			// si no, sube una fila y vuelve a repetir
			else
				fila--;
		}
	}

}
