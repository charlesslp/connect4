package control;

import logica.Ficha;
import logica.Tablero;
import logica.TableroSoloLectura;

public class JugadorAleatorioReversi extends Jugador {

	
	@Override
	public void obtenFilaColumna(Tablero tab, Ficha color) {
		int fichasTotalCambiadas = 0, fichasCambiadasMAX = 0;
		
		for (int fila = 0; fila < tab.getFilas(); fila++) {
			for (int columna = 0; columna < tab.getColumnas(); columna++) {
				if (tab.getFicha(fila, columna) == Ficha.VACIA) {
					fichasTotalCambiadas = 0;
					for (int campoX = -1; campoX <= 1; campoX++) {
						for (int campoY = -1; campoY <= 1; campoY++) {
							fichasTotalCambiadas += inteligencia(columna, fila, campoX, campoY, color, tab);
							if(fichasTotalCambiadas > fichasCambiadasMAX){ //Si hemos cambiado mas fichas que antes, nos quedamos con esa opción
								fichasCambiadasMAX = fichasTotalCambiadas;
								this.fila = fila;
								this.columna = columna;
							}
							else{
								if(fichasTotalCambiadas == fichasCambiadasMAX){ //Si cambiamos el mismo numero de fichas, es preferible llegar hasta el borde del tablero ya que es mas facil ganar en este caso
									if(fila == 0 || fila == tab.getFilas() || columna == 0 || columna == tab.getColumnas()){
										this.fila = fila;
										this.columna = columna;
									}
								}
							}
						}
					}
				}
			}
		}
	}
	

	/*
	 * Este metodo comprueba que se puede mover en la direccion indicada desde la posicion indicada
	 */
	public int inteligencia(int finalPosX, int finalPosY, int campoX, int campoY, Ficha turno, TableroSoloLectura t){
		
		int fichasCambiadas = 1;
		boolean grupo = false, primeraFicha = true, acabar = false; //la primera vez que comprobamos la ficha contigua no forma grupo si es del mismo color que el turno 
		if(campoX == 0 && campoY == 0)
			return 0;
		finalPosX += campoX; // dejamos pasar la primera
		finalPosY += campoY;

		while (dentroDeTablero(t, finalPosY, finalPosX) && !acabar) {
			if (t.getFicha(finalPosY, finalPosX)!=Ficha.VACIA && t.getFicha(finalPosY, finalPosX) != turno ) { // si no es de tu turno (hay que voltearla)
				finalPosX += campoX;
				finalPosY += campoY;
				primeraFicha = false; // ya no es la primera
				fichasCambiadas++;
			} else {
				if (!primeraFicha && t.getFicha(finalPosY, finalPosX)!=Ficha.VACIA) { // si no es la primera ficha o la ultima es vacia
					grupo = true;
				}
				acabar = true;
			}
		}

		if(grupo)
			return fichasCambiadas;
		else
			return 0;
	}
	

	private boolean dentroDeTablero(TableroSoloLectura t, int fila, int columna){
		return fila >= 0 && fila < t.getFilas() && columna >= 0 && columna < t.getColumnas();
	}
	
}
