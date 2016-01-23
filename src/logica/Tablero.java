package logica;

public class Tablero implements TableroSoloLectura{

	private Ficha[][] tablero;
	private int columnas;
	private int filas;

	/**
	 * Constructora de Tablero introduciendo parametros
	 * 
	 * @param filas
	 *            Situa la altura del tablero
	 * @param columnas
	 *            Mide la anchura del mismo
	 */
	public Tablero(int filas, int columnas) {
		this.columnas = columnas;
		this.filas = filas;
		this.tablero = new Ficha[filas][columnas];
		reset();
	}

	/**
	 * Metodo de consulta del ancho del tablero
	 * 
	 * @return columnas del tablero
	 */
	public int getColumnas() {
		return this.columnas;
	}

	/**
	 * Metodo de consulta del alto del tablero
	 * 
	 * @return filas del tablero
	 */
	public int getFilas() {
		return this.filas;
	}

	/**
	 * Encuentra la ficha colocada en una posicion
	 * 
	 * @param fila
	 * @param columna
	 * @return Ficha en dicha posicion
	 */
	public Ficha getFicha(int fila, int columna) {
		return tablero[fila][columna];
	}

	/**
	 * Coloca una ficha en una posicion
	 * 
	 * @param fila
	 * @param columna
	 * @param ficha
	 */
	public void setFicha(int fila, int columna, Ficha ficha) {
		this.tablero[fila][columna] = ficha;
	}

	/**
	 * resetea el tablero colocando todas las fichas a VACIA
	 */
	public void reset() {
		for (int i = 0; i < this.filas; i++)
			for (int j = 0; j < this.columnas; j++)
				setFicha(i, j, Ficha.VACIA);
	}

	/**
	 * Comprueba si el tablero esta lleno
	 * @return true si no hay fichas vacias
	 */
	public boolean completo() {

		boolean completo = true;
		int i = 0;

		while (completo && i < this.columnas) {
			if (getFicha(0, i) == Ficha.VACIA)
				completo = false;
			i++;
		}

		return completo;
	}

	/**
	 * Crea un string con el tablero listo para ser presentado por pantalla
	 */
	public String toString() {

		String string = System.getProperty("line.separator");

		for (int i = 0; i < this.filas; i++) {
			string += "| ";

			for (int j = 0; j < this.columnas; j++) {

				switch (getFicha(i, j)) {
				case NEGRA:
					string += "X ";
					break;
				case BLANCA:
					string += "O ";
					break;
				default:
					string += "  ";
					break;
				}

			}
			string += "|" + System.getProperty("line.separator");
		}

		string += "+";
		for (int i = 0; i < this.columnas; i++) {
			string += " -";
		}
		string += " +" + System.getProperty("line.separator") + "  ";

		for (int i = 0; i < this.columnas; i++) {
			string += (i + 1) + " ";
		}

		return string;

	}

	

}

