package interfazGrafica;

import javax.swing.JButton;

import logica.Ficha;

/**
 * Clase creada para implementar los botones del tablero
 * 
 *
 */
public class Casilla extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int fila;
	private int columna;
	private Ficha color;

	public Casilla(int fila, int columna, Ficha color) {
		super();
		this.setFila(fila);
		this.setColumna(columna);
		this.setColor(color);
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public Ficha getColor() {
		return color;
	}

	public void setColor(Ficha color) {
		this.color = color;
	}

}
