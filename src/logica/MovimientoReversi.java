package logica;

import exceptions.ExcepcionOcupada;
import exceptions.ExcepcionPosicion;
import exceptions.MovimientoInvalido;

public class MovimientoReversi extends Movimiento{
	
	private int finalPosX;
	private int finalPosY;
	private Tablero copiaTablero; // necesario para el undo
	
	public MovimientoReversi(int columna, int fila, Ficha turno) {
		super(columna, turno);
		this.fila = fila;
		this.finalPosX = 0;
		this.finalPosY = 0;
		copiaTablero = new Tablero(ReglasReversi.FILAS,ReglasReversi.COLUMNAS);
	}
	
	public MovimientoReversi(int columna,int fila,Ficha turno, int finalPosX,int finalPosY){
		super(columna, turno);
		this.fila = fila;
		this.finalPosX = finalPosX;
		this.finalPosY = finalPosY;
	}

	@Override
	public void ejecutaMovimiento(Tablero t) throws MovimientoInvalido {
		if (!this.columnaCorrecta(t)) { // columna mal introducida
			throw new ExcepcionPosicion();
		} else if (!this.filaCorrecta(t)) { // fila mal introducida
			throw new ExcepcionPosicion();
		} else if (t.getFicha(this.fila, this.columna) != Ficha.VACIA) { // posicion ocupada
			throw new ExcepcionOcupada();
		} else {
			//Copiamos el tablero actual en una copia del tablero para poder deshacer sin problema
			for (int columna = 0; columna < t.getColumnas(); columna++) {
				for (int fila = 0; fila < t.getFilas(); fila++) {
					this.copiaTablero.setFicha(fila, columna,
							t.getFicha(fila, columna));
				}
			}

			if (!move(t))
				throw new MovimientoInvalido(
						"Debes voltear al menos una ficha del contrario");

		}

	}

	@Override
	public void undo(Tablero t) {
		for (int columna = 0; columna < t.getColumnas(); columna++) {
			for (int fila = 0; fila < t.getFilas(); fila++) {
				t.setFicha(fila, columna, this.copiaTablero.getFicha(fila,columna)); 
			}
		}
		
	}
	
	/**
	 * Comprueba si la fila es correcta
	 * @param t	tablero
	 * @return	true si esta dentro del tablero
	 */
	public boolean filaCorrecta(Tablero t){
		
		return this.fila >= 0 && this.fila < t.getFilas();
		
	}

	//Comprueba en todas las direcciones y si encuentra un movimiento valido cambia el color de las fichas
	private boolean move(Tablero t) {
		this.finalPosX = this.columna;
		this.finalPosY = this.fila;
		boolean mismaFicha, movimientoRealizado = false;

		for (int campoX = -1; campoX <= 1; campoX++) {
			for (int campoY = -1; campoY <= 1; campoY++) {

				this.finalPosX = this.columna;
				this.finalPosY = this.fila;
				if (comprobar(campoX, campoY, t)) { // comprobamos si puede
													// mover en esa direccion
					mismaFicha = finalPosY == this.fila
							&& finalPosX == this.columna;
					finalPosX -= campoX;
					finalPosY -= campoY;

					while (!mismaFicha) {
						t.setFicha(finalPosY, finalPosX, this.turno);
						movimientoRealizado = true;
						finalPosX -= campoX;
						finalPosY -= campoY;
						mismaFicha = finalPosY == this.fila
								&& finalPosX == this.columna;
					}
					t.setFicha(this.fila, this.columna, this.turno);

				}

			}
		}
		return movimientoRealizado;
	}

	//Comprueba si se puede mover en la direccion que se pasa por parametro
	public boolean comprobar(int campoX, int campoY, TableroSoloLectura t){
		
		boolean grupo = false, primeraFicha = true, acabar = false; //la primera vez que comprobamos, la ficha contigua no forma grupo si es del mismo color que el turno 
		if(campoX == 0 && campoY == 0)
			return false;
		finalPosX += campoX; // dejamos pasar la primera
		finalPosY += campoY;

		while (dentroDeTablero(t, finalPosY, finalPosX) && !acabar) {
			if (t.getFicha(finalPosY, finalPosX)!=Ficha.VACIA && t.getFicha(finalPosY, finalPosX) != this.turno ) { // si no es de tu turno (hay que voltearla)
				finalPosX += campoX;
				finalPosY += campoY;
				primeraFicha = false; // ya no es la primera
			} else {
				if (!primeraFicha && t.getFicha(finalPosY, finalPosX)!=Ficha.VACIA) { // si no es la primera ficha o la ultima es vacia
					grupo = true;
				}
				acabar = true;
			}
		}

		return grupo;
	}

	//Comprueba que la posicion que estamos mirando permanece entre los límites del tablero
	private boolean dentroDeTablero(TableroSoloLectura t, int fila, int columna){
		return fila >= 0 && fila < t.getFilas() && columna >= 0 && columna < t.getColumnas();
	}


}
