package control;

import java.util.Scanner;

import logica.Ficha;
import logica.Movimiento;
import logica.MovimientoReversi;
import logica.ReglasJuego;
import logica.ReglasReversi;

public class FactoriaReversi implements FactoriaTipoJuego{
	
	@Override
	public ReglasJuego creaReglas() {
		return new ReglasReversi();
	}

	@Override
	public String tituloJuego() {
		return "   Reversi  ";
	}

	@Override
	public Movimiento creaMovimiento(int fila, int col, Ficha color) {
		return new MovimientoReversi(col,fila,color);
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioReversi();
	}

	@Override
	public Jugador creaJugadorHumano(Scanner sc) {
		return new JugadorHumanoReversi(sc);
	}

}
