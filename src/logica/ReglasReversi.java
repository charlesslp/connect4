package logica;

public class ReglasReversi extends ReglasJuego{

	
	public final static int FILAS = 8;
	public final static int COLUMNAS = 8;

	
	@Override
	public Tablero iniciaTablero() {
		Tablero tablero = new Tablero(FILAS,COLUMNAS);
		tablero.setFicha(FILAS/2,COLUMNAS/2,Ficha.NEGRA);
		tablero.setFicha(FILAS/2,COLUMNAS/2-1,Ficha.BLANCA);
		tablero.setFicha(FILAS/2-1,COLUMNAS/2,Ficha.BLANCA);
		tablero.setFicha(FILAS/2-1,COLUMNAS/2-1,Ficha.NEGRA);
		
		
		return tablero;
	}

	@Override
	public boolean tablas(Tablero t) {
		int blancas = 0, negras = 0;
		Ficha fichaActual;
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				fichaActual = t.getFicha(i, j);
				if (fichaActual == Ficha.BLANCA)
					blancas++;
				else if (fichaActual == Ficha.NEGRA)
					negras++;
				else
					return false;
			}
		}
		if (blancas == negras)
			return true;
		else
			return false;
	}

	@Override
	public Ficha hayGanador(int f, int c, Ficha ultimo, Tablero tablero) {
		int blancas = 0, negras = 0;
		boolean hay_vacia = false;
		Ficha fichaActual;
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				fichaActual = tablero.getFicha(i, j);
				if (fichaActual == Ficha.BLANCA)
					blancas++;
				else if (fichaActual == Ficha.NEGRA)
					negras++;
				else
					hay_vacia = true;
			}
		}
		if(hay_vacia){
			if(negras > 0 && blancas > 0)// si hay alguna vacia y ademas hay alguna blanca y alguna negra
				return Ficha.VACIA;
			if(negras == 0)// si hay alguna vacia y no hay negras, ganan las blancas
				return Ficha.BLANCA;
			if(blancas == 0)// si hay alguna vacia y no hay blancas, ganan las negras
				return Ficha.NEGRA;
		}
		if (blancas == negras)
			return Ficha.VACIA;
		else if (blancas < negras)
			return Ficha.NEGRA;
		else
			return Ficha.BLANCA;
	}

	@Override
	public Ficha jugadorInicial() {
		return Ficha.NEGRA;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimo,TableroSoloLectura t) {
		
		MovimientoReversi mov;
		//Primero comprobamos si el oponente puede poner una ficha en alguna casilla del tablero
		//Si es asi, devolvemos ultimo, que será el color del oponente
		if (ultimo == Ficha.BLANCA)
			ultimo = Ficha.NEGRA;
		else
			ultimo = Ficha.BLANCA;

		for (int fila = 0; fila < FILAS; fila++) {
			for (int columna = 0; columna < COLUMNAS; columna++) {
				if (t.getFicha(fila, columna) == Ficha.VACIA) {
					for (int campoX = -1; campoX <= 1; campoX++) {
						for (int campoY = -1; campoY <= 1; campoY++) {
							mov = new MovimientoReversi(columna, fila, ultimo,
									columna, fila);
							if (mov.comprobar(campoX, campoY, t))
								return ultimo;
						}
					}
				}
			}
		}

		//En el caso de que nuestro oponente no pueda mover, comprobamos si nosotros podemos mover de nuevo
		//Si es asi, devolvemos ultimo, que será el color del que acaba de mover
		//En caso contrario devolveremos una Ficha.VACIA ya que ningun jugador puede realizar ningun movimiento
		if (ultimo == Ficha.BLANCA)
			ultimo = Ficha.NEGRA;
		else
			ultimo = Ficha.BLANCA;

		for (int fila = 0; fila < FILAS; fila++) {
			for (int columna = 0; columna < COLUMNAS; columna++) {
				if (t.getFicha(fila, columna) == Ficha.VACIA) {
					for (int campoX = -1; campoX <= 1; campoX++) {
						for (int campoY = -1; campoY <= 1; campoY++) {
							mov = new MovimientoReversi(columna, fila, ultimo,
									columna, fila);
							if (mov.comprobar(campoX, campoY, t))
								return ultimo;
						}
					}
				}
			}
		}

		return Ficha.VACIA;

	}
	
	public Ficha ganadorImposibleMover(TableroSoloLectura t){
		
		int blancas = 0, negras = 0;
		Ficha fichaActual;
		for (int i = 0; i < FILAS; i++) {
			for (int j = 0; j < COLUMNAS; j++) {
				fichaActual = t.getFicha(i, j);
				if (fichaActual == Ficha.BLANCA)
					blancas++;
				else if (fichaActual == Ficha.NEGRA)
					negras++;
			}
		}
		
		if (blancas < negras)
			return Ficha.NEGRA;
		else
			return Ficha.BLANCA;
	}

}
