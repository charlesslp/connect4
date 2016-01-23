package logica;

/**
 * Este interfaz ofrece los metodos para consultar la informacion del tablero
 */
public interface TableroSoloLectura {

	int getFilas();

	int getColumnas();

	Ficha getFicha(int fila, int col);

	String toString();

}
