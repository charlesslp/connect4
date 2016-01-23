package control;

import java.util.Scanner;

import logica.Ficha;
import logica.Movimiento;
import logica.ReglasJuego;

public interface FactoriaTipoJuego {

	public ReglasJuego creaReglas();
	public String tituloJuego();
	public Movimiento creaMovimiento(int fila, int col, Ficha color);
	public Jugador creaJugadorAleatorio();
	public Jugador creaJugadorHumano(Scanner sc);

}
