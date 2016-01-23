package control;

import java.util.Scanner;

import logica.Ficha;
import logica.Tablero;

public class JugadorHumanoConecta4 extends Jugador {

	private Scanner sc;

	public JugadorHumanoConecta4(Scanner sc) {
		this.sc = sc;
	}

	@Override
	public void obtenFilaColumna(Tablero tab, Ficha color) {
		
		System.out.print("Introduce la columna: ");
		
		int col = sc.nextInt();
		
		sc.nextLine(); // Suprimimos el intro
		
		this.fila = tab.getFilas();
		this.columna = col-1;
	}
}
