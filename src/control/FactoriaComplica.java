package control;

import java.util.Scanner;

import logica.Ficha;
import logica.Movimiento;
import logica.MovimientoComplica;
import logica.ReglasComplica;
import logica.ReglasJuego;

public class FactoriaComplica implements FactoriaTipoJuego {

	
	public ReglasJuego creaReglas() {
		return new ReglasComplica();
	}
	
	public String tituloJuego(){
		return "   Complica  ";
	}
	
	public Movimiento creaMovimiento(int f, int c, Ficha color) {
		return new MovimientoComplica(c, color);
	}
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioComplica();
	}
	public Jugador creaJugadorHumano(Scanner sc) {
		return new JugadorHumanoComplica(sc);
	}
	
	
}
