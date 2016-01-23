package control;

import java.util.Scanner;

import logica.Ficha;
import logica.Movimiento;
import logica.MovimientoConecta4;
import logica.ReglasConecta4;
import logica.ReglasJuego;

public class FactoriaC4 implements FactoriaTipoJuego {

	
	public ReglasJuego creaReglas() {
		return new ReglasConecta4();
	}
	
	public String tituloJuego(){
		return "   Conecta 4 ";
	}
	
	public Movimiento creaMovimiento(int f, int c, Ficha color) {
		return new MovimientoConecta4(c, color);
	}
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioConecta4();
	}
	public Jugador creaJugadorHumano(Scanner sc) {
		return new JugadorHumanoConecta4(sc);
	}
	
	

}
