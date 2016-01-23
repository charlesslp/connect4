package control;

import java.util.Scanner;

import logica.Ficha;
import logica.Movimiento;
import logica.MovimientoGravity;
import logica.ReglasGravity;
import logica.ReglasJuego;

public class FactoriaGravity implements FactoriaTipoJuego {

	

	private int _columnas;
	private int _filas;
	
	public FactoriaGravity(int f,int c){
		this._columnas = c;
		this._filas = f;
	}
	
	public String tituloJuego(){
		
		String s = "";
		
		for(int i = _columnas; i>0; i=i/2)
			s += " ";
		return s + "Gravity";
	}
	
	public ReglasJuego creaReglas() {
		return new ReglasGravity(this._filas,this._columnas);
	}
	
	public Movimiento creaMovimiento(int f, int c, Ficha color) {
		return new MovimientoGravity(f, c, color);
	}
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity();
	}
	public Jugador creaJugadorHumano(Scanner sc) {
		return new JugadorHumanoGravity(sc);
	}
	
	
}
