package logica;

public class ReglasGravity extends ReglasJuego{


	private int _columnas;
	private int _filas;
	
	public ReglasGravity(int f, int c){
		this._columnas = c;
		this._filas = f;
	}
	
	@Override
	public Tablero iniciaTablero() {
		return new Tablero(this._filas,this._columnas);
	}

	@Override
	public boolean tablas(Tablero t) {
		
		int fila = 0, columna = 0;
		boolean tablas = true;
		
		while( fila < this._filas && tablas){
			while(columna < this._columnas && tablas){
				tablas = (t.getFicha(fila, columna)!=Ficha.VACIA);
				columna++;
			}
			columna = 0;
			fila++;
		}
		
		return tablas;
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
