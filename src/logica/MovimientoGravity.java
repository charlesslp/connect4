package logica;


import exceptions.ExcepcionOcupada;
import exceptions.ExcepcionPosicion;
import exceptions.MovimientoInvalido;

public class MovimientoGravity extends Movimiento{

	
	private int campoY;
	private int campoX;
	
	/**
	 * Constructora de movimientoGravity
	 * @param fila
	 * @param columna
	 * @param turno
	 */
	public MovimientoGravity(int fila, int columna, Ficha turno) {
		super(columna, turno);
		this.fila = fila;
		this.campoX = 0;
		this.campoY = 0;
	}

	/**
	 * Comprueba si la fila es correcta
	 * @param t	tablero
	 * @return	true si esta dentro del tablero
	 */
	public boolean filaCorrecta(Tablero t){
		
		return this.fila >= 0 && this.fila < t.getFilas();
		
	}
	
	
	public void calculaCampo(Tablero t) {

		double columnaMedio = (double)(t.getColumnas()-1) / 2, 
			   filaMedio = (double)(t.getFilas()-1) / 2;

		if (columna < columnaMedio) { // mitad izquierda del tablero
			if (fila < filaMedio) { // cuadro de arriba izquierda
				if (fila < columna) {
					campoX = -1;
					campoY = 0;
				} else if (fila > columna) {
					campoX = 0;
					campoY = -1;
				} else { // diagonal arriba izquierda
					campoX = -1;
					campoY = -1;
				}
			} else if (fila > filaMedio) {// cuadro de abajo izquierda
				if (t.getFilas() - fila -1 < columna) {
					campoX = 1;
					campoY = 0;
				} else if (t.getFilas() - fila -1 > columna) {
					campoX = 0;
					campoY = -1;
				} else { // diagonal abajo izquierda
					campoX = 1;
					campoY = -1;
				}
			}
			else if (fila == filaMedio){ // fila del medio (columna < columnaMedio)
				campoX = 0;
				campoY = -1;
			}
		} else if(columna > columnaMedio){ // mitad derecha del tablero
			if (fila < filaMedio) { // cuadro de arriba derecha
				if (fila < t.getColumnas() - columna -1) {
					campoX = -1;
					campoY = 0;
				} else if (fila > t.getColumnas() - columna -1) {
					campoX = 0;
					campoY = 1;
				} else { // diagonal arriba derecha
					campoX = -1;
					campoY = 1;
				}
			} else if (fila > filaMedio) {// cuadro de abajo derecha
				if (t.getFilas() - fila -1 < t.getColumnas() - columna -1) {
					campoX = 1;
					campoY = 0;
				} else if (t.getFilas() - fila -1 > t.getColumnas() - columna -1) {
					campoX = 0;
					campoY = 1;
				} else { // diagonal abajo derecha
					campoX = 1;
					campoY = 1;
				}
			}
			else if (fila == filaMedio){// fila del medio (columna > columnaMedio)
				campoX = 0;
				campoY = 1;
			}
		}
		else if (columna == columnaMedio){ // columna del medio
			if (fila < filaMedio) {
				campoX = -1;
				campoY = 0;
			} else if (fila > filaMedio) {
				campoX = 1;
				campoY = 0;
			}
			else{ // centro del tablero
				campoX = 0;
				campoY = 0;
			}
		}
	}	
	
	
	
	@Override
	public void ejecutaMovimiento(Tablero t) throws MovimientoInvalido{

				
		if (!this.columnaCorrecta(t)){ // columna mal introducida
			throw new ExcepcionPosicion();
		}
		else
			if(!this.filaCorrecta(t)){ // fila mal introducida
				throw new ExcepcionPosicion();
			}
			else
				if(t.getFicha(this.fila, this.columna) != Ficha.VACIA){ // posicion ocupada
					throw new ExcepcionOcupada();
				}
				else{
					
					calculaCampo(t);
					if(campoX!=0 || campoY!=0){ // si no es la posicion central
							
						while(fila+campoX < t.getFilas() && fila+campoX >= 0 // la fila no se salga del tablero
							&& columna+campoY < t.getColumnas() && columna+campoY >= 0 // la columna no se salga del tablero
							&& t.getFicha(fila+campoX, columna+campoY)==Ficha.VACIA){ // la siguiente ficha no esté ocupada
							
							columna = columna+campoY; // actualizamos columna
							fila = fila+campoX; // actualizamos fila
							
						}
					}
					
					t.setFicha(fila, columna, turno); // colocamos la ficha
				}		
	}
	
	
	@Override
	public void undo(Tablero t) {
		
		t.setFicha(this.fila, this.columna, Ficha.VACIA);
		
	}

}
