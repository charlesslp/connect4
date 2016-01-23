package logica;

import logica.Ficha;
import logica.ReglasJuego;
import logica.Tablero;
import logica.ReglasJuegoCuatroEnLinea;

	
	
public class ReglasConecta4 extends ReglasJuego{

	private final static int FILAS = 4;
	private final static int COLUMNAS = 5;
	
	/** 
	 * Constructora de reglas conecta 4
	 */
	public ReglasConecta4(){
	}

	@Override
	public boolean tablas(Tablero t) {
		return t.completo();
	}
	
	@Override
	public Tablero iniciaTablero() {
		return new Tablero(FILAS,COLUMNAS);
	}

	@Override
	public Ficha hayGanador(int f, int c, Ficha ultimo, Tablero tablero) {
		
		if(ReglasJuegoCuatroEnLinea.cuatroEnLinea(tablero, ultimo, f, c)) 
			return ultimo;
		else return Ficha.VACIA;
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
