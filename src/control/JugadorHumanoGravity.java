package control;

import java.util.Scanner;

import logica.Ficha;
import logica.Tablero;

public class JugadorHumanoGravity extends Jugador {
	private Scanner sc;

	public JugadorHumanoGravity(Scanner sc) {
		this.sc = sc;
	}

	@Override
	public void obtenFilaColumna(Tablero tab, Ficha color) {
		
		System.out.print("Introduce la columna: ");
		
		int col = sc.nextInt();
		
		System.out.print("Introduce la fila: ");
		
		int fila = sc.nextInt();
				
		sc.nextLine(); // Suprimimos el intro
		
		this.fila = tab.getFilas()-fila;
		this.columna = col-1;
	}
}
